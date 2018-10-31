package com.liupeng.learning.juc;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class TestDelayQueue {
    public static void main(String[] args) {
        DelayQueue<DelayEvent> queue = new DelayQueue();
        DelayEvent d1 = new DelayEvent();
        d1.setTime(1);
        d1.setName("name1");
        queue.add(d1);

        DelayEvent d2 = new DelayEvent();
        d1.setTime(-1);
        d1.setName("name2");
        queue.add(d2);

        System.out.println(queue.poll().getName());


    }
}

class DelayEvent implements Delayed{

    private long time;
    private String name;

    @Override
    public long getDelay(TimeUnit unit) {
        return time;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
