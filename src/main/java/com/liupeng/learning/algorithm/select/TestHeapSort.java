package com.liupeng.learning.algorithm.select;

import com.alibaba.fastjson.JSON;

/**
 * Seq代表节点的排序，从1开始。所以节点的实际索引为seq-1。
 * @Author oliver.liu
 * @Date 2019/7/3 19:34
 */
public class TestHeapSort {

    public static void main(String[] args) {
        int[] arr = new int[]{3,2,1,7,6,5,4,8,9};
        System.out.println(JSON.toJSONString(arr));
        sort(arr);
        System.out.println(JSON.toJSONString(arr));
    }

    public static void sort(int[] arr){
        buildMaxHeap(arr);
        for (int i=arr.length-1;i>0;i--) {
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            maxifyHeap(arr,1, i);
        }
    }

    public static void buildMaxHeap(int[] arr){
        for (int i=arr.length/2;i>0;i--) {
            maxifyHeap(arr,i,arr.length);
        }
    }

    public static void maxifyHeap(int[] arr, int fromSeq, int length){
        int largestSeq = fromSeq;

        int leftSeq = fromSeq * 2;

        int rightSeq = leftSeq + 1;

        if (leftSeq <= length && arr[leftSeq-1] > arr[largestSeq-1]) {
            largestSeq = leftSeq;
        }

        if (rightSeq <= length && arr[rightSeq-1] > arr[largestSeq-1]) {
            largestSeq = rightSeq;
        }

        if (largestSeq != fromSeq) {
            int tmp = arr[fromSeq-1];
            arr[fromSeq-1] = arr[largestSeq-1];
            arr[largestSeq-1] = tmp;
            maxifyHeap(arr,largestSeq,length);
        }

    }

}
