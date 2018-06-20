package com.liupeng.dubbo;

import static java.util.concurrent.TimeUnit.SECONDS;

public class DemoServiceImpl2 implements DemoService {
    @Override
    public String sayHello(String name) {
        try {
            System.out.println("-2-");
            SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello " + name;
    }
}
