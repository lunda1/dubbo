package com.liupeng.learning.io.zip;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class DeleteZipTask implements Runnable {
    private String fileName;

    private String basicAuth;

    DeleteZipTask(String fileName, String basicAuth){
        this.fileName = fileName;
        this.basicAuth = basicAuth;
    }

    @Override
    public void run() {

        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

        HttpDelete httpDelete = new HttpDelete("http://10.2.73.43:8080/filex/rest/file/NT-TEST?pathAndFile=/igtftptest/hotelList/"+fileName+".zip");
        httpDelete.addHeader("Authorization",basicAuth);

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = closeableHttpClient.execute(httpDelete);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                System.out.println("SUCCESS "+fileName);
            } else {
                System.out.println(response.getStatusLine().getReasonPhrase()+" "+fileName);
            }
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
