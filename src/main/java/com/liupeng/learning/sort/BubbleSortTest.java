package com.liupeng.learning.sort;

import com.alibaba.fastjson.JSON;

public class BubbleSortTest {
    public static void main(String[] args) {
        int [] arr = Constants.DISORDER_ARR;
        System.out.println(JSON.toJSONString(arr));
        bubbleSort(arr);
        System.out.println(JSON.toJSONString(arr));
    }

    public static void bubbleSort(int[] arr){
        int length = arr.length;
        for (int i=1;i<length;i++) {
            for (int j=0;j<length-i;j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
}
