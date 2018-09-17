package com.liupeng.dubbo;

import java.util.List;

public interface DemoService {
    String sayHello(String name) throws InterruptedException;

    List<Integer> listInt(List<Integer> args) throws InterruptedException;
}
