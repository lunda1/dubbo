package com.liupeng.learning;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author oliver.liu
 * @Date 2019/6/4 13:59
 */
public class net {
    public static void main(String[] args) throws UnknownHostException {
        System.out.println("hostaddress: "+InetAddress.getLocalHost().getHostAddress());
        System.out.println("address: "+InetAddress.getLocalHost().getAddress().toString());
        System.out.println("hostname: "+InetAddress.getLocalHost().getHostName());
        System.out.println("CanonicalHostName: "+InetAddress.getLocalHost().getCanonicalHostName());
    }
}
