package com.tony.basis.class07;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/14 10:58
 * Description: 二叉树递归，前序、中序、后序遍历
 */
public class Code01_RecursiveTraversalBinaryTree {

    /**
     * 二叉树递归前序遍历
     * @param head
     */
    public static void preorderTraversal(Node head){
        if(head==null){
            return;
        }
        System.out.println(head.value);
        preorderTraversal(head.left);
        preorderTraversal(head.right);
    }

    /**
     * 二叉树递归中序遍历
     * @param head
     */
    public static void inorderTraversal(Node head){
        if(head==null){
            return;
        }
        inorderTraversal(head.left);
        System.out.println(head.value);
        inorderTraversal(head.right);
    }

    /**
     * 二叉树递归后序遍历
     * @param head
     */
    public static void postorderTraversal(Node head){
        if(head==null){
            return;
        }
        postorderTraversal(head.left);
        postorderTraversal(head.right);
        System.out.println(head.value);
    }



    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        preorderTraversal(head);
        System.out.println("========");
        inorderTraversal(head);
        System.out.println("========");
        postorderTraversal(head);
        System.out.println("========");
    }
}