package com.liupeng.dubbo.api;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.RegistryFactory;

import java.net.URLDecoder;

public class TestAddRoute {
    public static void main(String[] args) throws InterruptedException {
        RegistryFactory registryFactory = ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension();
        Registry registry = registryFactory.getRegistry(URL.valueOf("zookeeper://localhost:2181"));
        registry.register(URL.valueOf("condition://0.0.0.0/com.liupeng.dubbo.DemoService?category=routers&dynamic=false&rule=" + URL.encode("host = 100.80.146.72 => host = 100.80.146.72")));
//        System.out.println(URLDecoder.decode("/dubbo/com.liupeng.dubbo.DemoService/consumers/consumer%3A%2F%2F100.80.146.72%2Fcom.liupeng.dubbo.DemoService%3Factives%3D5%26application%3Dapp-api-consumer%26category%3Dconsumers%26check%3Dfalse%26connections%3D2%26dubbo%3D2.6.1%26group%3Ddubbo1%26interface%3Dcom.liupeng.dubbo.DemoService%26methods%3DsayHello%2CtestValidation%2ClistInt%26pid%3D24584%26revision%3D1.0.0%26router%3Dcondition%26side%3Dconsumer%26timeout%3D9000%26timestamp%3D1538214551733%26version%3D1.0.0"));
        Thread.currentThread().join();
    }

}
