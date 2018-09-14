package com.liupeng.learning.basic;

import org.apache.commons.lang.StringEscapeUtils;

public class TestUTF8 {
    public static void main(String[] args) {
        String s1 = "\\u529f\\u80fd\\ud83d\\ude00\\ud83d\\ude00\\ud83d\\ude00\\ud83d\\ude03";
        System.out.println(s1);

        String s3 = StringEscapeUtils.escapeJava(s1);
        System.out.println(s3);

        String s2 = "\u529f\u80fd\ud83d\ude00\ud83d\ude00\ud83d\ude00\ud83d\ude03";
        System.out.println(s2);


    }
}
