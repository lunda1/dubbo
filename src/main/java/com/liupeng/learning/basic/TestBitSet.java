package com.liupeng.learning.basic;

import java.util.BitSet;

/**
 * https://www.cnblogs.com/xujian2014/p/5491286.html#_label2
 * https://blog.csdn.net/code52/article/details/50412634
 */
public class TestBitSet {
    public static void main(String[] args) {
        int [] array = new int [] {1,2,3,29,0,3};
        BitSet bitSet  = new BitSet();
        //将数组内容组bitmap
        for(int i=0;i<array.length;i++)
        {
            bitSet.set(array[i], true);
        }
        System.out.println(bitSet.size());
        System.out.println(bitSet.length());
        System.out.println(bitSet.get(12));
        System.out.println(bitSet.get(22));
        System.out.println(bitSet.get(29));
    }
}
