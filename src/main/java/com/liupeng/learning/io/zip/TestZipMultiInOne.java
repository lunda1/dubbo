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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class TestZipMultiInOne {

    public static String haoqUnZipFilePath = "D:\\H9847_online_static_xml\\hotelList\\";
    public static String hotelFileName = "hotellist_cityId";
    public static String ext = ".xml";
    public static int largestFileNum = getLargestNum();
    public static int step = 100;
    public static int rezipFileCount = largestFileNum/step + 1;
    public static String haoqReZipFilePath = "D:\\haoq\\hotelList\\";


    public static String username = "filex-test";
    public static String password = "123456";
    public static String basicAuth = getHeader(username,password);

    public static void main(String[] args) throws Exception {
        if (!validDataDir()) {
            System.out.println("原始数据文件目录不存在，"+haoqUnZipFilePath);
            return;
        }

        //1.重压缩xml文件
        //rezipFile();

        //2.删除ctrip的ftp上的zip文件
        //deleteFile();
        //deleteFileWithPool();

        //3.上传重压缩包
        //uploadZipFile();
        //uploadZipFileWithPool();

        //4.下载解析重压缩包
        //downloadAndUnZipFile();

        //5.
        //testZip11();

        //6.列表zip文件
        //listZip();

        //7.dos test
        //testDos();

        //8.查看目录文件列表
        int count = getFilesSize("/igtftptest/hotelList");
        System.out.println(count);
    }

    public static void rezipFile() {

        byte[] buf = new byte[1024];
        try {
            long begin = System.currentTimeMillis();
            for (int j=0; j<rezipFileCount; j++) {
                long start = System.currentTimeMillis();
                if ((j*100+1) > largestFileNum) {
                    break;
                }
                File outZipFile = new File(haoqReZipFilePath+"zip"+(j+1)+".zip");
                if (!outZipFile.getParentFile().exists()) {
                    outZipFile.getParentFile().mkdirs();
                }
                ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(outZipFile));
                for (int i = 1+100*j; i < 101+100*j && i<=largestFileNum; i++) {
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
                System.out.println((j+1)+". "+outZipFile.getName()+"压缩完成，耗时"+(System.currentTimeMillis()-start)+"毫秒，大小"+(outZipFile.length()/1024/1024)+"MB");
            }
            System.out.println("重压缩完成，耗时"+(System.currentTimeMillis()-begin)/(1000)+"秒");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("本机重压缩文件异常！");
        }

    }

    public static void deleteFile() {
        long begin = System.currentTimeMillis();
        for (int i=1; i<=rezipFileCount; i++) {
            // 创建HttpClientBuilder
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
            CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

            HttpDelete httpDelete = new HttpDelete("http://10.2.73.43:8080/filex/rest/file/NT-TEST?pathAndFile=/igtftptest/hotelList/zip"+i+".zip");
            httpDelete.addHeader("Authorization",basicAuth);

            CloseableHttpResponse response = null;
            try {
                // 执行请求
                response = closeableHttpClient.execute(httpDelete);
                // 判断返回状态是否为200
                if (response.getStatusLine().getStatusCode() == 200) {
                    System.out.println("SUCCESS "+i);
                } else {
                    System.out.println(response.getStatusLine().getReasonPhrase()+" "+i);
                }
            } catch(Exception e) {
                e.printStackTrace();
                System.out.println("删除ftp上zip文件异常！");
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
        System.out.println("删除原有zip文件完成，耗时： "+(System.currentTimeMillis()-begin)/1000 +"秒");
    }

    public static void uploadZipFile() {
        long begin = System.currentTimeMillis();
        for (int i=1; i<=rezipFileCount; i++) {

            /* 1.设置basic auth */
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
            CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

            /* 2.设置post参数 */
            HttpPost httpPost = new HttpPost("http://10.2.73.43:8080/filex/rest/file/NT-TEST?path=/igtftptest/hotelList");
            httpPost.addHeader("Authorization",basicAuth);
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();

            CloseableHttpResponse response = null;

            try {
                File file = new File(haoqReZipFilePath+"zip"+i+".zip");
                byte[] bytes;
                HttpEntity httpEntity;
                long start;
                try (FileInputStream fis = new FileInputStream(file)) {
                    Long fileSize = file.length();
                    bytes = new byte[Integer.valueOf(fileSize.toString())];
                    fis.read(bytes);
                    fis.close();
                }
                multipartEntityBuilder.addBinaryBody("file",bytes, ContentType.DEFAULT_BINARY,file.getName());
                httpEntity = multipartEntityBuilder.build();
                httpPost.setEntity(httpEntity);

                start = System.currentTimeMillis();
                response = closeableHttpClient.execute(httpPost);
                // 判断返回状态是否为200
                if (response.getStatusLine().getStatusCode() == 200) {
                    System.out.println(i+". SUCCESS，耗时: " + (System.currentTimeMillis()-start)/1000+"秒");
                } else {
                    System.out.println(i+". "+response.getStatusLine().getReasonPhrase());
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("上传重压缩文件异常！");
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

        }//if
        System.out.println("上传zip文件完成，总耗时: "+(System.currentTimeMillis()-begin)/1000/60+"分钟");
    }

    public static void uploadZipFileWithPool() throws Exception{
        long begin = System.currentTimeMillis();
        ExecutorService pool = Executors.newFixedThreadPool(5);
        CountDownLatch latch = new CountDownLatch(185);

        for (int i=1; i<=185; i++) {
            String fname = "src/main/resources/io/zip/zip"+i+".zip";
            pool.submit(new UploadZipTask(fname,basicAuth,latch));

        }//for
        pool.shutdown();
        latch.await();

        System.out.println("总耗时: "+(System.currentTimeMillis()-begin)/1000/60+"分钟");
    }

    public static void downloadAndUnZipFile() throws Exception{

        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

        HttpGet httpGet = new HttpGet("http://10.2.73.43:8080/filex/rest/file/NT-TEST?pathAndFile=/igtftptest/zip11.zip");
        httpGet.addHeader("Authorization",basicAuth);
        CloseableHttpResponse response = null;
        try {

            response = closeableHttpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 获取服务端返回的数据
                byte[] bytes = EntityUtils.toByteArray(response.getEntity());
                // 服务端返回数据的长度
                System.out.println("内容长度：" + bytes.length);

                //根据字节流解析为zip文件
                ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(bytes));
                BlockingQueue fileNameQueue = new LinkedBlockingDeque();
                ZipEntry zipEn = null;
                AtomicInteger completedTask = new AtomicInteger(0);
                final Integer totalTaskCount;
                ArrayList<TaskEntry> taskList = new ArrayList();

                while ((zipEn = zis.getNextEntry()) != null) {
                    if (!zipEn.isDirectory()) {
                        int fileSize = (int) zipEn.getSize();

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

                        //打印压缩文件名称
                        //System.out.println(zipEn.getName() + ";\t");

                        File outFile = new File ( "src/main/resources/io/tmp/" + zipEn.getName() ) ;

                        //打印文件名称
                        fileNameQueue.offer(outFile.getName());
                        System.out.println(outFile.getName());

                        TaskEntry taskEntry = new TaskEntry();
                        taskEntry.setFileName(zipEn.getName());
                        taskEntry.setFileBytes(b);
                        taskList.add(taskEntry);

                    }//if

                }//while

                zis.close();

                totalTaskCount = taskList.size();

                ExecutorService pool = Executors.newFixedThreadPool(5);
                for (TaskEntry taskEntry : taskList) {
                    pool.submit(new Runnable() {
                        @Override public void run() {
                            try {
                                if (!totalTaskCount.equals(completedTask.get())) {
                                    TimeUnit.SECONDS.sleep(3);
                                    //插入数据
                                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                                    System.out.println("insert data: "+taskEntry.getFileName()+" "+df.format(new Date()));
                                    int i = completedTask.incrementAndGet();
                                    System.out.println(i + "task "+taskEntry.getFileName()+" finished !");
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }

                pool.shutdown();

                System.out.println(JSON.toJSONString(fileNameQueue));


            }
        } finally {
            if (response != null) {
                response.close();
            }
            // 相当于关闭浏览器
            closeableHttpClient.close();
        }

    }

    public static void deleteFileWithPool() throws Exception{
        long begin = System.currentTimeMillis();
        ExecutorService pool = Executors.newFixedThreadPool(5);

        for (int i=1; i<=185; i++) {
            pool.submit(new DeleteZipTask("zip"+i,basicAuth));
        }

        pool.shutdown();

        System.out.println("耗时： "+(System.currentTimeMillis()-begin)/1000/60);
    }

    public static void testZip11() throws Exception{
        ZipInputStream zis = null;
        try {

                // 获取服务端返回的数据
                File file11 = new File("src/main/resources/io/zip/zip11.zip");
                FileInputStream fis = new FileInputStream(file11);

                //根据字节流解析为zip文件
                zis = new ZipInputStream(fis);
                BlockingQueue fileNameQueue = new LinkedBlockingDeque();
                ZipEntry zipEn = null;
                AtomicInteger completedTask = new AtomicInteger(0);
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

                            File outFile = new File ( "src/main/resources/io/tmp/" + zipEn.getName() ) ;
                            fileNameQueue.offer(outFile.getName());
                            System.out.println(outFile.getName());

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

                        //打印压缩文件名称
                        //System.out.println(zipEn.getName() + ";\t");

                        File outFile = new File ( "src/main/resources/io/tmp/" + zipEn.getName() ) ;
                        fileNameQueue.offer(outFile.getName());
                        System.out.println(outFile.getName());

                        TaskEntry taskEntry = new TaskEntry();
                        taskEntry.setFileName(zipEn.getName());
                        taskEntry.setFileBytes(b);
                        taskList.add(taskEntry);

                    }//if

                }//while

                zis.close();

                totalTaskCount = taskList.size();

                ExecutorService pool = Executors.newFixedThreadPool(5);
                for (TaskEntry taskEntry : taskList) {
                    pool.submit(new Runnable() {
                        @Override public void run() {
                            try {
                                if (!totalTaskCount.equals(completedTask.get())) {
                                    TimeUnit.SECONDS.sleep(3);
                                    //插入数据
                                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                                    System.out.println("insert data: "+taskEntry.getFileName()+" "+df.format(new Date()));
                                    int i = completedTask.incrementAndGet();
                                    System.out.println(i + "task "+taskEntry.getFileName()+" finished !");
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }

                pool.shutdown();

                System.out.println(JSON.toJSONString(fileNameQueue));


        } finally {
            zis.close();
        }

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

    public static void listZip() {
        try {
            File file = new File(haoqUnZipFilePath);
            int count = file.list().length;
            int total = 0;
            for (int i=1; i<=18482; i++) {
                File tmp = new File(haoqUnZipFilePath+"hotellist_cityId"+i+".xml");
                if (!tmp.exists()) {
                    System.out.println(i);
                    total++;
                }
            }

            String[] fileNames = file.list();
            for (String fileName : fileNames) {
                System.out.println(fileName);
            }

            System.out.println("none exist:"+total);
            System.out.println("count:"+count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testDos() {
        try {
            File f = new File(haoqReZipFilePath + "1.txt");
            byte[] bytes = String.valueOf(200).getBytes("utf-8");
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(bytes);
            fos.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String count = br.readLine();
            br.close();
            System.out.println(count);

        } catch (Exception e) {
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
            return fileCount;
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

    private static String getHeader(String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("UTF-8")));
        String authHeader = "Basic " + new String(encodedAuth);
        return authHeader;
    }
}
