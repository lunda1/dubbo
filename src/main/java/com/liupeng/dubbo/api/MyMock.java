package com.liupeng.dubbo.api;

import com.liupeng.dubbo.DemoService;

import java.util.List;

public class MyMock implements DemoService {

    @Override
    public String sayHello(String name) throws InterruptedException {
        return " oooooo ";
    }

    @Override public List<Integer> listInt(List<Integer> args) throws InterruptedException {
        return null;
    }

    @Override public String testValidation(MyParameter myParameter) throws Exception {
        return null;
    }

}
