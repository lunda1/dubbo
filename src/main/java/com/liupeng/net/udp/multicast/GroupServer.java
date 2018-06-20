package com.liupeng.net.udp.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class GroupServer {
    private static int port = 8000;
    private static String address = "228.0.0.4";
    private static volatile boolean flag = true;
    public static void main(String[] args) throws Exception {
        MulticastSocket mss = null;
        try {
            InetAddress group = InetAddress.getByName(address);
            mss = new MulticastSocket(port);
            mss.joinGroup(group);
            while (flag) {
                String message = "Hello from GroupServer";
                byte[] buffer = message.getBytes();
                DatagramPacket dp = new DatagramPacket(buffer, buffer.length, group, port);
                mss.send(dp);
                Thread.sleep(1000);
                System.out.println("server send: "+message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (mss != null) {
                mss.close();
            }
        }

    }
}
