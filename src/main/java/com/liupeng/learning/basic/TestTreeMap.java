package com.liupeng.learning.basic;

import com.alibaba.fastjson.JSON;

import java.util.TreeMap;

public class TestTreeMap {
    public static void main(String[] args) {
        TreeMap<Integer,String> treeMap = new TreeMap<>();
        treeMap.put(1,"one");
        treeMap.put(2,"two");
        treeMap.put(6,"six");
        treeMap.put(4,"four");
        treeMap.put(3,"three");

        System.out.println(JSON.toJSONString(treeMap.tailMap(0,true).firstEntry()));
    }
}
