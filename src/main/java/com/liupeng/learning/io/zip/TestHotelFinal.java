package com.liupeng.learning.io.zip;

import com.alibaba.fastjson.JSON;
import com.liupeng.learning.haoqjob.TaskEntry;
import org.apache.commons.net.util.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class TestHotelFinal {

    public static String haoqUnZipFilePath = "D:\\H9847_online_static_xml\\hotelList\\";
    public static String hotelFileName = "hotellist_cityId";
    public static String ext = ".xml";
    public static int largestFileNum = getLargestNum();
    public static int step = 10;
    public static int rezipFileCount = largestFileNum/step + 1;
    public static String haoqReZipFilePath = "D:\\haoq\\hotelList\\";
    public static String haoqCountFileName = "zipcount.txt";

    public static String username = "filex-test";
    public static String password = "123456";
    public static String basicAuth = getHeader(username,password);

    public static void main(String[] args) throws Exception {

        /*//1.本机读取酒店xml文件并重压缩xml文件为zip文件
        if (!validDataDir()) {
            System.out.println("原始数据文件目录不存在，"+haoqUnZipFilePath);
            return;
        }
        rezipLocalFiles();

        //2.删除ctrip的ftp服务器上的原有的zip文件
        deleteFTPFiles();

        //3.将本机新压缩的zip文件上传到ctrip的ftp服务器
        uploadFiles();*/

        //4.定时任务从ctrip的ftp服务器下载解析重压缩包
        downloadAndUnZipFile();

    }

    private static void generateLocalCountFile() throws Exception {
        File zipCountFile = new File(haoqReZipFilePath + haoqCountFileName);
        if (!zipCountFile.getParentFile().exists()) {
            zipCountFile.getParentFile().mkdirs();
        }
        if (!zipCountFile.exists()) {
            zipCountFile.createNewFile();
        }

        byte[] bytes = String.valueOf(rezipFileCount).getBytes("utf-8");
        FileOutputStream fos = new FileOutputStream(zipCountFile);
        fos.write(bytes);
        fos.close();

        System.out.println("本地计数文件"+haoqCountFileName+"创建成功!");
    }

    private static void deleteLocalRezipDir(File localReZipFile) throws Exception {
        if (!localReZipFile.exists()) {
            return;
        }

        if (localReZipFile.isFile()) {
            localReZipFile.delete();
            return;
        } else {
            for (File file : localReZipFile.listFiles()) {
                deleteLocalRezipDir(file);
            }
        }

        localReZipFile.delete();
        System.out.println("清空本地重压缩目录完成！");
    }


    public static void rezipLocalFiles() {
        System.out.println("开始本地重压缩...");
        byte[] buf = new byte[1024];
        try {

            File localReZipFile = new File(haoqReZipFilePath);
            deleteLocalRezipDir(localReZipFile);

            generateLocalCountFile();

            long begin = System.currentTimeMillis();
            for (int j=0; j<rezipFileCount; j++) {
                long start = System.currentTimeMillis();
                if ((j*step+1) > largestFileNum) {
                    break;
                }
                File outZipFile = new File(haoqReZipFilePath+"zip"+(j+1)+".zip");
                if (!outZipFile.getParentFile().exists()) {
                    outZipFile.getParentFile().mkdirs();
                }
                ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(outZipFile));
                for (int i = 1+step*j; i < step+1+step*j && i<=largestFileNum; i++) {
                    File file = new File(haoqUnZipFilePath + hotelFileName + i + ext);
                    if (!file.exists()){
                        continue;
                    }
                    FileInputStream fis = new FileInputStream(file);
                    zos.putNextEntry(new ZipEntry(file.getName()));
                    int len;
                    while ((len = fis.read(buf)) > 0) {
                        zos.write(buf, 0, len);
                    }

                    fis.close();
                    zos.closeEntry();
                }
                zos.close();
                System.out.println((j+1)+"."+outZipFile.getName()+" 重压缩文件创建成功，耗时"+(System.currentTimeMillis()-start)+"毫秒，大小"+(outZipFile.length()/1024/1024)+"MB");
            }
            System.out.println("本地重压缩完成，耗时"+(System.currentTimeMillis()-begin)/(1000)+"秒");
            System.out.println("---------------------------------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("本地重压缩文件异常！");
            System.out.println("---------------------------------------------------------------------");
        }

    }

    public static void deleteFTPFiles() {
        System.out.println("开始删除FTP服务器文件...");
        List<String> fileNames = getFTPFileNames("/igtftptest/hotelList");

        if (fileNames == null) {
            return;
        }

        long begin = System.currentTimeMillis();
        int count = 0;
        for (String fn : fileNames) {
            count++;
            // 创建HttpClientBuilder
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
            CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

            HttpDelete httpDelete = new HttpDelete("http://10.2.73.43:8080/filex/rest/file/NT-TEST?pathAndFile=/igtftptest/hotelList/"+fn);
            httpDelete.addHeader("Authorization",basicAuth);

            CloseableHttpResponse response = null;
            try {
                // 执行请求
                response = closeableHttpClient.execute(httpDelete);
                // 判断返回状态是否为200
                if (response.getStatusLine().getStatusCode() == 200) {
                    System.out.println(count+".FTP服务器文件 "+fn+" 删除成功!");
                } else {
                    System.out.println(count+".FTP服务器文件 "+fn+" 删除失败， "+response.getStatusLine().getReasonPhrase());
                }
            } catch(Exception e) {
                e.printStackTrace();
                System.out.println("FTP服务器文件删除异常！");
                System.out.println("---------------------------------------------------------------------");
            } finally {
                if (response != null) {
                    try {
                        response.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (closeableHttpClient != null) {
                    try {
                        closeableHttpClient.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("FTP服务器文件删除完成，文件个数"+fileNames.size()+"，耗时： "+(System.currentTimeMillis()-begin)/1000 +"秒");
        System.out.println("---------------------------------------------------------------------");
    }

    public static void uploadFiles() {
        System.out.println("开始上传重压缩文件...");
        File localFileDir = new File(haoqReZipFilePath);
        File[] files = localFileDir.listFiles();

        long begin = System.currentTimeMillis();
        int count = 0;
        for (File f : files) {
            count++;
            /* 1.设置basic auth */
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
            CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

            /* 2.设置post参数 */
            HttpPost httpPost = new HttpPost("http://10.2.73.43:8080/filex/rest/file/NT-TEST?path=/igtftptest/hotelList");
            httpPost.addHeader("Authorization",basicAuth);
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();

            CloseableHttpResponse response = null;

            try {
                byte[] bytes;
                HttpEntity httpEntity;
                long start;
                try (FileInputStream fis = new FileInputStream(f)) {
                    Long fileSize = f.length();
                    bytes = new byte[Integer.valueOf(fileSize.toString())];
                    fis.read(bytes);
                    fis.close();
                }
                multipartEntityBuilder.addBinaryBody("file",bytes, ContentType.DEFAULT_BINARY,f.getName());
                httpEntity = multipartEntityBuilder.build();
                httpPost.setEntity(httpEntity);

                start = System.currentTimeMillis();
                response = closeableHttpClient.execute(httpPost);
                // 判断返回状态是否为200
                if (response.getStatusLine().getStatusCode() == 200) {
                    System.out.println(count+".文件"+f.getName()+" 上传成功，耗时: " + (System.currentTimeMillis()-start)/1000+"秒");
                } else {
                    System.out.println(count+".文件"+f.getName()+" 上传失败，"+response.getStatusLine().getReasonPhrase());
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("上传重压缩文件异常！");
                System.out.println("---------------------------------------------------------------------");
            } finally {
                if (response != null) {
                    try {
                        response.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (closeableHttpClient != null) {
                    try {
                        closeableHttpClient.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }//for
        System.out.println("上传重压缩文件完成，耗时: "+(System.currentTimeMillis()-begin)/1000/60+"分钟");
        System.out.println("---------------------------------------------------------------------");
    }

    private static int getZIPFileCount() throws Exception {
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

        HttpGet httpGet = new HttpGet("http://10.2.73.43:8080/filex/rest/file/NT-TEST?pathAndFile=/igtftptest/hotelList/"+ haoqCountFileName);
        httpGet.addHeader("Authorization", basicAuth);

        CloseableHttpResponse response = null;

        response = closeableHttpClient.execute(httpGet);
        // 判断返回状态是否为200
        int count = 0;
        if (response.getStatusLine().getStatusCode() == 200) {
            // 获取服务端返回的数据
            byte[] bytes = EntityUtils.toByteArray(response.getEntity());
            BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
            count = Integer.parseInt(br.readLine());
            br.close();
        } else {
            throw new RuntimeException(response.getStatusLine().getStatusCode()+" "+response.getStatusLine().getReasonPhrase());
        }

        if (response != null) {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (closeableHttpClient != null) {
            try {
                closeableHttpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return count;
    }

    public static void downloadAndUnZipFile() {
        System.out.println("开始下载zip文件并更新数据...");
        long begin = System.currentTimeMillis();

        List<String> fileNames = getFTPFileNames("/igtftptest/hotelList");
        AtomicInteger totalZipCompletedTask = new AtomicInteger(0);
        CountDownLatch totalLatch = new CountDownLatch(fileNames.size()-1);

        int count = 0;
        for (String fn : fileNames) {
            if ("zipcount.txt".equals(fn)) {
                continue;
            }
            count++;
            totalZipCompletedTask.incrementAndGet();
            long start = System.currentTimeMillis();
            // 创建HttpClientBuilder
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
            CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

            HttpGet httpGet = new HttpGet("http://10.2.73.43:8080/filex/rest/file/NT-TEST?pathAndFile=/igtftptest/hotelList/"+fn);
            httpGet.addHeader("Authorization", basicAuth);

            CloseableHttpResponse response = null;
            AtomicInteger singleZipCompletedTask = new AtomicInteger(0);

            try {
                response = closeableHttpClient.execute(httpGet);
                // 判断返回状态是否为200
                if (response.getStatusLine().getStatusCode() == 200) {
                    // 获取服务端返回的数据
                    byte[] bytes = EntityUtils.toByteArray(response.getEntity());
                    // 服务端返回数据的长度
                    DecimalFormat df = new DecimalFormat(".##");
                    System.out.println("["+count+"]"+fn+"文件大小" + df.format(Double.parseDouble(String.valueOf(bytes.length))/1024/1024) + "MB，开始更新...");

                    //根据字节流解析为zip文件
                    ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(bytes));
                    ZipEntry zipEn = null;
                    final Integer totalTaskCount;
                    ArrayList<TaskEntry> taskList = new ArrayList();

                    while ((zipEn = zis.getNextEntry()) != null) {
                        if (!zipEn.isDirectory()) {
                            int fileSize = (int) zipEn.getSize();

                            if (fileSize == -1) {
                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                int b;
                                while ((b=zis.read()) != -1) {
                                    baos.write(b);
                                }

                                TaskEntry taskEntry = new TaskEntry();
                                taskEntry.setFileName(zipEn.getName());
                                taskEntry.setFileBytes(baos.toByteArray());
                                taskList.add(taskEntry);

                                baos.close();

                                continue;
                            }

                            byte[] b = new byte[fileSize];
                            int rb = 0, chunk = 0;

                            //通过循环一次把数据全部都读到内存中去
                            while(fileSize - rb > 0)
                            {
                                chunk = zis.read(b, rb, fileSize - rb);
                                if (chunk <= 0)
                                {
                                    break;
                                }
                                rb += chunk;
                            }

                            //打印文件名称
                            TaskEntry taskEntry = new TaskEntry();
                            taskEntry.setFileName(zipEn.getName());
                            taskEntry.setFileBytes(b);
                            taskList.add(taskEntry);

                        }//if

                    }//while

                    zis.close();

                    int totalCount = taskList.size();
                    CountDownLatch latch = new CountDownLatch(totalCount);
                    ExecutorService pool = Executors.newFixedThreadPool(5);

                    for (TaskEntry taskEntry : taskList) {
                        pool.submit(new Runnable() {
                            @Override public void run() {
                                //插入数据
                                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                                System.out.println(singleZipCompletedTask.incrementAndGet()+". "+taskEntry.getFileName()+" sync data finished! "+df.format(new Date()));
                                latch.countDown();
                            }
                        });
                    }

                    latch.await();
                    pool.shutdown();
                    System.out.println("["+count+"]"+fn+"下载并同步数据完成，xml文件总数"+singleZipCompletedTask+"，耗时: "+(System.currentTimeMillis()-start)/1000/60+"分钟");
                    System.out.println("_____________________________________________________________________");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("下载并更新酒店数据异常！");
                System.out.println("---------------------------------------------------------------------");
            } finally {
                if (response != null) {
                    try {
                        response.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (closeableHttpClient != null) {
                    try {
                        closeableHttpClient.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }//finally
            totalLatch.countDown();
        }//for

        try {
            totalLatch.await();
            System.out.println("下载zip文件并同步数据完成，zip文件总数"+totalZipCompletedTask.get()+"，耗时: "+(System.currentTimeMillis()-begin)/1000/60+"分钟");
            System.out.println("---------------------------------------------------------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int getFilesSize(String path) {
        long begin = System.currentTimeMillis();
        int fileCount = 0;
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

        HttpGet httpGet = new HttpGet("http://10.2.73.43:8080/filex/rest/file/NT-TEST/list?path="+path);
        httpGet.addHeader("Authorization",basicAuth);

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = closeableHttpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                System.out.println("SUCCESS ");
                // 获取服务端返回的数据
                byte[] bytes = EntityUtils.toByteArray(response.getEntity());
                File f = new File(haoqReZipFilePath+"dir.txt");
                if (!f.exists()) {
                    f.createNewFile();
                }
                FileOutputStream fos = new FileOutputStream(f);
                fos.write(bytes);
                fos.close();

                String json = new String(bytes,"utf-8");
                CtripDirList ctripDirList = JSON.parseObject(json,CtripDirList.class);
                if (ctripDirList != null && ctripDirList.getFiles() != null) {
                    fileCount = ctripDirList.getFiles().size();
                }
            } else {
                System.out.println(response.getStatusLine().getReasonPhrase()+" ");
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("查看目录异常！");
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (closeableHttpClient != null) {
                try {
                    closeableHttpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("查看目录完成，耗时： "+(System.currentTimeMillis()-begin)/1000 +"秒");
        return fileCount;
    }

    public static List<String> getFTPFileNames(String path) {
        long begin = System.currentTimeMillis();
        List<String> fileNames = null;
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

        HttpGet httpGet = new HttpGet("http://10.2.73.43:8080/filex/rest/file/NT-TEST/list?path="+path);
        httpGet.addHeader("Authorization",basicAuth);

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = closeableHttpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                byte[] bytes = EntityUtils.toByteArray(response.getEntity());
                String json = new String(bytes,"utf-8");
                CtripDirList ctripDirList = JSON.parseObject(json,CtripDirList.class);
                if (ctripDirList != null && ctripDirList.getFiles() != null) {
                    fileNames = ctripDirList.getFiles().stream().map(o->o.getName()).collect(Collectors.toList());
                }
            } else {
                System.out.println(response.getStatusLine().getReasonPhrase()+" ");
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("查看目录异常！");
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (closeableHttpClient != null) {
                try {
                    closeableHttpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return fileNames;
    }

    public static int getLargestNum() {

        if (!validDataDir()) {
            return -1;
        }

        try {
            File file = new File(haoqUnZipFilePath);
            int startIndex = hotelFileName.length();
            String[] fileNames = file.list();
            int max = 0;
            for (String fileName : fileNames) {
                int endIndex = fileName.indexOf(ext);
                int num = Integer.parseInt(fileName.substring(startIndex,endIndex));
                max = Math.max(max,num);
            }

            return max;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("获取最大酒店文件名异常!");
            return 18482;
        }
    }

    public static boolean validDataDir() {
        File examDirFile = new File(haoqUnZipFilePath);
        if (!examDirFile.exists()) {
            return false;
        }
        return true;
    }

    private static String getHeader(String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("UTF-8")));
        String authHeader = "Basic " + new String(encodedAuth);
        return authHeader;
    }
}
