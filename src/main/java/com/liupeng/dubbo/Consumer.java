package com.liupeng.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Consumer {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            new String[]{"dubbo-config-consumer.xml"});
        context.start();
        // obtain proxy object for remote invocation
        DemoService demoService = (DemoService) context.getBean("demoService");
        // execute remote invocation
        String hello = demoService.sayHello("world");
        // show the result
        System.out.println(hello);
        SECONDS.sleep(10);
    }
}
