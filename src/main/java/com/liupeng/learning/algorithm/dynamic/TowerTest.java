package com.liupeng.learning.algorithm.dynamic;

/**
 * 数塔问题
 * @Author oliver.liu
 * @Date 2019/9/5 16:37
 * https://blog.csdn.net/code17710597026/article/details/52107827
 */
public class TowerTest {
    public static int[][] data = new int[][]{
            {9,0,0,0,0},
            {12,15,0,0,0},
            {10,6,8,0,0},
            {2,18,9,5,0},
            {19,7,10,4,16}
    };

    public static void main(String[] args) {
//        getMax(5);
        getMin(5);
    }

    public static void getMax(int row){
        for(int i = row-1; i > 0; i--){//从倒数第二行开始计算最大值
            for (int j = 0; j < i; j++){
                data[i-1][j] += data[i][j] > data[i][j+1] ? data[i][j] : data[i][j+1];//重置数组内容为当前节点到最后一行的最大值
            }
        }
        System.out.println(data[0][0]);
    }

    public static void getMin(int row){
        for(int i = row-1; i > 0; i--){//从倒数第二行开始计算最小值
            for (int j = 0; j < i; j++){
                data[i-1][j] += data[i][j] < data[i][j+1] ? data[i][j] : data[i][j+1];//重置数组内容为当前节点到最后一行的最小值
            }
        }
        System.out.println(data[3][0]);
    }
}
