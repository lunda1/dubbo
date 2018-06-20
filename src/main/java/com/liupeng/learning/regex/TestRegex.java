package com.liupeng.learning.regex;

import com.alibaba.fastjson.JSON;

public class TestRegex {
    public static void main(String[] args) {
        //逗号和各种空格，制表键中的某一种，且出现一次到多次作为分隔符，即分隔符有多种，例如分隔符可能为','或者
        //',,'或'空格'或'空格,'或'空格,空格'
        String[] strArr = "999 1, ,  000".split("[,\\s]+");
        System.out.println(JSON.toJSONString(strArr));
        System.out.println(strArr.length);
    }
}
