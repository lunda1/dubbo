package com.liupeng.dubbo.api;

import com.alibaba.dubbo.config.*;
import com.liupeng.dubbo.DemoService;
import com.liupeng.dubbo.DemoServiceImpl;

public class TestApiProvider {
    public static void main(String[] args) throws InterruptedException {
        DemoService demoService = new DemoServiceImpl();

        ApplicationConfig application = new ApplicationConfig();
        application.setName("app-api-provider");

        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("127.0.0.1:2181");
        registry.setProtocol("zookeeper");

        ProtocolConfig protocol = new ProtocolConfig();
        /*protocol.setName("rmi");
        protocol.setPort(1099);*/

        protocol.setName("dubbo");
        protocol.setPort(20880);

//        MonitorConfig monitorConfig = new MonitorConfig();
//        monitorConfig.setProtocol("registry");
//        monitorConfig.setDefault(true);
//        monitorConfig.setAddress("127.0.0.1:8083");

        ServiceConfig<DemoService> service = new ServiceConfig<>();
        service.setApplication(application);
        service.setRegistry(registry);
        service.setProtocol(protocol);
        service.setInterface(DemoService.class);
        service.setRef(demoService);
        service.setVersion("1.0.0");
        service.setGroup("dubbo1");
//        service.setMonitor(monitorConfig);

        service.export();

        Thread.currentThread().join();

    }
}
