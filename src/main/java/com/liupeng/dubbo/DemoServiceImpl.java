package com.liupeng.dubbo;

import static java.util.concurrent.TimeUnit.SECONDS;

public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) throws InterruptedException {
        System.out.println("-1-");
        SECONDS.sleep(2);
        return "Hello " + name;
    }
}
