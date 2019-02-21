package com.liupeng.learning.algorithm;

import com.alibaba.fastjson.JSON;

import java.util.LinkedList;

/**
 * @Author oliver.liu
 * @Date 2019/2/21 17:42
 */
public class TestWindow {
    public static void main(String[] args) {
        int[] ori = new int[]{5,6,6,4,7,3,2,6,8,4};
        int window_size = 3;
        int index = 0;
        LinkedList<Integer> q = new LinkedList<>();
        int[] res = new int[ori.length-window_size+1];
        for (int i = 0; i < res.length; i++) {
            res[i] = -1;
        }
        for (int i = 0; i < ori.length; i++) {
            if (q.isEmpty()) {
                q.addLast(i);
            } else if (ori[q.peekLast()] >= ori[i]) {
                q.addLast(i);
            } else {
                while (!q.isEmpty() && ori[q.peekLast()] < ori[i]) {
                    q.removeLast();
                }
                q.addLast(i);
            }

            if (i >= window_size - 1) {
                if (q.peekFirst() >= i-window_size+1) {
                    res[index++] = q.peekFirst();
                } else if (q.peekFirst() < i-window_size+1) {
                    q.pollFirst();
                    res[index++] = q.peekFirst();
                }
            }

        }//for

        System.out.println(JSON.toJSONString(res));
    }

}
