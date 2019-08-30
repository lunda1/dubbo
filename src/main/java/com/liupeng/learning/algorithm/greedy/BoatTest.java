package com.liupeng.learning.algorithm.greedy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author oliver.liu
 * @Date 2019/8/29 20:50
 * https://blog.csdn.net/u012845311/article/details/73368947
 */
public class BoatTest {
    public static void main(String[] args) {
//        System.out.println("请输入数组长度：");
//        Scanner sc = new Scanner(System.in);
//        int N = sc.nextInt();
//
//        int[] array = new int[N];
//        System.out.println("请输入数组元素：");
//        for (int i = 0; i < N; i++) {
//            array[i] = sc.nextInt(); // 输入数组
//        }
        int N = 5;
        int[] array = {10,5,2,2,1};
        Arrays.sort(array);   //升序排列
        int sum = 0;

        //此处的贪心逻辑在于每次先将最慢的两个运送到对岸
        while (N > 3) {       //贪心算法实现
            //每计算一次时间代表运送了一趟，有四个时间则说明运送了四趟，运了四趟说明已经运送了两个人过去
            //分为两种：通过最快的人将最后两个送过去；使用次慢和最慢将最后两个送过去。二者的结果都是将最后两个先运送到对面。
            int asum = array[0] + 2 * array[1] + array[N - 1];
            int bsum = 2 * array[0] + array[N - 2] + array[N - 1];
            if (asum < bsum) // 两种方式选择
                sum += asum;
            else
                sum += bsum;
            //因为经过上面流程，会送过去2个人，所以此处减2。
            N -= 2;
        }

        //边界条件讨论
        if (N == 3) {
            sum += array[0] + array[1] + array[2]; // 只剩三人
        } else if (N == 2) {
            sum += array[1]; // 只剩两人
        } else {
            sum += array[0];
        }

        System.out.println("总过河时间为：" + sum);
    }
}
