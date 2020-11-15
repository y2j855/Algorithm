package com.tony.basis.class07;

import java.util.*;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/15 19:49
 * Description: 序列化反序列化二叉树
 */
public class Code04_SerializeAndReconstructTree {

    /**
     * 先序序列化对外接口
     * @param head
     * @return
     */
    public static Queue<Node> preorderSerial(Node head){
        Queue<Node> serial = new LinkedList<>();
        preorderSerial(head,serial);
        return serial;
    }
    /**
     * 先序序列化二叉树
     * 用非递归的方式实现会有很多坑，果断采取递归方式序列化
     * 递归方法设计时，要考虑base case，就是当节点为空把null值赋值给数组保证二叉树的结构
     * 用队列存反序列化时比较方便
     * @param head
     * @param serial
     */
    public static void preorderSerial(Node head, Queue<Node> serial){
        //先序遍历二叉树，发现节点为null,给它用null赋值
        if(head == null){
            serial.add(null);
        }else{
            serial.add(head);
            preorderSerial(head.left, serial);
            preorderSerial(head.right, serial);
        }
    }

    /**
     * 反序列化对外接口
     * @param serial
     * @return
     */
    public static Node preorderUnSerial(Queue<Node> serial){
        if(serial == null){
            return null;
        }
        return preUnSerial(serial);
    }

    /**
     * 反序列化一定要与序列化匹配
     * 序列化用的是前序，反序列化也要用前序
     * @param serial
     * @return
     */
    public static Node preUnSerial(Queue<Node> serial){
        Node head = serial.poll();
        if(head == null){
            return null;
        }else{//可以不写左半部，因为queue存的就是Node对象，如果只存值就要写
            head.left = preUnSerial(serial);
            head.right = preUnSerial(serial);
        }
        return head;
    }

    /**
     * 按层遍历二叉树序列化
     * @param head
     * @return
     */
    public static Queue<String> levelSerial(Node head){
        Queue<String> serial = new LinkedList<>();
        Queue<Node> data = new LinkedList<>();
        data.add(head);
        serial.add(String.valueOf(head.value));
        while (!data.isEmpty()){
            Node current = data.poll();
            if(current.left != null){
                data.add(current.left);
            }
            if(current.right != null){
                data.add(current.right);
            }
            serial.add(current.left != null ? String.valueOf(current.left.value) : null);
            serial.add(current.right != null ? String.valueOf(current.right.value) : null);
        }
        return serial;
    }

    /**
     * 反序列化按层遍历二叉树
     * 1.重构代码的重要性
     * 2.序列化是什么方式，反序列化就是什么方式
     * @param serial
     * @return
     */
    public static Node levelUnSerial(Queue<String> serial){
        Queue<Node> data = new LinkedList<>();
        if(serial ==null || serial.isEmpty()){
            return null;
        }
        Node head = generateNode(serial.poll());
        data.add(head);
        while (!data.isEmpty()){
            Node current = data.poll();
//            if(current != null && current.left == null){
//                current.left = generateNode(serial.poll());
//                data.add(current.left);
//            }
//            if(current != null && current.right == null){
//                current.right = generateNode(serial.poll());
//                data.add(current.right);
//            }
            if(current != null){
                current.left = generateNode(serial.poll());
                data.add(current.left);
                current.right = generateNode(serial.poll());
                data.add(current.right);
            }
        }
        return head;
    }

    private static Node generateNode(String value) {
        if(value == null){
            return null;
        }
        return new Node(Integer.valueOf(value));
    }


    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node left = new Node(2);
        Node right = new Node(3);
        head.left = left;
        left.right = right;

        Queue<Node> pre = preorderSerial(head);
        System.out.println(pre.size());

        Node head1 = preorderUnSerial(pre);
        System.out.println(head1);

        Queue<String> queue = levelSerial(head);
        System.out.println(queue.size());

        Node node = levelUnSerial(queue);
        System.out.println(node);
    }
}
