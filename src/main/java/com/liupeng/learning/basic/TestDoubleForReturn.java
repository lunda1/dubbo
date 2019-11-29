package com.liupeng.learning.basic;

/**
 * @Author oliver.liu
 * @Date 2019/11/29 11:36
 */
public class TestDoubleForReturn {
    public static void main(String[] args) {
        for (int i=0;i<3;i++) {
            System.out.println("i:"+i);
            inner:
            for (int j=0;j<3;j++) {
                System.out.println("j:"+j);
                break inner;
            }
        }
    }
}
