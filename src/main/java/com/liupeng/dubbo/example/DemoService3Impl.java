package com.liupeng.dubbo.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.concurrent.TimeUnit.SECONDS;

public class DemoService3Impl implements DemoService {
    Logger logger = LoggerFactory.getLogger(DemoService3Impl.class);

    @Override
    public String sayHello(String name) throws InterruptedException {
        logger.info("--"+this.getClass().getName()+"--");
        SECONDS.sleep(3);
        return "Hello " + name + this.getClass().getName();
    }
}
