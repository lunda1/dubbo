package com.liupeng.learning.algorithm.greedy;

/**
 * @Author oliver.liu
 * @Date 2019/8/28 17:03
 * https://www.cnblogs.com/hust-chen/p/8646009.html
 */
public class ClassroomTest {
    public static int[] s = {1,3,0,5,3,5,6,8,8,2,12};

    public static int[] e = {4,5,6,7,8,9,10,11,12,13,14};


    public static void main(String[] args) {
        printActivity();
        selectActivity();
    }

    public static void printActivity(){
        int index = 0;
        while (index < e.length) {
            System.out.println("["+s[index]+","+e[index]+")");
            index++;
        }
        System.out.println("----------------");
    }

    public static void selectActivity(){
        int index = 0;
        int lastIndex = 0;
        System.out.println("["+s[lastIndex]+","+e[lastIndex]+")");
        while (index < e.length) {
            while (index < e.length && e[lastIndex] > s[index]) {
                index++;
            }
            lastIndex = index;
            if (lastIndex < e.length) {
                System.out.println("["+s[lastIndex]+","+e[lastIndex]+")");
            }
        }
    }
}
