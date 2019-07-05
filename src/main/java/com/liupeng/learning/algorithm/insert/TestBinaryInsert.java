package com.liupeng.learning.algorithm.insert;

import com.alibaba.fastjson.JSON;
import com.liupeng.learning.algorithm.AlgorithmConst;

/**
 * @Author oliver.liu
 * @Date 2019/7/5 17:42
 */
public class TestBinaryInsert {
    public static void main(String[] args) {
        int[] origin = AlgorithmConst.getOriginArr();
        System.out.println(JSON.toJSONString(origin));
        binaryInsert(origin);
        System.out.println(JSON.toJSONString(origin));
    }

    public static void binaryInsert(int[] arr){
        for (int i=1;i<arr.length;i++) {
            int tmp = arr[i];
            int j;
            int pos = binarySearch(arr,0,i-1,tmp);
            for (j=i-1;j>=pos;j--) {
                arr[j+1] = arr[j];
            }
            arr[j+1] = tmp;
        }
    }

    public static int binarySearch(int[] arr, int s, int e, int target){
        int i = s;
        int j = e;
        while (i<=j) {
            int mid = (i+j)/2;
            if (arr[mid] > target) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }
        return i;
    }
}
