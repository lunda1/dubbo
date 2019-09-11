package com.liupeng.learning.algorithm.dynamic;

/**
 * 最长公共子序列长度
 * @Author oliver.liu
 * @Date 2019/9/10 16:45
 * https://blog.csdn.net/qq_37763204/article/details/79394397
 * https://blog.csdn.net/someone_and_anyone/article/details/81044153
 *
 * 如果采用暴力解法，A的每一位有两种选择，即选还是不选，则A的所有子序列个数为2^n,同理B的所有子序列个数为2^m。
 * 所以如果使用暴力解法一个个比较，则总的时间复杂度为O(2^(m+n))。
 */
public class LargestCommonSequencesTest {

    public static String A = "1A2C3D4B56";
    public static String B = "B1D23CA45B6A";

    public static void main(String[] args) {
        System.out.println(calculateLCS(A, A.length(),B,B.length()));

    }

    public static int calculateLCS(String A, int n, String B, int m) {
        //对比两个序列的第一个元素为边界停止条件，即如果边界的相等，可设置为1，如果不等则默认为0

        // write code here
        int[][] dp = new int[n][m];
        char[] a = A.toCharArray();
        char[] b = B.toCharArray();

        //初始化第一列
        for(int i=0;i<n;i++){
            if (a[i] == b[0]) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = 0;
            }
        }//for

        //初始化第一行
        for(int j=0;j<m;j++){
            if (a[0] == b[j]) {
                dp[0][j] = 1;
            } else {
                dp[0][j] = 0;
            }
        }//for

        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                if(a[i]==b[j]){
                    dp[i][j] = dp[i-1][j-1]+1;//此处是区分最长公共子序列和最长公共子串的关键区别
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }//for
        }//for

        return dp[n-1][m-1];
    }

    public static int max(int a,int b,int c){
        int max = a;
        if(b>max)
            max=b;
        if(c>max)
            max = c;
        return max;
    }

}
