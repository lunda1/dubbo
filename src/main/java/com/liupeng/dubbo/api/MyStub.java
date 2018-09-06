package com.liupeng.dubbo.api;

import com.liupeng.dubbo.DemoService;

public class MyStub implements DemoService {

    private final DemoService demoService;

    public MyStub(DemoService demoService){
        this.demoService = demoService;
    }

    @Override
    public String sayHello(String name) throws InterruptedException {
        return " ----stub---- "+demoService.sayHello(name);
    }
}
