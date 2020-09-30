package com.tony.basis.class02;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/30 10:17
 * Description:双向链表
 */
public class DoubleNode {
    public int value;
    public DoubleNode pre;
    public DoubleNode next;

    public DoubleNode(int value) {
        this.value = value;
    }

    public void setPre(DoubleNode pre) {
        this.pre = pre;
    }

    public void setNext(DoubleNode next) {
        this.next = next;
    }
}
