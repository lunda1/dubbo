package com.liupeng.dubbo.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

public class DemoService5Impl implements DemoService {
    Logger logger = LoggerFactory.getLogger(DemoService5Impl.class);

    @Override
    public String sayHello(String name) throws InterruptedException {
        logger.info("--"+this.getClass().getName()+"--");
        SECONDS.sleep(5);
        return "Hello " + name + this.getClass().getName();
    }

    @Override
    public List<Integer> list() throws Exception {
        return Arrays.asList(2);
    }

    @Override public String timestamp() throws Exception {
        return (new Date()).toString();
    }
}
