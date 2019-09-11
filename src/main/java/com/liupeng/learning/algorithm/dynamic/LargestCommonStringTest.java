package com.liupeng.learning.algorithm.dynamic;

/**
 * 最长公共子串长度
 * @Author oliver.liu
 * @Date 2019/9/10 16:45
 * https://blog.csdn.net/qq_31881469/article/details/77892324
 *
 */
public class LargestCommonStringTest {

    public static String A = "123wert";
    public static String B = "wer234";

    public static void main(String[] args) {
        System.out.println(calculateLCS1(A, A.length(),B,B.length()));
        System.out.println(calculateLCS2(A,B));

    }

    public static int calculateLCS1(String A, int n, String B, int m) {
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

    public static int calculateLCS2(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int result = 0;     //记录最长公共子串长度
        int c[][] = new int[len1+1][len2+1];
        for (int i = 0; i <= len1; i++) {
            for( int j = 0; j <= len2; j++) {
                if(i == 0 || j == 0) {//第0行和第0列本质并不存储任何字符
                    c[i][j] = 0;
                } else if (str1.charAt(i-1) == str2.charAt(j-1)) {//第i列存储坐标为i-1的元素，第j行存储坐标为j-1的元素
                    c[i][j] = c[i-1][j-1] + 1;
                    result = Math.max(c[i][j], result);
                } else {
                    c[i][j] = 0;
                }
            }
        }
        return result;
    }

}
