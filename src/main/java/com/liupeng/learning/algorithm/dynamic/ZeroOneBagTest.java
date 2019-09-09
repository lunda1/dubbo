package com.liupeng.learning.algorithm.dynamic;

import java.util.HashSet;
import java.util.Set;

/**
 * 0-1背包问题
 * @Author oliver.liu
 * @Date 2019/9/5 20:20
 * https://segmentfault.com/a/1190000015944719
 */
public class ZeroOneBagTest {
    //最优解为24
    public static int total = 12;
    public static int count = 6;
    public static int[] w = new int[]{4, 6, 2, 2, 5, 1};
    public static int[] v = new int[]{8, 10, 6, 3, 7, 2};

    public static void main(String[] args) {
        //System.out.println(getResult(w,v,total));
        Set<String> set = new HashSet<>();
        System.out.println(set.add("a"));
        System.out.println(set.add("a"));
        System.out.println(set.add("a"));
    }

    /**
     *
     * @param w 物品i的重量为wi
     * @param v 物品i的价值为vi
     * @param total 背包总的容量为total
     * @return 能装入背包的总价值
     */
    public static int getResult(int w[], int v[], int total) {
        if (total <= 0) {
            return 0;
        }
        int n = w.length;
        //m[i][j] 表示 在背包容量剩余j的时候考虑到第i件物品的最大价值（考虑到第i件商品的含义是i件可能放入或者不放入）
        int m[][] = new int[n][total+1];

        for(int j = 0; j < total+1; j++) {
            if(w[0] > j) {
                m[0][j] = 0;
            } else {
                m[0][j] = v[0];
            }
        }

        for(int i = 1; i < n; i++) {
            for(int j = 1; j <= total; j++) {
                if(w[i] > j) {
                    m[i][j] = m[i-1][j];
                } else {
                    m[i][j] = Math.max(m[i-1][j], m[i-1][j-w[i]]+v[i]);
                }
            }
        }
        return m[n-1][total];
    }
}
