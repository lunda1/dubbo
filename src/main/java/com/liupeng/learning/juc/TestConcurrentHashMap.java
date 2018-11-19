package com.liupeng.learning.juc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestConcurrentHashMap {
    public static void main(String[] args) {
        Map<Node,Integer> map = new ConcurrentHashMap<>();

        for (int i=0;i<8;++i) {
            map.put(new Node(i),i);
        }
        /**
         * 1.假设原链表大小为7，如果第8个元素放到了7的后面，则不会转换为红黑树，且添加新节点后链表长度为8;
         * 2.假设原链表大小为8，无论是更新最后一个节点的value还是添加新的节点，都会触发转换为红黑树.
         */
        map.put(new Node(10),10);

    }
}

class Node{
    public Node(int id) {
        this.id = id;
    }

    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Node node = (Node) o;
        return id == node.id;
    }

    @Override
    public int hashCode() {
        /** 返回相同的值，确保新添加的元素放到了同一个桶中 **/
        return 0;
    }
}
