package com.liupeng.learning.pool;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestScheduled {
    public static void main(String[] args) {
        ScheduledExecutorService pool = null;
        pool = Executors.newScheduledThreadPool(5);
        System.out.println(new Date());
/*        pool.schedule(new Runnable() {
            @Override public void run() {
                System.out.println(new Date());
            }
        },5, TimeUnit.SECONDS);*/

        pool.scheduleAtFixedRate(new Runnable() {
            @Override public void run() {
                System.out.println(new Date()+" at fixed rate");
            }
        },6,3,TimeUnit.SECONDS);


        pool.scheduleWithFixedDelay(new Runnable() {
            @Override public void run() {
                System.out.println(new Date()+" with fixed rate");
            }
        },6,6,TimeUnit.SECONDS);

        //pool.shutdown();
    }
}
