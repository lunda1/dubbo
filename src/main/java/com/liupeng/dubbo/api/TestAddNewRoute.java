package com.liupeng.dubbo.api;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.RegistryFactory;

public class TestAddNewRoute {
    public static void main(String[] args) throws InterruptedException {
        RegistryFactory registryFactory = ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension();
        Registry registry = registryFactory.getRegistry(URL.valueOf("zookeeper://localhost:2181"));
        registry.register(URL.valueOf("route://0.0.0.0/com.liupeng.dubbo.DemoService?category=routers&dynamic=false&rule=" + URL.encode("host = 10.161.120.171 => host = 10.161.120.171")));
        Thread.currentThread().join();
    }

}
