package com.liupeng.dubbo.example;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider3 {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
            "example/dubbo-config-provider-3s.xml"});
        context.start();
        System.out.println("start");
        // press any key to exit
        System.in.read();
    }
}
