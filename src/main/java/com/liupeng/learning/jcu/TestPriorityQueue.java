package com.liupeng.learning.jcu;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TestPriorityQueue {
    public static void main(String[] args) {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        };
        PriorityQueue<Integer> queue = new PriorityQueue<>(comparator);//54321
        //PriorityQueue<Integer> queue = new PriorityQueue<>();//12345
        queue.add(3);
        queue.add(1);
        queue.add(2);
        queue.add(4);
        queue.add(5);
        while(!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}
