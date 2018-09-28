package com.liupeng.dubbo.api;

import com.alibaba.dubbo.config.*;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.service.EchoService;
import com.alibaba.fastjson.JSON;
import com.liupeng.dubbo.DemoService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class TestApiConsumer {
    public static void main(String[] args) throws InterruptedException {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("app-api-consumer");

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("127.0.0.1:2181");
        registryConfig.setProtocol("zookeeper");

//        MonitorConfig monitorConfig = new MonitorConfig();
//        monitorConfig.setProtocol("registry");
//        monitorConfig.setDefault(true);
//        monitorConfig.setAddress("127.0.0.1:8083");

        ReferenceConfig referenceConfig = new ReferenceConfig();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setInterface(DemoService.class);
        referenceConfig.setVersion("1.0.0");
        referenceConfig.setGroup("dubbo1");
        //mergeable
//        referenceConfig.setGroup("dubbo1,dubbo2");
//        referenceConfig.setMerger("true");
//        referenceConfig.setMerger("myMerger");

//        referenceConfig.setMonitor(monitorConfig);
        referenceConfig.setTimeout(9000);
        //referenceConfig.setMock("com.liupeng.dubbo.api.MyMock");
//        referenceConfig.setMock("return null");
        //referenceConfig.setStub("com.liupeng.dubbo.api.MyStub");
        //referenceConfig.setFilter("-myFilter");

        /*referenceConfig.setLoadbalance("consistenthash");
        Map<String,String> parameters = new HashMap<>();
        parameters.put("hash.arguments","0");
        parameters.put("hash.nodes","200");
        referenceConfig.setParameters(parameters);*/

//        referenceConfig.setUrl("dubbo://localhost:20880");

        //validation
        //referenceConfig.setValidation("true");


        //cache
        //referenceConfig.setCache("lru");

        //async
        //referenceConfig.setAsync(true);

        referenceConfig.setActives(5);

        DemoService demoService = (DemoService) referenceConfig.get();
        System.out.println(demoService.sayHello("123"));
//        demoService.sayHello("123");
//        Future<String> future = RpcContext.getContext().getFuture();
//
//        try {
//            System.out.println(future.get());
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        //echo
        //EchoService echoService = (EchoService) referenceConfig.get();
        //System.out.println(echoService.$echo("OK!"));

        //设置RpcContext
        //RpcContext.getContext().setAttachment("testkey","2222");
        //System.out.println(JSON.toJSONString(RpcContext.getContext()));


        //normal
        //System.out.println(demoService.sayHello("111"));

        //cache
        //System.out.println(demoService.listInt(Arrays.asList(5,6,7)));
        //System.out.println(demoService.listInt(Arrays.asList(5,6,7)));


        //validation
        /*MyParameter myParameter = new MyParameter();
        myParameter.setName("123");
        myParameter.setEmail("1163.com");
        myParameter.setAge(20);
        try {
            System.out.println(demoService.testValidation(myParameter));
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        Thread.currentThread().join();

    }
}
