package com.liupeng.learning.basic;

/**
 * -Xss128k:代表线程堆栈的大小为128k，一个进程可以创建多个线程，一个线程有一个堆栈
 */
public class TestStackOverflowError {
    private int stackLength = 1;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        TestStackOverflowError testStackOverflowError = new TestStackOverflowError();
        try {
            testStackOverflowError.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length: "+testStackOverflowError.stackLength);
            throw e;
        }
    }
}
