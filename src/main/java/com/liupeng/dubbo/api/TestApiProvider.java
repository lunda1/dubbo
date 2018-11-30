package com.liupeng.dubbo.api;

import com.alibaba.dubbo.config.*;
import com.liupeng.dubbo.DemoService;
import com.liupeng.dubbo.DemoServiceImpl;
import com.liupeng.dubbo.example.callback.Notify;
import com.liupeng.dubbo.example.callback.NotifyImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestApiProvider {
    public static void main(String[] args) throws InterruptedException {
        DemoService demoService = new DemoServiceImpl();

        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("app-api-provider");
        applicationConfig.setLogger("log4j");

        applicationConfig.setDumpDirectory("d://app-api-provider-dump.log");

        //qos
//        applicationConfig.setQosEnable(true);

        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("127.0.0.1:2181|127.0.0.1:2182");
//        registry.setAddress("127.0.0.1:2181");
        //registry.setAddress("127.0.0.1:2181?dynamic=false");
//        registry.setAddress("127.0.0.1:2181?subscribe=false");
        registry.setProtocol("zookeeper");

        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(20880);

//        ProtocolConfig protocol2 = new ProtocolConfig();
//        protocol2.setName("rmi");
//        protocol2.setPort(1099);

        List<ProtocolConfig> protocolConfigList = new ArrayList<>();
        protocolConfigList.add(protocol);
//        protocolConfigList.add(protocol2);

        //因为配置了log4j，所以默认会将访问日志记录到log4j配置的文件中
//        protocol.setAccesslog("true");
        //因为配置访问日志文件，所以访问日志会打印到相应的文件中
//        protocol.setAccesslog("d://app-api-provider.log");

//        protocol.setName("hessian");
//        protocol.setPort(8082);

//        MonitorConfig monitorConfig = new MonitorConfig();
//        monitorConfig.setProtocol("registry");
//        monitorConfig.setDefault(true);
//        monitorConfig.setAddress("127.0.0.1:8083");

        ServiceConfig<DemoService> service = new ServiceConfig<>();
        service.setApplication(applicationConfig);
        service.setRegistry(registry);
        //service.setProtocol(protocol);
        service.setProtocols(protocolConfigList);
        service.setInterface(DemoService.class);
        service.setRef(demoService);
        service.setVersion("1.0.0");
        service.setGroup("dubbo1");
//        service.setMonitor(monitorConfig);
//        service.setStub("com.liupeng.dubbo.api.MyStub");
        service.setDelay(5000);
//        service.setToken(true);
        service.setAccesslog(true);
//        service.setAccesslog("D:\\app-api-provider.log");


        Map<String,String> params = new HashMap<>();
        params.put("accepts","3");
        service.setParameters(params);

        service.export();

        Thread.currentThread().join();

    }
}
