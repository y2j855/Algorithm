package com.tony.basis.class02;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Tony.Chen
 * Create Time : 2020/10/27 10:06
 * Description: LRU(Least Recently Used)最近最少使用算法
 * 解题思路：O(1)
 * 1.LinkedList(双向链表)解决数据添加，删除，更改节点位置
 * 2.HashMap解决查询链表上的节点
 */
public class LRUCache {

    public static void main(String[] args) {
//        LRUCache cache = new LRUCache(3);
//        cache.put(1, 1);
//        cache.put(2, 2);
//        cache.put(3, 3);
//
//        cache.get(2);
//
//        cache.put(4,4);
//
//        cache.test();

        LRUCache cache = new LRUCache(2 /* 缓存容量 */);

        cache.put(1, 1);
        cache.put(2, 2);

        System.out.println(cache.get(1));       // 返回  1
        cache.put(3, 3);    // 该操作会使得关键字 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得关键字 1 作废

        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        System.out.println(cache.get(3));      // 返回  3
        System.out.println(cache.get(4));       // 返回  4

    }

    int capacity = 0;
    Map<Integer, DoubleNode> map = new HashMap();
    DoubleNodeQueue<Integer> queue = new DoubleNodeQueue<Integer>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        DoubleNode<Integer> node = map.get(key);
        if (null == node) {
            return -1;
        }
        queue.modifyNodeLocation(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (queue.getSize() >= capacity) {
            Integer integer = queue.popFromBottom();
            map.remove(integer);
        }
        DoubleNode<Integer> node = queue.addHeadNode(value);
        map.put(key, node);
    }

    private class DoubleNodeQueue<T> {
        private int size = 0;
        private DoubleNode<T> head;
        private DoubleNode<T> tail;

        /**
         * 从队列头部添加新节点
         *
         * @param value
         */
        public DoubleNode<T> addHeadNode(T value) {
            DoubleNode<T> current = new DoubleNode(value);
            if (head == null) {
                head = current;
                tail = current;
            } else {
                current.next = head;
                head.pre = current;
                head = current;
            }
            size++;
            return current;
        }

        /**
         * 从队列尾部获取节点
         *
         * @return
         */
        public T popFromBottom() {
            if (tail == null) {
                return null;
            }
            DoubleNode<T> current = tail;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = current.pre;
                current.pre = null;
                tail.next = null;
            }
            size--;
            return current.value;
        }

        public int getSize() {
            return size;
        }

        /**
         * 修改指定节点的位置
         *
         * @param node
         */
        public void modifyNodeLocation(DoubleNode node) {
            if (node == tail) {
                headNode(node);
            } else if (node != head && node != tail) {
                middleNode(node);
            }
        }

        /**
         * 修改中间点的指针引用
         *
         * @param node
         */
        private void middleNode(DoubleNode node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            node.next = head;
            head.pre = node;
            head = node;
        }

        /**
         * 修改头节点的指针引用
         *
         * @param node
         */
        private void headNode(DoubleNode node) {
            tail = node.pre;
            tail.next = null;
            node.pre = null;
            head.pre = node;
            head = node;

        }
    }
}
