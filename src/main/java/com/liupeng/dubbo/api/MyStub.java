package com.liupeng.dubbo.api;

import com.liupeng.dubbo.DemoService;

import java.util.List;

public class MyStub implements DemoService {

    private final DemoService demoService;

    public MyStub(DemoService demoService){
        this.demoService = demoService;
    }

    @Override
    public String sayHello(String name) throws InterruptedException {
        return " ----stub---- "+demoService.sayHello(name);
    }

    @Override
    public List<Integer> listInt(List<Integer> args) throws InterruptedException {
        return demoService.listInt(args);
    }

    @Override public String testValidation(MyParameter myParameter) throws Exception {
        return demoService.testValidation(myParameter);
    }

    @Override public List<Integer> addAll(List<Integer> list) throws Exception {
        return demoService.addAll(list);
    }
}
