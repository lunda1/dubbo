package com.liupeng.dubbo.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

public class DemoServiceExceptionImpl implements DemoService {
    Logger logger = LoggerFactory.getLogger(DemoService3Impl.class);

    @Override
    public String sayHello(String name) throws Exception {
        logger.info("--"+this.getClass().getName()+"--");
        throw new RuntimeException("Artifact Exception");
    }

    @Override
    public List<Integer> list() throws Exception {
        return null;
    }

    @Override
    public String timestamp() throws Exception {
        return (new Date()).toString();
    }
}
