package com.liupeng.learning.log4j;

import org.apache.log4j.Logger;

public class TestLog4j {
    private static Logger logger = Logger.getLogger(TestLog4j.class.getName());

    public static void main(String[] args) {
        logger.info("--info test--");
        logger.debug("--debug test--");
    }
}
