package com.liupeng.learning.basic;

/**
 * @Author oliver.liu
 * @Date 2019/6/14 16:14
 */
public class TestReturn {
    public static void main(String[] args) {
        System.out.println(test());
    }

    public static int test(){
        try{
            System.out.println("before return 1");
            return 1;
            //System.out.println("after return 1");
        } finally {
            System.out.println("finally");
            //return 2;
        }
    }
}
