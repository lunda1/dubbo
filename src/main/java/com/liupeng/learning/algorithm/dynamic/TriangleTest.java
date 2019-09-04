package com.liupeng.learning.algorithm.dynamic;

/**
 * @Author oliver.liu
 * @Date 2019/9/4 19:22
 * https://blog.csdn.net/ailaojie/article/details/83014821
 *
 * 7
 * 3 8
 * 8 1 0
 * 2 7 4 4
 * 4 5 2 6 5
 *
 */
public class TriangleTest {
    public static int[][] data = new int[5][5];

    public static void initData(){
        data[0][0] = 7;
        data[1][0] = 3;data[1][1] = 8;
        data[2][0] = 8;data[2][1] = 1;data[2][2] = 0;
        data[3][0] = 2;data[3][1] = 7;data[3][2] = 4;data[3][3] = 4;
        data[4][0] = 4;data[4][1] = 5;data[4][2] = 2;data[4][3] = 6;data[4][4] = 5;
    }

    public static int getMax(){
        int MAX = 101;
        int[][] D = data;   //存储数字三角形
        int level = 2;              //n表示层数
        int i = 0; int j = 0;
        int maxSum = getMaxSum(D,level-1,i,j);
        return maxSum;
    }

    public static int getMaxSum(int[][] D,int n,int i,int j){
        if(i == n){
            return D[i][j];
        }
        int x = getMaxSum(D,n,i+1,j);
        int y = getMaxSum(D,n,i+1,j+1);
        return Math.max(x,y)+D[i][j];
    }

    public static void main(String[] args) {
        initData();
        System.out.println(getMax());
    }

}
