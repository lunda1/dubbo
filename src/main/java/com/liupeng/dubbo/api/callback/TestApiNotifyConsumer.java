package com.liupeng.dubbo.api.callback;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.MethodConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.liupeng.dubbo.DemoService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestApiNotifyConsumer {
    public static void main(String[] args) throws InterruptedException {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("app-api-notify-consumer");

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("127.0.0.1:2181;127.0.0.1:2182");
        registryConfig.setProtocol("zookeeper");

        ReferenceConfig referenceConfig = new ReferenceConfig();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setProtocol("dubbo");
        referenceConfig.setInterface(DemoService.class);
        referenceConfig.setVersion("1.0.0");
        referenceConfig.setGroup("dubbo1");
        referenceConfig.setTimeout(9000);
        //async
        referenceConfig.setAsync(true);
        referenceConfig.setActives(5);
        referenceConfig.setConnections(2);
        Map<String,String> parameters = new HashMap<>();
        parameters.put("router","condition");
        referenceConfig.setParameters(parameters);

        List<MethodConfig> methods = new ArrayList<>();
        MethodConfig methodConfig = new MethodConfig();
        methodConfig.setName("sayHello");
        Notify notify = new NotifyImpl();
        methodConfig.setOnreturn(notify);
        methodConfig.setOnreturnMethod("onreturn");
        methods.add(methodConfig);
        referenceConfig.setMethods(methods);


        DemoService demoService = (DemoService) referenceConfig.get();
        System.out.println(demoService.sayHello("123"));

//        try {
//            System.out.println(RpcContext.getContext().getFuture().get());
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        Thread.currentThread().join();

    }
}
