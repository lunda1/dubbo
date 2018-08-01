package com.liupeng.dubbo.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoServiceExceptionImpl implements DemoService {
    Logger logger = LoggerFactory.getLogger(DemoService3Impl.class);

    @Override
    public String sayHello(String name) throws Exception {
        logger.info("--"+this.getClass().getName()+"--");
        throw new RuntimeException("Artifact Exception");
    }
}
