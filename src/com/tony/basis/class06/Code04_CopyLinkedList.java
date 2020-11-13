package com.tony.basis.class06;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/13 14:55
 * Description: 拷贝无环链表
 */
public class Code04_CopyLinkedList {

    private static class Node {
        private int value;
        private Node next;
        private Node rand;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 笔试使用
     * 利用哈希表对链表进行拷贝
     * 缺点：额外空间复杂度O(N)
     * @param head
     * @return
     */
    public static Node copyList1(Node head) {
        if (head == null) {
            return null;
        }
        Node current = head;
        Map<Node, Node> map = new HashMap<>();
        while (current != null) {
            map.put(current, new Node(current.value));
            current = current.next;
        }

        current = head;
        while (current != null) {
            map.get(current).next = map.get(current.next);
            map.get(current).rand = map.get(current.rand);
            current = current.next;
        }
        return map.get(head);
    }

    /**
     * 面试使用
     * 额外空间复杂度O(1)
     * 1.在本链表中增加要拷贝的元素
     * 2.复制好后再将指针连接关系进行分离变成原链表，要拷贝的链表
     * 3.返回拷贝好的链表头节点
     * @param head
     * @return
     */
    public static Node copyList2(Node head){
        if(head == null){
            return null;
        }
        Node current = head;
        while (current != null){
            Node newNode = new Node(current.value);
            newNode.next = current.next;
            newNode.rand = current.rand;
            current.next = newNode;
            current = current.next.next;
        }

        /**
         * copy:要拷贝的节点
         * next:链表中对应的下一个节点，因为结果是1-1'-2-2'-3-3',所以要去下一个的下一个
         * 真个目的是将原链表与拷贝的链表分离：1-2-3，1'-2'-3'
         */
        current = head;
        Node newHead = head.next;

        Node copy;
        Node next;
        while (current != null){
            copy = current.next;
            next = current.next.next;
            current.next = next;
            copy.next = next != null ? next : null;
            current = next;

        }
        return newHead;
    }

    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Node head = null;
        Node res1 = null;
        Node res2 = null;
        printRandLinkedList(head);
        res1 = copyList1(head);
        printRandLinkedList(res1);
        res2 = copyList2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        printRandLinkedList(head);
        res1 = copyList1(head);
        printRandLinkedList(res1);
        res2 = copyList2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");
    }


}
