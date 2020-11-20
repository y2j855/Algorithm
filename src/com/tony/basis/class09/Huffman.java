package com.tony.basis.class09;

import java.util.PriorityQueue;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/20 14:33
 * Description: 霍夫曼树
 */
public class Huffman {
    static PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
    static int[] C;
    static Node[] N;// 分支结构
    static Node root;// 根结点

    private static class Node {
        int key;
        Node left;
        Node right;

        public Node(int q) {
            this.key = q;
        }
    }

    // 初始化优先级队列,这里直接利用了Java内置的PriorityQueue
    public Huffman(int[] E) {
        C = E;
        N = new Node[C.length - 1];
        for (int i = 0; i < C.length; i++) {
            queue.offer(C[i]);
        }
    }

    // 建立Huffman树结构
    public void bulid() {
        // 每一个node都是一个独立的分支
        for (int i = 0; i < N.length; i++) {
            N[i] = new Node(0);
        }

        for (int i = 0; i < N.length; i++) {
            // 此处就是贪心算法的体现，选取当前看来最优的选择
            N[i].left = new Node(queue.poll());
            N[i].right = new Node(queue.poll());
            N[i].key = N[i].left.key + N[i].right.key;
            queue.offer(N[i].key);
        }

        // 把这些分支合并组成树结构(以N的最后一个元素为根结点)
        for (int i = N.length - 1; i >= 1; i--) {
            // 检查接下来的所有对象是不是它的左右结点
            for (int j = i - 1; j >= 0; j--) {
                if (N[i].left.key == N[j].key) {
                    N[i].left = N[j];
                }
                if (N[i].right.key == N[j].key) {
                    N[i].right = N[j];
                }
            }
            root = N[N.length - 1];
        }
    }

    // 构造Huffman编码
    public void code(String code, Node node, int i) {
        if (node == null) {
            return;
        }
        code(code + "0", node.left, i);
        code(code + "1", node.right, i);

        if (C[i] == node.key) {
            System.out.println(i + "(" + C[i] + ")" + "霍夫曼编码为" + code);
        }
    }

    private void key(int key, Node node, int index) {
        if (node == null){
            return;
        }
        key(0+node.key,node.left,index);
        key(0+node.key,node.right,index);
        if(C[index] == node.key){
            System.out.println(index+"(" + C[index] + ")" + "父节点的值" + key);
        }
    }

    public void printCode() {
        for (int i = 0; i < C.length; i++) {
            code("", root, i);
        }
    }

    public void printKey(){
        for (int i = 0; i < C.length; i++) {
            key(0,root,i);
        }
    }

    public static void main(String[] args) {
        Huffman hfm = new Huffman(new int[] { 9, 45, 12, 13, 5, 16 });
        hfm.bulid();
        hfm.printCode();
        hfm.printKey();
    }
}
