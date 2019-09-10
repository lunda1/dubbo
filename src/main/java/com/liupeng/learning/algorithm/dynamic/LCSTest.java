package com.liupeng.learning.algorithm.dynamic;

/**
 * 最长公共子序列
 * @Author oliver.liu
 * @Date 2019/9/10 16:45
 * https://blog.csdn.net/qq_37763204/article/details/79394397
 * https://blog.csdn.net/someone_and_anyone/article/details/81044153
 */
public class LCSTest {

    public static String A = "1A2C3D4B56";
    public static String B = "B1D23CA45B6A";

    public static void main(String[] args) {
        System.out.println(findLCS(A, A.length(),B,B.length()));

    }

    public static int findLCS(String A, int n, String B, int m) {
        //对比两个序列的第一个元素为边界停止条件，即如果边界的相等，可设置为1，如果不等则默认为0

        // write code here
        int[][] dp = new int[n][m];
        char[] a = A.toCharArray();
        char[] b = B.toCharArray();

        //初始化第一列
        for(int i=0;i<n;i++){
            if(a[i]==b[0]){
                dp[i][0] = 1;
                for(int j=i+1;j<n;j++){
                    dp[j][0] = 1;
                }
                break;
            }
        }//for

        //初始化第一行
        for(int i=0;i<m;i++){
            if(a[0]==b[i]){
                dp[0][i] = 1;
                for(int j=i+1;j<m;j++){
                    dp[0][j] = 1;
                }
                break;
            }
        }//for

        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                if(a[i]==b[j]){
                    dp[i][j] = max(dp[i-1][j-1]+1,dp[i-1][j],dp[i][j-1]);//如果i==j，说明最长子序列还可能是dp[i-1][j-1]+1的记录。
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
