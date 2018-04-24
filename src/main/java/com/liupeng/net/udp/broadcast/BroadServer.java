package com.liupeng.net.broad;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class BroadServer {
    //服务器端接收程序
    public static void main(String[] args) {
        int port = 9999;//开启监听的端口
        DatagramSocket ds = null;
        DatagramPacket dp = null;
        byte[] buf = new byte[1024];//存储发来的消息
        StringBuffer sbuf = new StringBuffer();
        try
        {
            //绑定端口的
            ds = new DatagramSocket(port);
            dp = new DatagramPacket(buf, buf.length);
            System.out.println("监听广播端口打开：");
//            ds.setSoTimeout(10*1000);
            ds.receive(dp);
            String address = dp.getAddress().getHostAddress();
            ds.close();
            int i;
            for(i=0;i<1024;i++) {
                if(buf[i] == 0) {
                    break;
                }
                sbuf.append((char) buf[i]);
            }
            System.out.println("收到广播消息：" + sbuf.toString()+", IP地址: "+address);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ds != null) {
                ds.close();
            }
        }
    }
}
