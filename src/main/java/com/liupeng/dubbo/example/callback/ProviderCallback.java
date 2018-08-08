package com.liupeng.dubbo.example.callback;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProviderCallback {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
            "example/callback/dubbo-config-provider-callback.xml"});
        context.start();
        System.out.println("start");
        // press any key to exit
        System.in.read();
    }
}
