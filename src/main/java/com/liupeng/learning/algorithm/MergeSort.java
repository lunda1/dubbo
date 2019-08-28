package com.liupeng.learning.algorithm;

import com.alibaba.fastjson.JSON;

/**
 * @Author oliver.liu
 * @Date 2019/7/10 15:38
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] origin = AlgorithmConst.getOriginArr();
        System.out.println(JSON.toJSONString(origin));
        mergeSort(origin);
        System.out.println(JSON.toJSONString(origin));
    }

    public static void mergeSort(int[] arr){
        sort(arr,0,arr.length-1);
    }

    public static void sort(int[] arr, int start, int end){
        if (start >= end) {
            return;
        }
        int mid = (start+end)/2;
        sort(arr,start,mid);
        sort(arr,mid+1,end);
        merge(arr,start,mid,end);

    }

    public static void merge(int[] arr, int start, int mid, int end){
        int[] tmp = new int[end-start+1];
        int i=start,j=mid+1,k=0;
        while (i<=mid&&j<=end) {
            if (arr[i]<arr[j]) {
                tmp[k++] = arr[i++];
            } else {
                tmp[k++] = arr[j++];
            }
        }
        while (i<=mid)  {
            tmp[k++] = arr[i++];
        }
        while(j<=end){
            tmp[k++] = arr[j++];
        }
        i = 0;
        while(i<tmp.length){
            arr[start+i] = tmp[i++];
        }
    }
}
