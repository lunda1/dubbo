package com.liupeng.learning.sort;

import com.alibaba.fastjson.JSON;

public class StraightInsertTest {
    public static void main(String[] args) {
        int[] arr = Constants.DISORDER_ARR;
        System.out.println(JSON.toJSONString(arr));
        straightInsersion(arr);
        System.out.println(JSON.toJSONString(arr));
    }

    public static void straightInsersion(int[] arr){
        int length = arr.length;
        for (int i=1; i<length; i++) {
            int temp = arr[i];
            int j = i -1;
            for (; j>=0 && arr[j]>temp; j--) {//如果当前元素大于temp，则后移
                arr[j+1] = arr[j];
            }
            arr[j+1] = temp;//跳出for循环时的j+1即为temp的位置
        }
    }
}
