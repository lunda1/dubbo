package com.liupeng.learning.juc;

import com.alibaba.fastjson.JSON;

import java.util.LinkedHashMap;
import java.util.Map;

public class TestLinkedHashMap<K,V> extends LinkedHashMap<K,V>{

    private int lruCap;

    public TestLinkedHashMap(int cap, float loadFactor){
        super(cap,loadFactor,true);
        lruCap = (int)(cap * loadFactor - 1);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > lruCap; // 当哈希表中节点（键值对）数量超出缓存容量，返回true，删除双向链表头结点
    }

    public static void main(String[] args) {
        Map<Integer,Integer> map = new TestLinkedHashMap(8, 0.75f);

        map.put(1,1);
        map.put(2,2);
        map.put(3,3);
        map.put(4,4);

        map.get(1);

        map.put(5,5);
        map.put(6,6);
        map.put(7,7);
        map.put(8,8);

        System.out.println(JSON.toJSONString(map));

    }
}
