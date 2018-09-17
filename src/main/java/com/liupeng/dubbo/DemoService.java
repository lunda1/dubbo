package com.liupeng.dubbo;

import com.liupeng.dubbo.api.MyParameter;

import java.util.List;

public interface DemoService {
    String sayHello(String name) throws InterruptedException;

    List<Integer> listInt(List<Integer> args) throws InterruptedException;

    @interface TestValidation{}
    String testValidation(MyParameter myParameter) throws Exception;
}
