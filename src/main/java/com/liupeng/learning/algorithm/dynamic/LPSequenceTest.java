package com.liupeng.learning.algorithm.dynamic;

/**
 * 最长回文子序列长度
 * @Author oliver.liu
 * @Date 2019/9/20 11:12
 * https://blog.csdn.net/lxt_Lucia/article/details/80085527
 */
public class LPSequenceTest {
    public static String s = "abcd232dcba";
    //此处的dp数组记录的是i到j的最长回文子序列长度
    public static int[][] dp = new int[s.length()][];


    public static void main(String[] args) {
//        System.out.println(lps1(s,0,s.length()-1));
        System.out.println(lps2(s));

    }

    /**
     * 递归方法
     * @param s
     * @param i
     * @param j
     * @return
     */
    public static int lps1(String s, int i, int j){
        if (i == j) {
            return 1;
        } else if (i > j) {
            return 0;
        }

        if (s.charAt(i) == s.charAt(j)) {
            return lps1(s,i+1,j-1)+2;
        }

        return Math.max(lps1(s,i+1,j),lps1(s,i,j-1));
    }

    /**
     * 动态规划
     * @param s
     * @return
     */
    public static int lps2(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];

        //初始化所有i==j的元素为1，其他默认为0
        for (int i=0;i<len;i++) {
            dp[i][i] = 1;
        }

        //以i=3为例，先计算[2,3],其有三种可能,[3,2]+2/[2,2]/[3,3]，对于i>j的情况默认值为0，i==j的情况初始化为1；
        //然后计算[1,3]，其值有三种可能[2,2]+2/[1,2]/[2,3],其中[2,3]在上一步中已经被算出了，所以推理边界条件为i==j和i>j。
        for(int i=1;i<len;i++){
            for(int j=i-1;j>=0;j--){
                if(s.charAt(i) == s.charAt(j))
                    dp[j][i]= dp[j+1][i-1]+2;
                else
                    dp[j][i]=Math.max(dp[j][i-1], dp[j+1][i]);
            }
        }
        return dp[0][len-1];
    }
}
