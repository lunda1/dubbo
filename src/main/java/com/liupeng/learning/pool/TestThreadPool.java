package com.liupeng.learning.pool;

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
        String fileName = queue.poll();

        while (fileName != null) {
            pool.submit(new Runnable() {
                @Override public void run() {
                    String fileName = null;
                    try {
                        TimeUnit.SECONDS.sleep(3);
                        //插入数据
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                        System.out.println("insert data: "+fileName+" "+df.format(new Date()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            fileName = queue.poll();
        }
        pool.shutdown();

    }
}
