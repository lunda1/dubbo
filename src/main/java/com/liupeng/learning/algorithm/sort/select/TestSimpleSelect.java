package com.liupeng.learning.algorithm.sort.select;

import com.alibaba.fastjson.JSON;
import com.liupeng.learning.algorithm.sort.AlgorithmConst;

/**
 * @Author oliver.liu
 * @Date 2019/7/5 17:32
 */
public class TestSimpleSelect {
    public static void main(String[] args) {
        int[] origin = AlgorithmConst.getOriginArr();
        System.out.println(JSON.toJSONString(origin));
        simpleSelect(origin);
        System.out.println(JSON.toJSONString(origin));
    }

    public static void simpleSelect(int[] arr){
        for (int i=arr.length-1;i>0;i--) {
            int largest = i;
            for (int j=0;j<i;j++) {
                if (arr[j]>arr[largest]) {
                    largest = j;
                }
            }
            if (largest != i) {
                int tmp = arr[largest];
                arr[largest] = arr[i];
                arr[i] = tmp;
            }
        }
    }
}
