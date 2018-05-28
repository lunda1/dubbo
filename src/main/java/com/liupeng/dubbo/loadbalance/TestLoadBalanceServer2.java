package com.liupeng.dubbo.loadbalance;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.TimeUnit.SECONDS;

public class TestLoadBalanceServer2 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(()-> {
            ClassPathXmlApplicationContext remoteContext = new ClassPathXmlApplicationContext("dubbo-config-provider.xml");
            remoteContext.start();
        });
        executorService.submit(()->{
            ClassPathXmlApplicationContext remoteContext2 = new ClassPathXmlApplicationContext("dubbo-config-provider-5s.xml");
            remoteContext2.start();
        });

    }
}
