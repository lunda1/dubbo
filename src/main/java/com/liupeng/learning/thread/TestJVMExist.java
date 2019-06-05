package com.liupeng.learning.thread;

/**
 * @Author oliver.liu
 * @Date 2019/6/5 15:13
 */
public class TestJVMExist {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
