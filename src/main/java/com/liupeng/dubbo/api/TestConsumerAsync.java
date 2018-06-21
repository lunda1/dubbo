package com.liupeng.dubbo.api;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.RpcContext;
import com.liupeng.dubbo.DemoService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class TestConsumerAsync {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("app-api-consumer");

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("127.0.0.1:2181");
        registryConfig.setProtocol("zookeeper");

        MonitorConfig monitorConfig = new MonitorConfig();
        monitorConfig.setProtocol("registry");
        //        monitorConfig.setDefault(true);
        //        monitorConfig.setAddress("127.0.0.1:8083");

        ReferenceConfig referenceConfig = new ReferenceConfig();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setInterface(DemoService.class);
        referenceConfig.setVersion("1.0.0");
        referenceConfig.setGroup("dubbo1");
        referenceConfig.setMonitor(monitorConfig);

        referenceConfig.setTimeout(3000);
        referenceConfig.setAsync(true);

        DemoService demoService = (DemoService) referenceConfig.get();

        long start = System.currentTimeMillis()/1000;

        System.out.println(demoService.sayHello("哈哈哈"));
        Future<String> f1 = RpcContext.getContext().getFuture();

        System.out.println(demoService.sayHello("哈哈哈2"));
        Future<String> f2 = RpcContext.getContext().getFuture();

        System.out.println(f1.get());
        System.out.println(f2.get());

        long endTime = System.currentTimeMillis()/1000;

        System.out.println("costs: " + (endTime-start));

        Thread.currentThread().join();
    }
}
