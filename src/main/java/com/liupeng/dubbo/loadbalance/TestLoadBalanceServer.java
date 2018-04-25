package com.liupeng.dubbo.loadbalance;

import com.alibaba.fastjson.JSON;
import com.liupeng.dubbo.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.TimeUnit.SECONDS;

public class TestLoadBalanceServer {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(()-> {
            ClassPathXmlApplicationContext remoteContext = new ClassPathXmlApplicationContext("dubbo-config-provider.xml");
            remoteContext.start();
        });
        executorService.submit(()->{
            try {
                SECONDS.sleep(2);
                ClassPathXmlApplicationContext remoteContext2 = new ClassPathXmlApplicationContext("dubbo-config-provider-5s.xml");
                remoteContext2.start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }
}
