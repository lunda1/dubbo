package com.liupeng.learning.algorithm.greedy;

import com.alibaba.dubbo.common.utils.CollectionUtils;

import java.util.*;

/**
 * @Author oliver.liu
 * @Date 2019/9/4 17:18
 * https://www.jianshu.com/p/5ad3e97d54a3
 */
public class HuffmanTest {
    public static String[] nameArr = new String[]{"A","B","C","D"};
    public static int[] weightArr = new int[]{7,5,2,4};
    public static HuffmanNode[] ori = new HuffmanNode[4];
    public static LinkedList<HuffmanNode> tmp = new LinkedList<>();

    public static void main(String[] args) {
        HuffmanNode root = buildRoot();
        printRoot(root);
    }

    public static void printRoot(HuffmanNode root){
        LinkedList<HuffmanNode> queue = new LinkedList<>();
        queue.add(root);

        int level = 1;
        while (CollectionUtils.isNotEmpty(queue)) {
            int tmpLevel = level;
            StringBuilder sb = new StringBuilder();
            while (tmpLevel > 0) {
                sb.append(" ");
                tmpLevel--;
            }
            int count = queue.size();
            List<HuffmanNode> list = new ArrayList<>();
            System.out.println("");
            while (count > 0) {
                HuffmanNode tmpNode = queue.poll();
                System.out.print(sb.toString()+tmpNode.getName()+","+tmpNode.getWeigh());

                if (tmpNode.getLeft() != null) {
                    list.add(tmpNode.getLeft());
                }
                if (tmpNode.getRight() != null) {
                    list.add(tmpNode.getRight());
                }
                count--;
            }
            queue.addAll(list);
            level++;
        }
    }

    public static HuffmanNode buildRoot(){
        HuffmanNode root = new HuffmanNode("root",999,false,null,null,null);
        initData();

        if (CollectionUtils.isEmpty(tmp) || tmp.size() == 1) {
            return root;
        } else {
            while (CollectionUtils.isNotEmpty(tmp)) {
                Collections.sort(tmp, new HuffmanNodeComparator());
                HuffmanNode a1 = tmp.pollFirst();
                HuffmanNode a2 = tmp.pollFirst();

                HuffmanNode tmpNode = new HuffmanNode("",a1.getWeigh()+a2.getWeigh(),false,null,a1,a2);
                a1.setParent(tmpNode);
                a2.setParent(tmpNode);

                if (tmp.size() == 0) {
                    root = tmpNode;
                    return root;
                }

                tmp.add(tmpNode);
            }

        }

        return root;
    }

    public static void initData(){
        ori[0] = new HuffmanNode(nameArr[0],weightArr[0],true,null,null,null);
        ori[1] = new HuffmanNode(nameArr[1],weightArr[1],true,null,null,null);
        ori[2] = new HuffmanNode(nameArr[2],weightArr[2],true,null,null,null);
        ori[3] = new HuffmanNode(nameArr[3],weightArr[3],true,null,null,null);
        tmp.addAll(Arrays.asList(ori));
    }
}

class HuffmanNode{
    String name;
    int weigh;
    boolean isLeaf;
    HuffmanNode parent;
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(String name, int weigh, boolean isLeaf, HuffmanNode parent, HuffmanNode left, HuffmanNode right) {
        this.name = name;
        this.weigh = weigh;
        this.isLeaf = isLeaf;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeigh() {
        return weigh;
    }

    public void setWeigh(int weigh) {
        this.weigh = weigh;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public HuffmanNode getParent() {
        return parent;
    }

    public void setParent(HuffmanNode parent) {
        this.parent = parent;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }
}

class HuffmanNodeComparator implements Comparator<HuffmanNode> {

    @Override
    public int compare(HuffmanNode o1, HuffmanNode o2) {
        return o1.getWeigh() - o2.getWeigh();
    }
}