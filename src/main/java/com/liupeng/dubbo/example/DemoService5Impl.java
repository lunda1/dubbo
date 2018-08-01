package com.liupeng.dubbo.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.concurrent.TimeUnit.SECONDS;

public class DemoService5Impl implements DemoService {
    Logger logger = LoggerFactory.getLogger(DemoService5Impl.class);

    @Override
    public String sayHello(String name) throws InterruptedException {
        logger.info("--"+this.getClass().getName()+"--");
        SECONDS.sleep(5);
        return "Hello " + name + this.getClass().getName();
    }
}
