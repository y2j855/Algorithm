package com.tony.basis.class06;

import java.util.Stack;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/12 18:31
 * Description: 链表中是否有回文
 * 回文定义：12321,abcba,123aa321
 */
public class Code02_IsPalindromeList {

    /**
     * 笔试可以使用
     * 通过栈来实现回文，利用栈先进后出的特性
     * 缺点:时间复杂度O(N),额外空间复杂度O(N)
     *
     * @param head
     * @return
     */
    public static boolean isPalindromeByStack(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node current = head;
        Stack<Node> stack = new Stack<>();
        while (current != null) {
            stack.push(current);
            current = current.next;
        }
        while (head != null) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 利用快慢指针和栈，实现判断链表中是否为回文结构
     * 时间复杂度O(N/2),空间复杂度(N/2)
     *
     * @param head
     * @return
     */
    public static boolean isPalindromeByPointer(Node head) {
        //获取链表中点节点
        Node middleNode = Code01_LinkedListMid.midOrUpMidNode(head);
        //将中点右边的位置加入到栈中
        Stack<Node> stack = new Stack<>();
        while (middleNode != null) {
            stack.push(middleNode);
            middleNode = middleNode.next;

        }
        //中点左边与栈进行比较
        while (!stack.isEmpty()) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 最优解:面试时可以使用
     * 利用快慢指针获取中点节点，反序原链表的中点右边的节点，然后左右节点进行比较判断是否是回文
     * 时间复杂度为O(N),空间复杂度为O(1)
     * @param head
     * @return
     */
    public static boolean isPalindromeByLinkedList(Node head) {
        boolean flag = true;
        Node current = head;
        //获取链表中点节点
        Node midNode = Code01_LinkedListMid.midOrUpMidNode(head);
        //将中点节点右边的节点进行反序,返回原先的尾节点，也是现在的新头节点
        Node tailNode = reverseLinkedList(midNode);
        Node rightNode = tailNode;
        //中点节点左边的节点与右边的节点进行比较，如果都相等就是回文
        while (current != null){
            if(current.value != rightNode.value){
                flag = false;
                break;
            }
            current = current.next;
            rightNode = rightNode.next;
        }
        //将之前反序的节点再还原回来
        reverseLinkedList(tailNode);
        return flag;
    }

    private static Node reverseLinkedList(Node head) {
        Node newHead = null;
        Node preNode = null;
        Node nextNode;
        while (head != null){
            nextNode = head.next;
            head.next = preNode;
            preNode = head;
            if(nextNode == null){
                newHead = head;
            }
            head = nextNode;
        }
        return newHead;
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Node head = null;
        printLinkedList(head);
        System.out.print(isPalindromeByStack(head) + " | ");
        System.out.print(isPalindromeByPointer(head) + " | ");
        System.out.println(isPalindromeByLinkedList(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindromeByStack(head) + " | ");
        System.out.print(isPalindromeByPointer(head) + " | ");
        System.out.println(isPalindromeByLinkedList(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        System.out.print(isPalindromeByStack(head) + " | ");
        System.out.print(isPalindromeByPointer(head) + " | ");
        System.out.println(isPalindromeByLinkedList(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindromeByStack(head) + " | ");
        System.out.print(isPalindromeByPointer(head) + " | ");
        System.out.println(isPalindromeByLinkedList(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.print(isPalindromeByStack(head) + " | ");
        System.out.print(isPalindromeByPointer(head) + " | ");
        System.out.println(isPalindromeByLinkedList(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindromeByStack(head) + " | ");
        System.out.print(isPalindromeByPointer(head) + " | ");
        System.out.println(isPalindromeByLinkedList(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindromeByStack(head) + " | ");
        System.out.print(isPalindromeByPointer(head) + " | ");
        System.out.println(isPalindromeByLinkedList(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindromeByStack(head) + " | ");
        System.out.print(isPalindromeByPointer(head) + " | ");
        System.out.println(isPalindromeByLinkedList(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindromeByStack(head) + " | ");
        System.out.print(isPalindromeByPointer(head) + " | ");
        System.out.println(isPalindromeByLinkedList(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

    }
}
