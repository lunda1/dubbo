package com.liupeng.learning.algorithm.insert;

import com.alibaba.fastjson.JSON;
import com.liupeng.learning.algorithm.AlgorithmConst;

/**
 * @Author oliver.liu
 * @Date 2019/7/5 17:42
 */
public class TestTwoWayInsert {
    public static void main(String[] args) {
        int[] origin = AlgorithmConst.getOriginArr();
        System.out.println(JSON.toJSONString(origin));
        origin = twoWayInsert(origin);
        System.out.println(JSON.toJSONString(origin));
    }

    public static int[] twoWayInsert(int[] arr){
        int[] circle = new int[arr.length];
        int length = arr.length;

        circle[0] = arr[0];
        int first = 0, last = 0;
        for (int i=1;i<arr.length;i++) {
            if (arr[i]<=circle[first]) {
                circle[(first = (first-1+length)%length)] = arr[i];
            } else if (arr[i]>=circle[last]) {
                circle[(last = (last+1+length)%length)] = arr[i];
            } else {
                int j;
                for (j=last;arr[i]<circle[j];j=(j-1+length)%length) {
                    circle[(j+1+length)%length] = circle[j];
                }
                circle[(j+1+length)%length] = arr[i];
                last = (last+1+length)%length;
            }
        }

        for (int i=0;i<arr.length;i++) {
            arr[i] = circle[(first+i+length)%length];
        }
        return arr;
    }

}
