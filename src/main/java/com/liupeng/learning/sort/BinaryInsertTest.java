package com.liupeng.learning.sort;

import com.alibaba.fastjson.JSON;

public class BinaryInsertTest {
    public static void main(String[] args) {
        int[] arr = Constants.DISORDER_ARR;
        System.out.println(JSON.toJSONString(arr));
        binaryInsertSort(arr);
        System.out.println(JSON.toJSONString(arr));
    }

    public static int binarySearch(int[] arr, int target, int start, int end){
        int i = start, j = end, mid;

        while (i<=j) {
            mid = (i + j) / 2;
            if (target < arr[mid]) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }

        return i;//i肯定为大于target的位置
    }

    public static void binaryInsertSort(int[] arr){
        int length = arr.length;
        for (int i=1; i < length; i++) {
            if (arr[i] < arr[i-1]) {
                int temp = arr[i];
                int j = i - 1;
                int pos = binarySearch(arr,temp,0,i-1);
                for (;j>=pos;j--) {
                    arr[j+1] = arr[j];
                }
                arr[j+1] = temp;
            }
        }
    }

}
