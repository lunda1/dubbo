package com.liupeng.learning.basic;

import org.apache.commons.lang.StringEscapeUtils;

import java.net.URLDecoder;

public class TestUTF8 {
    public static void main(String[] args) {
        String s1 = "\\u529f\\u80fd\\ud83d\\ude00\\ud83d\\ude00\\ud83d\\ude00\\ud83d\\ude03";
        System.out.println(s1);

        String s3 = StringEscapeUtils.escapeJava(s1);
        System.out.println(s3);

        String s2 = "\u529f\u80fd\ud83d\ude00\ud83d\ude00\ud83d\ude00\ud83d\ude03";
        System.out.println(s2);

        String d = "/dubbo/com.liupeng.dubbo.DemoService/consumers/consumer%3A%2F%2F100.80.146.24%2Fcom.liupeng.dubbo.DemoService%3Factives%3D5%26application%3Dapp-api-consumer%26category%3Dconsumers%26check%3Dfalse%26connections%3D2%26dubbo%3D2.6.1%26group%3Ddubbo1%26interface%3Dcom.liupeng.dubbo.DemoService%26methods%3DsayHello%2CtestValidation%2ClistInt%26pid%3D129048%26protocol%3Ddubbo%26revision%3D1.0.0%26router%3Dcondition%26side%3Dconsumer%26timeout%3D9000%26timestamp%3D1542957222642%26version%3D1.0.0";
        System.out.println(URLDecoder.decode(d));

    }
}
