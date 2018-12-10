package com.liupeng.learning.sort;

import com.alibaba.fastjson.JSON;

public class QuickSortTest {
    public static void main(String[] args) {
        int[] arr = Constants.DISORDER_ARR;
        System.out.println(JSON.toJSONString(arr));
        quickSort(arr,0,arr.length-1);
        System.out.println(JSON.toJSONString(arr));
    }

    public static void quickSort(int[] arr, int start, int end){
        if (start < end) {
            int i = start;
            int j = end;
            int tmp = arr[i];
            while (i < j) {
                while (i < j && arr[j] >= tmp) {
                    j--;
                }
                if (i < j) {
                    arr[i++] = arr[j];
                }
                while (i < j && arr[i] < tmp) {
                    i++;
                }
                if (i < j) {
                    arr[j--] = arr[i];
                }
            }
            arr[i] = tmp;
            quickSort(arr,start,i-1);
            quickSort(arr,i+1,end);
        }
    }
}
