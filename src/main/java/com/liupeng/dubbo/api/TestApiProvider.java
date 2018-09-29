package com.liupeng.dubbo.api;

import com.alibaba.dubbo.config.*;
import com.liupeng.dubbo.DemoService;
import com.liupeng.dubbo.DemoServiceImpl;
import com.liupeng.dubbo.example.callback.Notify;
import com.liupeng.dubbo.example.callback.NotifyImpl;

import java.util.HashMap;
import java.util.Map;

public class TestApiProvider {
    public static void main(String[] args) throws InterruptedException {
        DemoService demoService = new DemoServiceImpl();
        Notify notify = new NotifyImpl();

        ApplicationConfig application = new ApplicationConfig();
        application.setName("app-api-provider");

        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("127.0.0.1:2181");
        //registry.setAddress("127.0.0.1:2181?dynamic=false");
        registry.setProtocol("zookeeper");

        ProtocolConfig protocol = new ProtocolConfig();
//        protocol.setName("rmi");
//        protocol.setPort(1099);

        protocol.setName("dubbo");
        protocol.setPort(20880);


//        protocol.setName("hessian");
//        protocol.setPort(8082);

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
//        service.setStub(true);
        service.setDelay(5000);
        service.setToken(true);

        Map<String,String> params = new HashMap<>();
        params.put("accepts","3");
        service.setParameters(params);

        service.export();

        Thread.currentThread().join();

    }
}
