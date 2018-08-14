package com.liupeng.dubbo.example;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.service.EchoService;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.fastjson.JSON;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Consumer {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            new String[]{"example/dubbo-config-consumer.xml"});
        context.start();
        DemoService demoService = (DemoService) context.getBean("demoService");

        /*test sayHello method*/
        for (int i=0;i<10;i++) {
            String hello = demoService.sayHello(i+"world");
            System.out.println(i+" "+hello);
        }

        /*test list method*/
        /*for (int i=0;i<10;i++) {
            System.out.println("-------"+ JSON.toJSONString(demoService.list()));
        }*/

        /*test timestamp method*/
        /*for (int i=0;i<10;i++) {
            System.out.println("-------"+ JSON.toJSONString(demoService.timestamp()));
            SECONDS.sleep(1);
        }*/

        /*generic reference*/
        /*GenericService genericService = (GenericService) context.getBean("demoService");
        System.out.println(genericService.$invoke("sayHello",new String[]{"java.lang.String"},new Object[]{"wwwworld!"}));*/

        /*generic implement*/
        /*DemoService genericDemoService = (DemoService) context.getBean("genericService");
        System.out.println(genericDemoService.sayHello("hahaha!"));*/

        /*echo service*/
        /*EchoService echoService = (EchoService) demoService;
        System.out.println(echoService.$echo("444444"));*/

        /*RpcContext*/
        /*demoService.sayHello("hihihi!");
        RpcContext.getContext().setAttachment("index","11");
        System.out.println(JSON.toJSONString(RpcContext.getContext()));
        demoService.sayHello("hihihi!");
        System.out.println(JSON.toJSONString(RpcContext.getContext()));*/

        Thread.currentThread().join();

    }
}
