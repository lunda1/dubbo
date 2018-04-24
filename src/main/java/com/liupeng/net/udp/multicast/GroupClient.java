package com.liupeng.net.group;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class GroupClient {
    private static int port = 8000;

    private static String address = "228.0.0.4";

    private static volatile boolean flag = true;

    public static void main(String[] args) throws Exception {

        InetAddress group = InetAddress.getByName(address);
        MulticastSocket msr = null;
        try {
            msr = new MulticastSocket(port);
            msr.joinGroup(group);
            byte[] buffer = new byte[1024];
            while (flag) {
                DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
                msr.receive(dp);
                String s = new String(dp.getData(), 0, dp.getLength());
                System.out.println("receive from "+dp.getAddress().getCanonicalHostName()+" : "+s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if (msr != null) {
                msr.close();
            }
        }

    }
}
