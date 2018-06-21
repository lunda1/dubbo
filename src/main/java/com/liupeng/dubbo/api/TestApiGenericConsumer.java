package com.liupeng.dubbo.api;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;

/**
 * 适用于消费端没有API接口类和入参以及出参pojo类的情况，泛化调用通常用于框架集成，比如：实现一个通用的服务测试框架
 **/
public class TestApiGenericConsumer {
    public static void main(String[] args) {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("generic-consumer");

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("127.0.0.1:2181");
        registryConfig.setProtocol("zookeeper");

        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setVersion("1.0.0");
        referenceConfig.setGroup("dubbo1");

        referenceConfig.setInterface("com.liupeng.dubbo.DemoService");
        referenceConfig.setGeneric(true);

        GenericService genericService = referenceConfig.get();
        Object result = genericService.$invoke("sayHello",new String[]{"java.lang.String"},new Object[]{"qqq"});
        System.out.println(result);

    }
}
