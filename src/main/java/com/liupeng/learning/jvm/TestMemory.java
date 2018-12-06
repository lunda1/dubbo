package com.liupeng.learning.jvm;

import java.util.ArrayList;
import java.util.List;

public class TestMemory {
    static class OOMObject{
        public byte[] placeholder = new byte[64*1024];
    }

    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
        Thread.currentThread().join();
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i=0;i<num;i++) {
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }
}

