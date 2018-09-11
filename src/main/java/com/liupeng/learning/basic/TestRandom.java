package com.liupeng.learning.basic;

import java.util.Random;

public class TestRandom {
    public static void main(String[] args) {
        System.out.println(new Random().nextInt(100));
        System.out.println(new Random().nextInt(100));

        Random random = new Random();
        System.out.println(random.nextInt(100));
        System.out.println(random.nextInt(100));
    }
}
