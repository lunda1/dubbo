package com.liupeng.learning.lamdar;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author oliver.liu
 * @Date 2019/6/18 19:27
 */
public class TestGroupby {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,1,2,3);
        Map<Integer,Long> map = list.stream().collect(Collectors.groupingBy(Integer::intValue,Collectors.counting()));
        System.out.println(JSON.toJSONString(map));
    }
}
