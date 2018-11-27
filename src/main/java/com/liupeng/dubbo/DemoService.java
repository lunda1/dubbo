package com.liupeng.dubbo;

import com.liupeng.dubbo.api.MyParameter;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface DemoService {
    String sayHello(String name) throws InterruptedException;

    List<Integer> listInt(List<Integer> args) throws InterruptedException;

    //@interface TestValidation{}
    String testValidation(@NotNull MyParameter myParameter);
}
