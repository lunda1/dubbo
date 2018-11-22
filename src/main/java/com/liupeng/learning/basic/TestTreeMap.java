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
        treeMap.put(5,"five");

        System.out.println(JSON.toJSONString(treeMap.tailMap(2,true)));
        System.out.println("----------------");
        System.out.println(JSON.toJSONString(treeMap.tailMap(0,true).firstEntry()));
        System.out.println("----------------");
        System.out.println(JSON.toJSONString(treeMap.tailMap(4,true)));
    }
}
