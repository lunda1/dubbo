package com.liupeng.learning.algorithm.sort.exchange;

import com.alibaba.fastjson.JSON;
import com.liupeng.learning.algorithm.sort.AlgorithmConst;

/**
 * @Author oliver.liu
 * @Date 2019/7/5 17:12
 */
public class TestBubble {

    public static void main(String[] args) {
        int[] origin = AlgorithmConst.getOriginArr();
        System.out.println(JSON.toJSONString(origin));
        bubbleSort2(origin);
        System.out.println(JSON.toJSONString(origin));
    }

    public static void bubbleSort(int[] arr){
        int k = 0;
        for (int i=arr.length-1;i>0;i--) {
            System.out.println(++k);
            int count = 0;
            for (int j=0;j<i-1;j++) {
                if (arr[j]>arr[j+1]) {
                    count++;
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
            if (count == 0) {
                break;
            }
        }
    }

    /**
     * 内部每次循环只进行到上次发生过交换的地方即可
     * @param arr
     */
    public static void bubbleSort2(int[] arr){
        int k = 0;
        int flag = arr.length-1;
        while (flag > 0) {
            System.out.println(++k);
            int count = 0;
            int i = flag;
            for (int j = 0; j < i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    count++;
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    flag = j+1;
                }
            }
            if (count == 0) {
                break;
            }
        }///while

    }


}
