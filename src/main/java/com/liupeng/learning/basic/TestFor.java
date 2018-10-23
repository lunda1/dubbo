package com.liupeng.learning.basic;

public class TestFor {
    public static void main(String[] args) {
        retry:
        for (int j=0;j<5;j++) {
            System.out.println("j:"+j);
            for (int i=0;i<5;i++) {
                System.out.println(" i:"+i);
                if (i == 1) {
                    continue retry;
                } else if (i == 2) {
                    break retry;
                }
            }
        }
    }
}
