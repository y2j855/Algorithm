package com.tony.basis.class07;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/15 14:58
 * Description: 按层遍历二叉树
 */
public class Code03_LevelTraversalBinaryTree {

    /**
     * 按层遍历打印二叉树,通过队列遍历
     * @param head
     */
    public static void level(Node head){
        if(head==null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()){
            Node current = queue.poll();
            System.out.println(current.value);
            if(current.left != null){
                queue.add(current.left);
            }
            if(current.right != null){
                queue.add(current.right);
            }
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        level(head);
        System.out.println("========");
    }

}
