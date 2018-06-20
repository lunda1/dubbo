package com.liupeng.learning.io.zip;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class UploadZipTask implements Runnable {
    private String fileName;
    private String basicAuth;
    private CountDownLatch latch;

    UploadZipTask(String fileName, String basicAuth, CountDownLatch latch){
        this.fileName = fileName;
        this.basicAuth = basicAuth;
        this.latch = latch;
    }

    @Override
    public void run() {

        CloseableHttpResponse response = null;

        /* 1.设置basic auth */
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

        /* 2.设置post参数 */
        HttpPost httpPost = new HttpPost("http://10.2.73.43:8080/filex/rest/file/NT-TEST?path=/igtftptest/hotelList");
        httpPost.addHeader("Authorization", basicAuth);
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();

        try{
            File file = new File(fileName);
            byte[] bytes;
            HttpEntity httpEntity;
            long start;
            try (FileInputStream fis = new FileInputStream(file)) {
                Long fileSize = file.length();
                bytes = new byte[Integer.valueOf(fileSize.toString())];
                fis.read(bytes);
                fis.close();
            }
            multipartEntityBuilder.addBinaryBody("file", bytes, ContentType.DEFAULT_BINARY, file.getName());
            httpEntity = multipartEntityBuilder.build();
            httpPost.setEntity(httpEntity);

            start = System.currentTimeMillis();
            response = closeableHttpClient.execute(httpPost);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                System.out.println(file.getName() + ". SUCCESS，耗时: " + (System.currentTimeMillis() - start) / 1000 + "秒");
            } else {
                System.out.println(file.getName() + ". " + response.getStatusLine().getReasonPhrase());
            }
            latch.countDown();
        } catch (IOException e) {
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
}
