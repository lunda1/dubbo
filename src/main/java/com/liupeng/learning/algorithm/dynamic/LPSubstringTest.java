package com.liupeng.learning.algorithm.dynamic;

/**
 * 最长回文子串长度
 * @Author oliver.liu
 * @Date 2019/9/20 11:12
 * https://www.cnblogs.com/coderJiebao/p/Algorithmofnotes30.html
 * https://blog.csdn.net/lxt_Lucia/article/details/80085527
 */
public class LPSubstringTest {
    public static String s = "abcd232dcba";
    //此处的dp数组记录取值只有0或者1,0代表i到j不是回文串，1代表i到j是回文串
    public static int[][] dp = new int[s.length()][s.length()];

    public static void main(String[] args) {
        System.out.println(lps2(s));

    }

    /**
     * 动态规划
     * @param s
     * @return
     */
    public static int lps2(String s) {
        int len=s.length(), ans=1;        // ans 记录最长回文子串长度
        int i, j, L;
        // 边界(初始化长度为1和长度为2的子串是否为回文串，并记录最大回文串长度ans)
        for(i=0; i<len; ++i) {
            dp[i][i] = 1;
            if(i < len-1) {
                if(s.charAt(i) == s.charAt(i+1)) {
                    dp[i][i+1] = 1; //含义为i到j是回文串，1代表true
                    ans = 2;
                }
            }
        }
        // 状态转移方程（长度1和2的子串为边界条件，所以从长度为3的子串开始判断）
        for(L=3; L<=len; ++L) {            // 枚举子串长度
            for(i=0; i+L-1 < len; ++i) {    // 枚举子串的起始节点
                j = i+L-1;                // 子串的右端结点
                if(s.charAt(i) == s.charAt(j) && dp[i+1][j-1]==1) {//判断长度为3的串是用到了长度为1的串，同理判断长度为4的串时会用到长度为2的串
                    dp[i][j] = 1;
                    ans = L;            // 更新最长回文子串长度
                }
            }
        }

        return ans;
    }
}
