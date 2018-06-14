package com.liupeng.learning.basic;

import org.springframework.util.CollectionUtils;

import java.util.List;

public class TestForNull {
    public static void main(String[] args) {
        List<Integer> list = null;
        if (!CollectionUtils.isEmpty(list)) {
            for (Integer l : list) {
                System.out.println(l);

            }
        }
    }
}
