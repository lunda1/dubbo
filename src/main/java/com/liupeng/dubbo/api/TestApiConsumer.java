package com.liupeng.dubbo.api;

import com.alibaba.dubbo.config.*;
import com.liupeng.dubbo.DemoService;

public class TestApiConsumer {
    public static void main(String[] args) throws InterruptedException {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("app-api-consumer");

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("127.0.0.1:2181");
        registryConfig.setProtocol("zookeeper");

        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("rmi");
        protocolConfig.setPort(1099);

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
//        referenceConfig.setMonitor(monitorConfig);
        referenceConfig.setTimeout(3000);


        DemoService demoService = (DemoService) referenceConfig.get();
        System.out.println(demoService.sayHello("111"));

        Thread.currentThread().join();

    }
}
