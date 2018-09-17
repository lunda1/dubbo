package com.liupeng.dubbo.api;

import com.alibaba.dubbo.rpc.cluster.Merger;

import java.util.ArrayList;
import java.util.List;

public class MyMerger implements Merger<List<Integer>> {
    @Override
    public List<Integer> merge(List<Integer>... lists) {
        ArrayList<Integer> result = new ArrayList<>();
        for (List<Integer> list : lists) {
            result.addAll(list);
        }

        return result;
    }
}
