package com.liupeng.learning.haoqjob;

import com.alibaba.fastjson.JSON;
import com.liupeng.learning.xml.jaxb.HaoQCityResponse;
import com.liupeng.learning.xml.jaxb.HaoQCountryResponse;
import com.liupeng.learning.xml.jaxb.HaoQHotelResponse;
import org.apache.commons.net.util.Base64;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class TestHaoQJob {


    public static String username = "filex-test";
    public static String password = "123456";
    public static String basicAuth = getHeader(username,password);

    public static void main(String[] args) {
        execute();
    }

    public static void execute() {

        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

        HttpGet httpGet = new HttpGet("http://10.2.73.43:8080/filex/rest/file/NT-TEST?pathAndFile=/igtftptest/HAOQIAO_test_static_xml.zip");
        httpGet.addHeader("Authorization",basicAuth);
        CloseableHttpResponse response = null;
        try {

            response = closeableHttpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 获取服务端返回的数据
                byte[] bytes = EntityUtils.toByteArray(response.getEntity());
                // 服务端返回数据的长度
                System.out.println("ZIP文件内容字节长度：" + bytes.length);

                AtomicInteger completedTask = new AtomicInteger(0);

                //生成任务列表
                ArrayList<TaskEntry> taskList = generateTaskList(bytes);

                //执行任务列表
                executeTaskList(taskList, completedTask);

            } else {
                System.out.println("FTP下载基础数据ZIP文件失败!");
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 相当于关闭浏览器
            if (closeableHttpClient != null) {
                try {
                    closeableHttpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    private static ArrayList<TaskEntry> generateTaskList(byte[] bytes) throws IOException {
        ArrayList<TaskEntry> taskList = new ArrayList();
        ArrayList<String> fileNameList = new ArrayList<String>();
        ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(bytes));
        ZipEntry zipEn = null;
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
                fileNameList.add(outFile.getName());

                TaskEntry taskEntry = new TaskEntry();
                taskEntry.setFileName(outFile.getName());
                taskEntry.setFileBytes(b);
                taskList.add(taskEntry);

            }//if

        }//while

        zis.close();

        System.out.println("文件名称列表: "+JSON.toJSONString(fileNameList));
        System.out.println("文件列表数量: "+fileNameList.size());

        return taskList;
    }

    private static void executeTaskList(ArrayList<TaskEntry> taskList,AtomicInteger completedTask) {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (TaskEntry taskEntry : taskList) {
            pool.submit(new Runnable() {
                @Override public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                        //插入数据
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                        int i = completedTask.incrementAndGet();
                        System.out.println("task "+i+" insert into redis "+taskEntry.getFileName()+" "+df.format(new Date()));


                        System.out.println("task "+i+" insert into mysql "+taskEntry.getFileName()+" "+df.format(new Date()));
                        batchUpdateDBBasicData(taskEntry);

                        System.out.println("task "+i+" sync "+taskEntry.getFileName()+" finished !");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        pool.shutdown();
    }

    private static void batchUpdateDBBasicData(TaskEntry taskEntry) {
        BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(taskEntry.getFileBytes())));
        StringBuilder sb = new StringBuilder();
        String str = null;
        try{
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Integer count = 0;
        if (taskEntry.getFileName().startsWith(HaoQBasicDataFileNameEnum.HOTEL.getFileName())) {
            HaoQHotelResponse haoQHotelResponse = converyToJavaBean(sb.toString(),HaoQHotelResponse.class);
            count = haoQHotelResponse.getHotelList().size();
        } else if (taskEntry.getFileName().startsWith(HaoQBasicDataFileNameEnum.COUNTRY.getFileName())) {
            HaoQCountryResponse haoQCountryResponse = converyToJavaBean(sb.toString(), HaoQCountryResponse.class);
            count = haoQCountryResponse.getCountryList().size();
        } else if (taskEntry.getFileName().startsWith(HaoQBasicDataFileNameEnum.CITY.getFileName())) {
            HaoQCityResponse haoQCityResponse = converyToJavaBean(sb.toString(),HaoQCityResponse.class);
            count = haoQCityResponse.getCityList().size();
        }

        System.out.println(taskEntry.getFileName()+" "+count);
    }

    public static <T> T converyToJavaBean(String xml, Class<T> c) {
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    private static String getHeader(String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("UTF-8")));
        String authHeader = "Basic " + new String(encodedAuth);
        return authHeader;
    }
}
