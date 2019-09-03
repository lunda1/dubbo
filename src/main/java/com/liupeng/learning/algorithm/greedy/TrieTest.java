package com.liupeng.learning.algorithm.greedy;

import com.google.common.base.Strings;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author oliver.liu
 * @Date 2019/9/3 14:25
 * https://www.jianshu.com/p/bbfe4874f66f
 */
public class TrieTest {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("improvement");
        System.out.println("exist: "+trie.exist("improvement"));
        System.out.println("existStartWith: "+trie.existStartWith("impr"));
        System.out.println("existStartWith: "+trie.existStartWith("mpr"));
    }
}

class TrieNode {
    boolean isLeaf;
    //记录当前节点的下一个节点的map，比如root节点下有e,f,g三个节点，则content中包含e,f,g三个key
    Map<Character,TrieNode> content;

    public TrieNode() {
        this.content = new HashMap<Character, TrieNode>();
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    //将单词插入到Trie树中
    public void insert(String word){
        if (Strings.isNullOrEmpty(word)) {
            return;
        }

        //node用于循环使用，所以起名叫做node，最开始是从root开始
        TrieNode node = root;
        TrieNode tmpNode = null;
        for (int i=0;i<word.length();i++) {
            Character c = word.charAt(i);
            tmpNode = node.content.get(c);
            if (tmpNode == null) {
                tmpNode = new TrieNode();
                node.content.put(c,tmpNode);
            }
            node = tmpNode;
        }
        node.isLeaf = true;
    }

    public boolean exist(String word){
        if (Strings.isNullOrEmpty(word)) {
            return false;
        }

        TrieNode node = root;
        TrieNode tmpNode = null;
        for (int i=0;i<word.length();i++) {
            tmpNode = node.content.get(word.charAt(i));
            if (tmpNode == null) {
                return false;
            }
            node = tmpNode;
        }
        return node.isLeaf;
    }

    public boolean existStartWith(String word) {
        if (Strings.isNullOrEmpty(word)) {
            return false;
        }

        TrieNode node = root;
        TrieNode tmpNode = null;
        for (int i=0;i<word.length();i++) {
            tmpNode = node.content.get(word.charAt(i));
            if (tmpNode == null) {
                return false;
            }
            node = tmpNode;
        }

        return true;
    }
}