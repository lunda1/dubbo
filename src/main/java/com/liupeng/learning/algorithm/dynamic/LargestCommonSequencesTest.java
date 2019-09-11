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
        calculateLCS(A, A.length(),B,B.length());
        calculateLCS2(A,B);

    }

    public static void calculateLCS(String A, int n, String B, int m) {
        //对比两个序列的第一个元素为边界停止条件，即如果边界的相等，可设置为1，如果不等则默认为0

        // write code here
        int[][] dp = new int[n][m];//此方法没有单独的0行和0列
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
                    dp[i][j] = dp[i-1][j-1]+1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);//此处是区分最长公共子序列和最长公共子串的关键区别
                }
            }//for
        }//for

        System.out.println("计算方法一<最长公共子序列长度>："+dp[n-1][m-1]);
    }

    public static int max(int a,int b,int c){
        int max = a;
        if(b>max)
            max=b;
        if(c>max)
            max = c;
        return max;
    }

    public static void calculateLCS2(String str1, String str2) {
        //1.代表左上，2.代表上，3.代表左
        int len1 = str1.length();
        int len2 = str2.length();
        int c[][] = new int[len1+1][len2+1];//此方法定义了单独的0行和0列
        int b[][] = new int[len1+1][len2+1];
        for (int i = 0; i <= len1; i++) {
            for( int j = 0; j <= len2; j++) {
                if(i == 0 || j == 0) {
                    c[i][j] = 0;//0行和0列默认填充0，是为了其他节点计算使用
                } else if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    c[i][j] = c[i-1][j-1] + 1;
                    b[i][j] = 1;
                } else if (c[i-1][j] >= c[i][j-1]) {
                    c[i][j] = c[i-1][j];
                    b[i][j] = 2;
                } else {
                    c[i][j] = c[i][j-1];
                    b[i][j] = 3;
                }
            }
        }

        System.out.println("计算方法二<最长公共子序列长度>："+c[len1][len2]);

        //打印其中一个最长子序列
        printLCS(b,str1,len1,len2);
    }

    public static void printLCS(int[][] b, String str1, int len1, int len2){
        if (len1 == 0 || len2 == 0){
            return;
        }
        if (b[len1][len2] == 1){
            printLCS(b,str1,len1-1,len2-1);
            System.out.print(str1.charAt(len1-1));
        } else if (b[len1][len2] == 2) {
            printLCS(b,str1,len1-1,len2);
        } else if (b[len1][len2] == 3) {
            printLCS(b,str1,len1,len2-1);
        }
    }
}
