package com.liupeng.net.broad;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class BroadClient {
    public static void main(String[] args) {
        //广播的实现 :由客户端发出广播，服务器端接收
        String host = "255.255.255.255";//广播地址
//        String host = "127.0.0.1";//本地地址
//        String host = "100.80.146.49";//本地地址
//        String host = "10.170.12.105";//本地地址
        int port = 9999;//广播的目的端口
        String message = "this is a broadcase message!";//用于发送的字符串
        DatagramSocket ds = null;
        try {
            InetAddress adds = InetAddress.getByName(host);
            ds = new DatagramSocket();
            DatagramPacket dp = new DatagramPacket(message.getBytes(),message.length(), adds, port);
            ds.send(dp);
            System.out.println("start to broadcast message: "+message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ds != null) {
                ds.close();
            }
        }
    }
}
