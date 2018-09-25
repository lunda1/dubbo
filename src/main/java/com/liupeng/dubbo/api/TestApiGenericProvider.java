package com.liupeng.dubbo.api;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.liupeng.dubbo.DemoService;
import com.liupeng.dubbo.DemoServiceImpl;
import com.liupeng.dubbo.example.MyGenericService;

public class TestApiGenericProvider {
    public static void main(String[] args) throws InterruptedException {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("app-api-generic-provider");

        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("127.0.0.1:2181");
        registry.setProtocol("zookeeper");

        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(20880);


        ServiceConfig<GenericService> serviceConfig = new ServiceConfig<GenericService>();
        GenericService service = new MyGenericService();
        serviceConfig.setApplication(application);
        serviceConfig.setRegistry(registry);
        serviceConfig.setProtocol(protocol);
        serviceConfig.setInterface("com.liupeng.dubbo.DemoService");
        serviceConfig.setRef(service);
        serviceConfig.setVersion("1.0.0");
        serviceConfig.setGroup("dubbo1");

        serviceConfig.export();

        Thread.currentThread().join();

    }
}
