package com.liupeng.dubbo.example;

import java.util.List;

public interface DemoService {
    String sayHello(String name) throws Exception;

    List<Integer> list() throws Exception;

    String timestamp() throws Exception;
}
