package com.liupeng.learning.algorithm.sort;

/**
 * @Author oliver.liu
 * @Date 2019/7/10 17:02
 */
public class ReverseNodeTest {
    public static void main(String[] args) {
        Node h1 = new Node("");
        Node n1 = new Node("n1");
        Node n2 = new Node("n2");
        Node n3 = new Node("n3");
        Node n4 = new Node("n4");
        h1 = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        Node tmp = h1;
        printNode(tmp);

        Node h2 = new Node("");
        tmp = h1;
        while (tmp != null) {
            h1 = tmp.next;
            tmp.next = h2;
            h2 = tmp;
            tmp = h1;
        }

        tmp = h2;
        printNode(tmp);

    }

    public static void printNode(Node n){
        System.out.println("");
        while (n != null) {
            System.out.print(n.name+" ");
            n = n.next;
        }
    }
}

class Node{
    String name;
    Node next;

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
