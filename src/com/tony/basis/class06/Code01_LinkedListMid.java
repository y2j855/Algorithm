package com.tony.basis.class06;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/12 16:37
 * Description: 快慢指针的使用，单链表
 * 下边的四个方法就是在纸上一点一点试出来的，没有太明确的思路
 */
public class Code01_LinkedListMid {

    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
     * 面试时使用这个方法，额外空间复杂度为O(1)
     *
     * @param head
     * @return 如果是奇数返回中点，如果是偶数返回上中点
     */
    public static Node midOrUpMidNode(Node head) {
        /**
         * 如果head节点为空，或者只有一个head节点(奇数)，或者只有2个节点(偶数)
         * 都返回head节点
         */
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        /*定义慢指针，快指针*/
        Node slowPointer = head;
        Node fastPointer = head;
        while (fastPointer.next != null && fastPointer.next.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }
        return slowPointer;
    }

    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
     * 面试时使用这个方法，额外空间复杂度为O(1)
     *
     * @param head
     * @return
     */
    public static Node midOrDownMidNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        /*定义慢指针，快指针*/
        Node slowPointer = head.next;
        Node fastPointer = head.next;
        while (fastPointer.next != null && fastPointer.next.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }
        return slowPointer;
    }

    /**
     * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
     * 面试时使用这个方法，额外空间复杂度为O(1)
     *
     * @param head
     * @return
     */
    public static Node midOrUpMidPreNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        Node slowPointer = head;
        Node fastPointer = head.next.next;
        while (fastPointer.next != null && fastPointer.next.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }
        return slowPointer;
    }

    /**
     * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
     * 面试时使用这个方法，额外空间复杂度为O(1)
     *
     * @param head
     * @return
     */
    public static Node midOrDownMidPreNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        Node slowPointer = head;
        Node fastPointer = head.next;
        while (fastPointer.next != null && fastPointer.next.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }
        return slowPointer;
    }

    //笔试时，可以这样写
    public static Node right1(Node head) {
        if (head == null) {
            return null;
        }
        Node current = head;
        List<Node> array = new ArrayList<>();
        while (current != null) {
            array.add(current);
            current = current.next;
        }
        return array.get((array.size() - 1) / 2);
    }

    public static Node right2(Node head) {
        if (head == null) {
            return null;
        }
        Node current = head;
        List<Node> array = new ArrayList<>();
        while (current != null) {
            array.add(current);
            current = current.next;
        }
        return array.get(array.size() / 2);
    }

    public static Node right3(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node current = head;
        List<Node> array = new ArrayList<>();
        while (current != null) {
            array.add(current);
            current = current.next;
        }
        return array.get((array.size() - 3) / 2);
    }

    public static Node right4(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 2) / 2);
    }



    public static void main(String[] args) {
        Node test = null;
        test = new Node(0);
        test.next = new Node(1);
        test.next.next = new Node(2);
        test.next.next.next = new Node(3);
        test.next.next.next.next = new Node(4);
        test.next.next.next.next.next = new Node(5);
        test.next.next.next.next.next.next = new Node(6);
        test.next.next.next.next.next.next.next = new Node(7);
        test.next.next.next.next.next.next.next.next = new Node(8);

        Node ans1 = null;
        Node ans2 = null;

        ans1 = midOrUpMidNode(test);
        ans2 = right1(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");

        ans1 = midOrDownMidNode(test);
        ans2 = right2(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");

        ans1 = midOrUpMidPreNode(test);
        ans2 = right3(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");

        ans1 = midOrDownMidPreNode(test);
        ans2 = right4(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");
    }
}
