package com.tony.basis.class06;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/13 21:22
 * Description:
 * 给定两个可能有环也可能无环的单链表，头节点head1和head2。
 * 请实现一个函数，如果两个链表相交，请返回相交的第一个节点。如果不相交，返回null。
 * 要求:如果两个链表长度之和为N，时间复杂度请达到O(N),额外空间复杂度请达到O(1).（难度比较高）
 * 这道题与约瑟夫环齐名，但约瑟夫环问题更难
 */
public class Code05_FindFirstIntersectNode {

    /**
     * 使用hashset判断是否有重复值，如果有证明有环
     * 判断单链表是否有环，有环返回入环节点
     *
     * @param head
     * @return null:无环 Node:入环节点
     */
    public static Node loopNode(Node head) {
        if (head == null) {
            return null;
        }
        Node loopNode = null;
        Set<Node> data = new HashSet<>();
        while (head != null) {
            if (data.contains(head)) {
                loopNode = head;
                break;
            }
            data.add(head);
            head = head.next;
        }
        return loopNode;
    }

    /**
     * 使用快慢指针来判断单链表中是否有环
     * 逻辑思路
     * 让快慢指针移动，当第一次相遇时让快指针回到头节点，每次移动一步
     * 快慢指针再相遇就是入环的节点
     *
     * @param head
     * @return
     */
    public static Node loopNodeByPointer(Node head) {
        if (head == null) {
            return null;
        }
        //定义快慢指针
        Node slow = head.next;
        Node fast = head.next.next;

        while (fast != slow) {
            //如果快指针遍历到链表最后了，证明链表没有环
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        /**
         * 1.判断2个单向链表是否有环
         * 2.如果2个链表都没有环，判断是否有公共部分，如果有证明相交，如果没有证明不相交
         * 3.如果一个链表有环，另一个没有环，肯定不会相交。
         * 4.如果2个链表相交，需要判断三种情况
         */
        Node intersectNode = null;
        Node node1 = loopNodeByPointer(head1);
        Node node2 = loopNodeByPointer(head2);

        if (node1 == null && node2 == null) {
            intersectNode = noLoop(head1, head2);
        }else if(node1 != null && node2 != null){
            intersectNode = bothLoop(node1,node2);
        }
        return intersectNode;

    }

    /**
     * 判断2个都有环的单向链表是否相交
     * 三种情况
     * 1.独立的2个不相交环链表
     * 2.有同一个入环节点的2个链表，证明它们相交
     * 3.不同入环节点的2个链表，通过循环遍历环，让loop1遍历，找loop2如果找到，证明它们在同一个环上，从而证明相交。如果没有证明是1情况。
     * @param loop1
     * @param loop2
     * @return
     */
    private static Node bothLoop(Node loop1, Node loop2) {
        Node intersectNode = null;
        //第二种情况
        if(loop1 == loop2){
            //TODO 逻辑错误,要找第一个相交节点，而不是最后一个
            intersectNode = loop1;
        }else{
            while (loop1 != null){
                if(loop1 == loop2){
                    intersectNode = loop1;
                    break;
                }
                loop1 = loop1.next;
            }
        }
        return intersectNode;
    }

    /**
     * 判断2个无环单向链表是否相交
     * 1.判断2个链表是否有公共部分，也就是end节点相等
     * 2.如果相等，通过length判断2个链表哪个大，大的先遍历差值
     * 3.然后一起遍历，2个链表的第一个相等值就是入环节点
     *
     * @param node1
     * @param node2
     * @return
     */
    private static Node noLoop(Node node1, Node node2) {
        //TODO 需要重构代码
        Node intersect = null;
        Node current1 = node1;
        Node current2 = node2;
        Node end1 = null;
        Node end2 = null;
        int length = 0;

        while (current1 != null) {
            length++;
            if (current1.next == null) {
                end1 = current1;
            }
            current1 = current1.next;
        }

        while (current2 != null) {
            length--;
            if (current2.next == null) {
                end2 = current2;
            }
            current2 = current2.next;
        }

        if (end1 != end2) {
            return null;
        }

        if (length > 0) {
            while (length > 0) {
                length--;
                node1 = node1.next;
            }
        } else {
            while (length < 0) {
                length++;
                node2 = node2.next;
            }
        }
        while (node1 != null) {
            if (node1.value == node2.value) {
                intersect = node1;
                break;
            }
            node1 = node1.next;
            node2 = node2.next;
        }
        return intersect;
    }



    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);
    }
}
