package com.liupeng.learning.algorithm.greedy;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author oliver.liu
 * @Date 2019/8/30 16:53
 */
public class RegionFullCoverTest {

    public static void main(String[] args) {
        int[] barr = new int[]{2,1,3,3,6,2,3};
        int[] earr = new int[]{6,4,6,7,8,4,5};
        List<Region> regionList = new ArrayList<>();
        Region r;
        for (int i=0;i<barr.length;i++) {
            r = new Region();
            r.setLeft(barr[i]);
            r.setRight(earr[i]);
            regionList.add(r);
        }
        regionList.sort(new Comparator<Region>() {
            @Override
            public int compare(Region o1, Region o2) {
                return o1.getLeft().equals(o2.getLeft()) ? o1.getRight() - o2.getRight() : o1.getLeft() - o2.getLeft() ;
            }
        });

        System.out.println("after sort: "+ JSON.toJSONString(regionList));

        int total = regionList.size();
        List<Region> results = new LinkedList<>();
        int index = 0;
        Region last = null;
        Integer lastIndex = -1;
        Region tmp = null;
        Integer tmpIndex = 0;
        for (int i=0;i<regionList.size();i++) {
            if (lastIndex == -1) {
                results.add(regionList.get(i));
                last = ((LinkedList<Region>) results).getLast();
                lastIndex = i;
                tmp = last;
            } else {
                for (int j=i;j<regionList.size();j++) {
                    if (regionList.get(j).getLeft() >= last.getLeft() &&  regionList.get(j).getLeft() <= last.getRight() && regionList.get(j).getRight() > last.getRight()) {
                        if (regionList.get(j).getRight() > tmp.getRight()) {
                            tmp = regionList.get(j);
                            tmpIndex = j;
                        }
                    }
                }//for

                i=tmpIndex;

                if (tmp != last) {
                    results.add(tmp);
                    last = tmp;
                    lastIndex = tmpIndex;
                }
            }
        }//for

        System.out.println("cover regions: "+JSON.toJSONString(results));

    }
}

class Region{
    Integer left;
    Integer right;

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }
}
