package com.liupeng.dubbo.api.callback;

import com.alibaba.dubbo.config.*;

public class TestApiCallbackProvider {
    public static void main(String[] args) throws InterruptedException {
        CallbackService callbackService = new CallbackServiceImpl();

        ApplicationConfig application = new ApplicationConfig();
        application.setName("app-api-callback-provider");

        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("127.0.0.1:2181");
        registry.setProtocol("zookeeper");

        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(20880);

        ServiceConfig<CallbackService> service = new ServiceConfig<>();
        service.setApplication(application);
        service.setRegistry(registry);
        service.setProtocol(protocol);
        service.setInterface(CallbackService.class);
        service.setRef(callbackService);
        service.setVersion("1.0.0");
        service.setGroup("dubbo1");

        //service.setCallbacks(1);

        service.export();

        Thread.currentThread().join();

    }
}
