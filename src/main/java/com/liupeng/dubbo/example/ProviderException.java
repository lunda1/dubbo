package com.liupeng.dubbo.example;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProviderException {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
            "example/dubbo-config-provider-exception.xml"});
        context.start();
        System.out.println("start");
        // press any key to exit
        System.in.read();
    }
}
