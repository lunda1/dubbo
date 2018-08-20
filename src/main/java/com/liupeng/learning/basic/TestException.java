package com.liupeng.learning.basic;

public class TestException {
    public static void main(String[] args) throws Exception{
        try {
            int divide = 0;
            int a = 1 / divide;
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException");
            throw new Exception("1111");
        } catch (Exception e) {
            System.out.println("Exception");
        } finally {
            System.out.println("finally");
        }
    }
}
