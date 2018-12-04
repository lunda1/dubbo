package com.liupeng.learning.basic;

/**
 * -Xss128k
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
