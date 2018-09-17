package com.liupeng.learning.basic;

import java.util.HashMap;
import java.util.Map;

public class TestHashMap {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("1","1111");
        map.put("1","2222");
        System.out.println(map.get("1"));
    }
}
