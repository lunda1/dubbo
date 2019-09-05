package com.liupeng.learning.algorithm.dynamic;

/**
 * 最大连续子序列之和
 * @Author oliver.liu
 * @Date 2019/9/5 17:54
 * https://blog.csdn.net/code17710597026/article/details/52107827
 */
public class LargestSumTest {
    public static int[] arr = new int[]{-2,11,-4,13,-5,-2};
    public static int[] dp = new int[arr.length];


    public static void main(String[] args) {
        printLargestDP();

    }

    public static void printLargestDP(){
        dp[0] = arr[0];// 边界
        for(int i=1; i<arr.length; ++i) {
            // 状态转移方程
            dp[i] = Math.max(arr[i], dp[i-1]+arr[i]);
        }
        // 求最大连续子序列和
        int k = dp[0];
        for(int i=1; i<arr.length; ++i) {
            if(dp[i] > k) {
                k = dp[i];
            }
        }
        System.out.println(k);
    }
}
