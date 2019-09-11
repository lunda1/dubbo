package com.liupeng.learning.algorithm.dynamic;

/**
 * 最长公共子串长度
 * @Author oliver.liu
 * @Date 2019/9/10 16:45
 * https://blog.csdn.net/qq_31881469/article/details/77892324
 * 非相等的位置全部填写0，相等的位置
 */
public class LargestCommonStringTest {

    public static String A = "123wert4567";
    public static String B = "wer234t567";

    public static void main(String[] args) {
        System.out.println(calculateLCS2(A,B));

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
