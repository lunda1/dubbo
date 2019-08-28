package com.liupeng.learning.algorithm.sort.exchange;

import com.alibaba.fastjson.JSON;

/**
 * @Author oliver.liu
 * @Date 2019/7/3 16:45
 */
public class TestQuickSort {


    public static void main(String[] args) {
        int[] arr = new int[]{3,2,4,1,5,8,7,6,9};
        System.out.println(JSON.toJSONString(arr));
        quickSort(arr,0,arr.length-1);
        System.out.println(JSON.toJSONString(arr));
    }

    public static void quickSort(int[] arr, int s, int e){
        if (s < e) {
            int i = s;
            int j = e;
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
            quickSort(arr, s,i-1);
            quickSort(arr,i+1, e);
        }
    }
}
