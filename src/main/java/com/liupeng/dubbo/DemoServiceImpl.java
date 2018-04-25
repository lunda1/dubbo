package com.liupeng.dubbo;

public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        System.out.println("-1-");
        return "Hello " + name;
    }
}
