package com.liupeng.learning.lamdar;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestLamdarNull {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        /*list.add("aaa");
        list.add("bbb");*/
        String result = Joiner.on(",").join(list.stream().map(o->o.length()).collect(Collectors.toList()));
        System.out.println(Strings.isNullOrEmpty(result));
        System.out.println(result);
    }
}
