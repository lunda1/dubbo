package com.liupeng.dubbo.api;

import java.net.URLDecoder;

public class TestDecodeCMDDubboURL {
    public static void main(String[] args) {
        String encodedUrl = "dubbo%3A%2F%2F10.170.12.87%3A20880%2Fcom.liupeng.dubbo.DemoService%3Fanyhost%3Dtrue%26application%3Dapp-api-provider%26dubbo%3D2.6.1%26generic%3Dfals\n"
            + "e%26group%3Ddubbo1%26interface%3Dcom.liupeng.dubbo.DemoService%26methods%3DsayHello%26pid%3D9488%26revision%3D1.0.0%26side%3Dprovider%26timestamp%3D15\n"
            + "29398373425%26version%3D1.0.0";
        String decodedUrl = URLDecoder.decode(encodedUrl);
        System.out.println(decodedUrl);
    }
}
