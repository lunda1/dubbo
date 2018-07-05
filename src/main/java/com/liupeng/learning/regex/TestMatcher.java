package com.liupeng.learning.regex;

import com.alibaba.fastjson.JSON;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestMatcher {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("\\d+");
        System.out.println(pattern.pattern());

        String[] arr = pattern.split("I12343Love56789U6788!");
        System.out.println(JSON.toJSONString(arr));

        Matcher m = pattern.matcher("22bb33");
        System.out.println(m.pattern());

        System.out.println(m.matches());

        Matcher m2 = pattern.matcher("2233");
        System.out.println(m2.matches());

        Matcher m3 = pattern.matcher("aaa222bbb");
        System.out.println(m3.find());
        System.out.println(m3.start());
        System.out.println(m3.end());
        System.out.println(m3.group());

        Matcher m4 = pattern.matcher("I12343Love56789U6788");
        while (m4.find()) {
            System.out.println(m4.group());
        }
    }
}
