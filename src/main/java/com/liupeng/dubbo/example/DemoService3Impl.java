package com.liupeng.dubbo.example;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

public class DemoService3Impl implements DemoService {
    Logger logger = LoggerFactory.getLogger(DemoService3Impl.class);

    @Override
    public String sayHello(String name) throws InterruptedException {
        logger.info("--"+this.getClass().getName()+"--");
        SECONDS.sleep(3);
        return "Hello " + name + this.getClass().getName();
    }

    @Override
    public List<Integer> list() throws Exception {
        return Arrays.asList(1);
    }

    @Override public String timestamp() throws Exception {
        return (new Date()).toString();
    }
}
