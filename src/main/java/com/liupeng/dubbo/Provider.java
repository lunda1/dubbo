package com.liupeng.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"dubbo-config-provider.xml"});
        context.start();
        System.out.println("start");
        // press any key to exit
        System.in.read();
    }
}
