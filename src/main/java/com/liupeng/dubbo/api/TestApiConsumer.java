package com.liupeng.dubbo.api;

import com.alibaba.dubbo.config.*;
import com.liupeng.dubbo.DemoService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestApiConsumer {
    public static void main(String[] args) throws InterruptedException {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("app-api-consumer");

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("127.0.0.1:2181");
        registryConfig.setProtocol("zookeeper");

        ProtocolConfig protocolConfig = new ProtocolConfig();
//        protocolConfig.setName("hessian");
//        protocolConfig.setPort(8081);
//        protocolConfig.setName("rmi");
//        protocolConfig.setPort(1099);

//        MonitorConfig monitorConfig = new MonitorConfig();
//        monitorConfig.setProtocol("registry");
//        monitorConfig.setDefault(true);
//        monitorConfig.setAddress("127.0.0.1:8083");

        ReferenceConfig referenceConfig = new ReferenceConfig();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setInterface(DemoService.class);
        referenceConfig.setVersion("1.0.0");

        //referenceConfig.setGroup("dubbo1");
        referenceConfig.setGroup("dubbo1,dubbo2");
        referenceConfig.setMerger("myMerger");

//        referenceConfig.setMonitor(monitorConfig);
        referenceConfig.setTimeout(3000);
//        referenceConfig.setMock("com.liupeng.dubbo.api.MyMock");
        //referenceConfig.setMock("return null");
        //referenceConfig.setStub("com.liupeng.dubbo.api.MyStub");
        //referenceConfig.setFilter("-myFilter");

        /*referenceConfig.setLoadbalance("consistenthash");
        Map<String,String> parameters = new HashMap<>();
        parameters.put("hash.arguments","0");
        parameters.put("hash.nodes","200");
        referenceConfig.setParameters(parameters);*/

//        referenceConfig.setUrl("dubbo://localhost:20880");

        DemoService demoService = (DemoService) referenceConfig.get();
        //System.out.println(demoService.sayHello("111"));
        System.out.println(demoService.listInt(Arrays.asList(3,2,3)));

        Thread.currentThread().join();

    }
}
