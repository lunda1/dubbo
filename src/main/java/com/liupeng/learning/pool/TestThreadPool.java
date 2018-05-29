package com.liupeng.learning.pool;

import jdk.nashorn.internal.ir.Block;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

public class TestThreadPool {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingDeque<>();
        queue.offer("hotellist_cityId1.xml");
        queue.offer("hotellist_cityId3.xml");
        queue.offer("hotellist_cityId7.xml");
        queue.offer("hotellist_cityId757.xml");

        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i=0; i<5; i++) {
            pool.submit(new Runnable() {
                @Override public void run() {
                    String fileName = null;
                    try {
                        while ((fileName = queue.poll()) != null) {
                            TimeUnit.SECONDS.sleep(3);
                            //插入数据
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                            System.out.println("insert data: "+fileName+" "+df.format(new Date()));
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        pool.shutdown();
    }
}
