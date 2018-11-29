package com.liupeng.dubbo.api;

import com.liupeng.dubbo.DemoService;

import java.util.List;

public class DemoServiceStub implements DemoService {

    private final DemoService demoService;

    public DemoServiceStub(DemoService demoService){
        this.demoService = demoService;
    }

    @Override
    public String sayHello(String name) throws InterruptedException {
        return " ----DemoServiceStub---- "+demoService.sayHello(name);
    }

    @Override
    public List<Integer> listInt(List<Integer> args) throws InterruptedException {
        return demoService.listInt(args);
    }

    @Override public String testValidation(MyParameter myParameter) {
        return demoService.testValidation(myParameter);
    }

}
