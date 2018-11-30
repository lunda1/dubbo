package com.liupeng.dubbo.api;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.RegistryFactory;

import java.net.URLDecoder;

public class TestAddConfiguration {
    public static void main(String[] args) throws InterruptedException {
        RegistryFactory registryFactory = ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension();
        Registry registry = registryFactory.getRegistry(URL.valueOf("zookeeper://localhost:2181"));
        registry.register(URL.valueOf("override://0.0.0.0/com.liupeng.dubbo.DemoService?category=configurators&dynamic=false&application=app-api-provider&timeout=15000"));
        Thread.currentThread().join();
    }

}
