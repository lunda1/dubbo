package com.liupeng.learning.basic;

import static java.util.concurrent.TimeUnit.SECONDS;

public class TestJVMShutdownHook {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("start ...");
        System.out.println("sleep 5 sec");
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override public void run() {
                System.out.println("jvm is shutdown...");
            }
        });
        SECONDS.sleep(5);
        System.out.println("end.");
    }
}
