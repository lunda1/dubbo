package com.liupeng.learning.lru;

import com.alibaba.fastjson.JSON;

import java.util.LinkedHashMap;

/**
 * @Author oliver.liu
 * @Date 2019/5/28 19:59
 */
public class LruTest {
    public static void main(String[] args) {
        LinkedHashMap<Integer,Integer> map = new LinkedHashMap<Integer,Integer>(10,0.7f,true);
        map.put(1,10);
        map.put(2,20);
        map.put(3,30);
        System.out.println(JSON.toJSONString(map));
        map.get(1);
        System.out.println(JSON.toJSONString(map));
        map.put(4,40);
        System.out.println(JSON.toJSONString(map));
        map.get(2);
        System.out.println(JSON.toJSONString(map));

    }
}
