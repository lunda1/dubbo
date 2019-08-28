package com.liupeng.learning.algorithm.sort.insert;

import com.alibaba.fastjson.JSON;
import com.liupeng.learning.algorithm.sort.AlgorithmConst;

/**
 * @Author oliver.liu
 * @Date 2019/7/5 17:42
 */
public class TestShellInsert {
    public static void main(String[] args) {
        int[] origin = AlgorithmConst.getOriginArr();
        System.out.println(JSON.toJSONString(origin));
        shellInsert(origin);
        System.out.println(JSON.toJSONString(origin));
    }

    public static void shellInsert(int[] arr){
        int len = arr.length;
        for (int gap=len/2;gap>0;gap/=2) {
            for (int i=gap;i<len;i++) {
                int j;
                int tmp = arr[i];
                for (j=i-gap;j>=0&&tmp<arr[j];j-=gap) {
                    arr[j+gap] = arr[j];
                }
                arr[j+gap]=tmp;
            }//for
        }
    }

}
