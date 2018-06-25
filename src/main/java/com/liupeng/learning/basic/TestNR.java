package com.liupeng.learning.basic;

public class TestNR {
    public static void main(String[] args) {
        System.out.println("111\r\n2222");
        System.out.println("111\n\r2222");
        int count = 1;
        for (int i=0;i<3;i++) {
            System.out.println(i+" "+count+" "+count++);
        }
    }
}
