package com.liupeng.learning.basic;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author oliver.liu
 * @Date 2019/6/6 17:42
 */
public class TestHashMap1 {
    public static void main(String[] args) {
        Map<Integer,Integer> map = new HashMap<>();
        map.put(1,1);
        System.out.println("success");
        final int MAXIMUM_CAPACITY = 1 << 30;
        int n = 16 - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        System.out.println((n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1);
    }
}
