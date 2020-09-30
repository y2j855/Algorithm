package com.tony.basis.class02;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/30 10:23
 * Description:单向链表
 */
public class Node {
    public int value;
    public Node next;

    public Node(int value) {
        this.value = value;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
