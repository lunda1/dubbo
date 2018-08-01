package com.liupeng.dubbo.example;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            new String[]{"example/dubbo-config-consumer.xml"});
        context.start();

        DemoService demoService = (DemoService) context.getBean("demoService");
        for (int i=0;i<10;i++) {
            String hello = demoService.sayHello(i+"world");
            System.out.println(i+" "+hello);
        }
        Thread.currentThread().join();
    }
}
