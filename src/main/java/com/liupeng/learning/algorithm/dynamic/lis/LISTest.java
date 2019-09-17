package com.liupeng.learning.algorithm.dynamic.lis;

/**
 * 最长上升子序列长度
 * @Author oliver.liu
 * @Date 2019/9/16 11:12
 * https://blog.csdn.net/lxt_Lucia/article/details/81206439
 */
public class LISTest {
    public static int[] arr = {2 ,7 ,1 ,5 ,6 ,4 ,3 ,8 ,9};
    public static int max = 1;

    public static void main(String[] args) {
        printLISLength();

    }

    public static void printLISLength(){
        int[] dp = new int[arr.length];

        for (int i=0;i<arr.length;i++) {
            dp[i] = 1;
        }

        for (int i=1;i<arr.length;i++) {
            for (int j=0;j<i;j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
        }

        for (int i=0;i<arr.length;i++) {
            max = Math.max(max,dp[i]);
        }

        System.out.println(max);
    }
}
