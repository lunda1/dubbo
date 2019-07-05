package com.liupeng.learning.algorithm.insert;

import com.alibaba.fastjson.JSON;
import com.liupeng.learning.algorithm.AlgorithmConst;

/**
 * @Author oliver.liu
 * @Date 2019/7/5 17:42
 */
public class TestSimpleInsert {
    public static void main(String[] args) {
        int[] origin = AlgorithmConst.getOriginArr();
        System.out.println(JSON.toJSONString(origin));
        simpleInsert(origin);
        System.out.println(JSON.toJSONString(origin));
    }

    public static void simpleInsert(int[] arr){
        for (int i=1;i<arr.length;i++) {
            int tmp = arr[i];
            int j;
            for (j=i-1;j>=0&&arr[j]>tmp;j--) {
                arr[j+1] = arr[j];
            }
            arr[j+1] = tmp;
        }
    }
}
