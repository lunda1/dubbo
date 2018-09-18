package com.liupeng.dubbo.api;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;

public class TestApiGenericConsumer {
    public static void main(String[] args) throws InterruptedException {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("app-api-generic-consumer");

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("127.0.0.1:2181");
        registryConfig.setProtocol("zookeeper");

        ProtocolConfig protocolConfig = new ProtocolConfig();

        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<GenericService>();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setInterface("com.liupeng.dubbo.DemoService");
        referenceConfig.setVersion("1.0.0");
        referenceConfig.setGeneric(true);

        referenceConfig.setGroup("dubbo1");
        referenceConfig.setTimeout(9000);

        GenericService genericService = referenceConfig.get();

        System.out.println(genericService.$invoke("sayHello",new String[]{"java.lang.String"},new Object[]{"world"}));

        Thread.currentThread().join();

    }
}
