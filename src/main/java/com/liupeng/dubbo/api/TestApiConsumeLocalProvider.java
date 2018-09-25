package com.liupeng.dubbo.api;

import com.alibaba.dubbo.config.*;
import com.liupeng.dubbo.DemoService;
import com.liupeng.dubbo.DemoServiceImpl;

public class TestApiConsumeLocalProvider {
    public static void main(String[] args) throws InterruptedException {
        DemoService demoService = new DemoServiceImpl();

        ApplicationConfig application = new ApplicationConfig();
        application.setName("app-api-provider");

        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("127.0.0.1:2181");
        registry.setProtocol("zookeeper");

        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(20880);

        ServiceConfig<DemoService> service = new ServiceConfig<>();
        service.setApplication(application);
        service.setRegistry(registry);
        service.setProtocol(protocol);
        service.setInterface(DemoService.class);
        service.setRef(demoService);
        service.setVersion("1.0.0");
        service.setGroup("dubbo1");
        service.export();

        System.out.println("---export---");

        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("app-api-consumer");

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("127.0.0.1:2181");
        registryConfig.setProtocol("zookeeper");

        ReferenceConfig referenceConfig = new ReferenceConfig();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setInterface(DemoService.class);
        referenceConfig.setVersion("1.0.0");
        referenceConfig.setGroup("dubbo1");
        referenceConfig.setScope("local");
        referenceConfig.setTimeout(9000);

        DemoService demoServiceConsumer = (DemoService) referenceConfig.get();
        System.out.println(demoServiceConsumer.sayHello("123"));

        Thread.currentThread().join();

    }
}
