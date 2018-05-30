package com.liupeng.learning.io.http;

import com.alibaba.fastjson.JSON;
import org.apache.commons.net.util.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class TestHttp {

    public static String username = "filex-test";
    public static String password = "123456";
    public static String basicAuth = getHeader(username,password);

    public static void main(String[] args) throws Exception {
        //1.下载txt文件
        //downloadTxtFile();
        //2.下载zip文件
        //downloadZipFile();
        //3.下载并解压生成xml文件
        //downloadAndUnzipFile();
        //4.上传文件
        //uploadFile();
        //5.上传文件
        //uploadFileByDirectlySetAuth();
        //6.下载zip文件
        downloadZipFileByDirectlySetAuth();
    }

    public static void uploadFile() throws Exception{


        /* 1.设置basic auth */

        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // 设置BasicAuth
        CredentialsProvider provider = new BasicCredentialsProvider();
        // Create the authentication scope
        AuthScope scope = new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM);
        // Create credential pair，在此处填写用户名和密码
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("filex-test", "123456");
        // Inject the credentials
        provider.setCredentials(scope, credentials);
        // Set the default credentials provider
        httpClientBuilder.setDefaultCredentialsProvider(provider);
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

        //end 设置basic auth


        /* 2.设置post参数 */
        //RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000000).build();
        HttpPost httpPost = new HttpPost("http://10.2.73.43:8080/filex/rest/file/NT-TEST?path=/igtftptest/cityList");
        //httpPost.setConfig(requestConfig);
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();

        File file = new File("src/main/resources/io/tmp/HAOQIAO_test_static_xml/cityList/cityList.xml");
        FileInputStream fis = new FileInputStream(file);
        Long fileSize = file.length();
        byte[] bytes = new byte[Integer.valueOf(fileSize.toString())];
        fis.read(bytes);
        fis.close();
        multipartEntityBuilder.addBinaryBody("file",file);
//        multipartEntityBuilder.addBinaryBody("file",bytes);
        //multipartEntityBuilder.addTextBody("comment", "this is liup comment");
        HttpEntity httpEntity = multipartEntityBuilder.build();
        httpPost.setEntity(httpEntity);

        CloseableHttpResponse response = null;
        try {

            response = closeableHttpClient.execute(httpPost);
            // 判断返回状态是否为200
            System.out.println(response.getStatusLine().getReasonPhrase());
            if (response.getStatusLine().getStatusCode() == 200) {
                System.out.println("SUCCESS");
            }
        } finally {
            if (response != null) {
                response.close();
            }
            // 相当于关闭浏览器
            closeableHttpClient.close();
        }

    }

    public static void uploadFileByDirectlySetAuth() throws Exception{


        /* 1.设置basic auth */
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

        /* 2.设置post参数 */
        HttpPost httpPost = new HttpPost("http://10.2.73.43:8080/filex/rest/file/NT-TEST?path=/igtftptest/cityList");
        httpPost.addHeader("Authorization",basicAuth);
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();

        File file = new File("src/main/resources/io/tmp/HAOQIAO_test_static_xml/cityList/cityList.xml");
        FileInputStream fis = new FileInputStream(file);
        Long fileSize = file.length();
        byte[] bytes = new byte[Integer.valueOf(fileSize.toString())];
        fis.read(bytes);
        fis.close();
        //multipartEntityBuilder.addBinaryBody("file",file);
        multipartEntityBuilder.addBinaryBody("file",bytes, ContentType.DEFAULT_BINARY,file.getName());
        //multipartEntityBuilder.addTextBody("comment", "this is liup comment");
        HttpEntity httpEntity = multipartEntityBuilder.build();
        httpPost.setEntity(httpEntity);

        CloseableHttpResponse response = null;
        try {

            response = closeableHttpClient.execute(httpPost);
            // 判断返回状态是否为200
            System.out.println(response.getStatusLine().getReasonPhrase());
            if (response.getStatusLine().getStatusCode() == 200) {
                System.out.println("SUCCESS");
            }
        } finally {
            if (response != null) {
                response.close();
            }
            // 相当于关闭浏览器
            closeableHttpClient.close();
        }

    }

    public static void downloadAndUnzipFile() throws Exception{

        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // 设置BasicAuth
        CredentialsProvider provider = new BasicCredentialsProvider();
        // Create the authentication scope
        AuthScope scope = new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM);
        // Create credential pair，在此处填写用户名和密码
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("filex-test", "123456");
        // Inject the credentials
        provider.setCredentials(scope, credentials);
        // Set the default credentials provider
        httpClientBuilder.setDefaultCredentialsProvider(provider);
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.setDefaultCredentialsProvider(provider).build();


        HttpGet httpGet = new HttpGet("http://10.2.73.43:8080/filex/rest/file/NT-TEST?pathAndFile=/igtftptest/HAOQIAO_test_static_xml.zip");
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
                BlockingQueue queue = new LinkedBlockingDeque();
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
                        if (!outFile.getParentFile().exists()){
                            outFile.getParentFile().mkdirs();
                        }
                        if (!outFile.exists()) {
                            outFile.createNewFile();
                        }

                        //打印文件名称
                        queue.offer(outFile.getName());
                        System.out.println(outFile.getName());

                        BufferedOutputStream bos = null;
                        bos = new BufferedOutputStream(new FileOutputStream(outFile));
                        bos.write(b);
                        bos.close();
                    }//if

                }//while

                zis.close();

                System.out.println(JSON.toJSONString(queue));

            }
        } finally {
            if (response != null) {
                response.close();
            }
            // 相当于关闭浏览器
            closeableHttpClient.close();
        }

    }

    public static void downloadTxtFile() throws Exception{

        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // 设置BasicAuth
        CredentialsProvider provider = new BasicCredentialsProvider();
        // Create the authentication scope
        AuthScope scope = new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM);
        // Create credential pair，在此处填写用户名和密码
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("filex-test", "123456");
        // Inject the credentials
        provider.setCredentials(scope, credentials);
        // Set the default credentials provider
        httpClientBuilder.setDefaultCredentialsProvider(provider);
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();


        HttpGet httpGet = new HttpGet("http://10.2.73.43:8080/filex/rest/file/NT-TEST?pathAndFile=/igtftptest/111.txt");
        CloseableHttpResponse response = null;
        try {
            // 执行请求

            response = closeableHttpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 获取服务端返回的数据
                //String content = EntityUtils.toString(response.getEntity(),"UTF-8");

                byte[] bytes = EntityUtils.toByteArray(response.getEntity());
                File file = new File("src/main/resources/io/111.txt");
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                bos.write(bytes);
                bos.close();

                /*FileUtils.writeStringToFile(new File("E:\\111.txt"),
                    content, "UTF-8");*/
                // 服务端返回数据的长度
                System.out.println("内容长度：" + bytes.length);
            }
        } finally {
            if (response != null) {
                response.close();
            }
            // 相当于关闭浏览器
            closeableHttpClient.close();
        }

    }

    public static void downloadZipFile() throws Exception{

        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // 设置BasicAuth
        CredentialsProvider provider = new BasicCredentialsProvider();
        // Create the authentication scope
        AuthScope scope = new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM);
        // Create credential pair，在此处填写用户名和密码
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("filex-test", "123456");
        // Inject the credentials
        provider.setCredentials(scope, credentials);
        // Set the default credentials provider
        httpClientBuilder.setDefaultCredentialsProvider(provider);
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

        HttpGet httpGet = new HttpGet("http://10.2.73.43:8080/filex/rest/file/NT-TEST?pathAndFile=/igtftptest/HAOQIAO_test_static_xml.zip");
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = closeableHttpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 获取服务端返回的数据
                //String content = EntityUtils.toString(response.getEntity(),"UTF-8");

                byte[] bytes = EntityUtils.toByteArray(response.getEntity());
                File file = new File("src/main/resources/io/HAOQIAO_test_static_xml1.zip");
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                bos.write(bytes);
                bos.close();

                /*FileUtils.writeStringToFile(new File("E:\\111.txt"),
                    content, "UTF-8");*/
                // 服务端返回数据的长度
                System.out.println("内容长度：" + bytes.length);
            }
        } finally {
            if (response != null) {
                response.close();
            }
            // 相当于关闭浏览器
            closeableHttpClient.close();
        }

    }

    public static void downloadZipFileByDirectlySetAuth() throws Exception{

        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

        HttpGet httpGet = new HttpGet("http://10.2.73.43:8080/filex/rest/file/NT-TEST?pathAndFile=/igtftptest/HAOQIAO_test_static_xml.zip");
        httpGet.addHeader("Authorization",basicAuth);

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = closeableHttpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 获取服务端返回的数据
                //String content = EntityUtils.toString(response.getEntity(),"UTF-8");

                byte[] bytes = EntityUtils.toByteArray(response.getEntity());
                File file = new File("src/main/resources/io/HAOQIAO_test_static_xml1.zip");
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                bos.write(bytes);
                bos.close();

                /*FileUtils.writeStringToFile(new File("E:\\111.txt"),
                    content, "UTF-8");*/
                // 服务端返回数据的长度
                System.out.println("内容长度：" + bytes.length);
                System.out.println("SUCCESS");
            }
        } finally {
            if (response != null) {
                response.close();
            }
            // 相当于关闭浏览器
            closeableHttpClient.close();
        }

    }

    private static String getHeader(String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("UTF-8")));
        String authHeader = "Basic " + new String(encodedAuth);
        return authHeader;
    }
}
