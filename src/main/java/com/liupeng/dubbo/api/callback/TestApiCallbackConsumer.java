package com.liupeng.dubbo.api.callback;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;

public class TestApiCallbackConsumer {
    public static void main(String[] args) throws InterruptedException {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("app-api-callback-consumer");

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("127.0.0.1:2181");
        registryConfig.setProtocol("zookeeper");

        ReferenceConfig referenceConfig = new ReferenceConfig();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setInterface(CallbackService.class);
        referenceConfig.setVersion("1.0.0");
        referenceConfig.setGroup("dubbo1");
        referenceConfig.setTimeout(9000);

        CallbackService callbackService = (CallbackService) referenceConfig.get();
        System.out.println("consumer: "+callbackService.addListener("123", new CallbackListener(){
            @Override public void changed(String msg) {
                System.out.println("------------------------");
                System.out.println(msg);
                System.out.println("------------------------");
            }
        }));


        Thread.currentThread().join();

    }
}
