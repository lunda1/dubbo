package com.liupeng.dubbo.api;

import com.liupeng.dubbo.DemoService;

public class MyMock implements DemoService {

    @Override
    public String sayHello(String name) throws InterruptedException {
        return " oooooo ";
    }
}
