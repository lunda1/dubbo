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
public class TriangleRecursiveTest {
    public static int[][] data = new int[5][5];

    public static void main(String[] args) {
        initData();
        System.out.println(getMax());

    }

    public static void initData(){
        data[0][0] = 7;
        data[1][0] = 3;data[1][1] = 8;
        data[2][0] = 8;data[2][1] = 1;data[2][2] = 0;
        data[3][0] = 2;data[3][1] = 7;data[3][2] = 4;data[3][3] = 4;
        data[4][0] = 4;data[4][1] = 5;data[4][2] = 2;data[4][3] = 6;data[4][4] = 5;
    }

    public static int getMax(){
        int level = 2;              //level表示层数
        int startI = 0; int startJ = 0;
        int maxSum = getMaxSum(level-1,startI,startJ);
        return maxSum;
    }

    public static int getMaxSum(int maxRowIndex,int i,int j){
        if(maxRowIndex == i){
            return data[i][j];
        }
        int x = getMaxSum(maxRowIndex,i+1,j);
        int y = getMaxSum(maxRowIndex,i+1,j+1);
        return Math.max(x,y)+data[i][j];
    }

}
