package com.tony.basis.class02;

import com.tony.basis.StopWatch;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/30 10:30
 * Description:使用双向链表实现栈和队列
 * Description:使用双向链表实现栈和队列
 */
public class Code03_DoubleNodeToStackAndQueue {

    /**
     * 用于实现栈和队列的双向链表
     * @param <T>
     */
    public static class DoubleNodeQueue<T>{
        private DoubleNode<T> head;
        private DoubleNode<T> tail;

        /**
         * 从头部添加节点
         * @param value
         */
        public void addFromHead(T value){
            DoubleNode<T> current = new DoubleNode<>(value);
            if(head == null){
                head = current;
                tail = current;
            }else{
                current.next = head;
                head.pre = current;
                head = current;
            }
        }

        /**
         * 从尾部添加节点
         * @param value
         */
        public void addFromBottom(T value){
            DoubleNode<T> current = new DoubleNode<>(value);
            if(tail==null){
                tail = current;
                head = current;
            }else{
                current.pre = tail;
                tail.next = current;
                tail = current;
            }
        }

        /**
         * 从头部删除节点
         * @return
         */
        public T popFromHead(){
            if(head == null){
                return null;
            }
            DoubleNode<T> current = head;
            if(head == tail){
                head = null;
                tail = null;
            }else{
                head = head.next;
                current.next = null;
                head.pre = null;
            }
            return current.value;
        }

        /**
         * 从尾部删除节点
         * @return
         */
        public T popFromBottom(){
            if(tail == null){
                return null;
            }
            DoubleNode<T> current = tail;
            if(head == tail){
                head = null;
                tail = null;
            }else{
                tail = tail.pre;
                current.pre = null;
                tail.next = null;
            }
            return current.value;
        }

        public boolean isEmpty() {
            return head == null;
        }
    }

    public static class MyQueue<T>{
        private DoubleNodeQueue<T> queue;

        public MyQueue() {
            queue = new DoubleNodeQueue<>();
        }

        public void enqueue(T value){
            queue.addFromHead(value);
        }

        public T dequeue(){
            return queue.popFromBottom();
        }

        public boolean isEmpty(){
            return queue.isEmpty();
        }
    }

    public static class MyStack<T>{
        private DoubleNodeQueue<T> stacks;

        public MyStack() {
            stacks = new DoubleNodeQueue<>();
        }

        public void push(T value){
            stacks.addFromHead(value);
        }

        public T pop(){
            return stacks.popFromHead();
        }

        public boolean isEmpty(){
            return stacks.isEmpty();
        }
    }

    public static void main(String[] args) {
        int oneTestDataNum = 100;
        int value = 10000;
        int testTimes = 100000;
        StopWatch stopWatch = new StopWatch();
        for (int i = 0; i < testTimes; i++) {
            MyStack<Integer> myStack = new MyStack<>();
            MyQueue<Integer> myQueue = new MyQueue<>();
            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTestDataNum; j++) {
                int nums = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myStack.push(nums);
                    stack.push(nums);
                } else {
                    if (Math.random() < 0.5) {
                        myStack.push(nums);
                        stack.push(nums);
                    } else {
                        if (!isEqual(myStack.pop(), stack.pop())) {
                            System.out.println("oops!");
                        }
                    }
                }

                int numq = (int) (Math.random() * value);
                if (queue.isEmpty()) {
                    myQueue.enqueue(numq);
                    queue.offer(numq);
                } else {
                    if (Math.random() < 0.5) {
                        myQueue.enqueue(numq);
                        queue.offer(numq);
                    } else {
                        if (!isEqual(myQueue.dequeue(), queue.poll())) {
                            System.out.println("oops!");
                        }
                    }
                }
            }
        }
        System.out.println(stopWatch.elapsedTime());
        System.out.println("finish!");
    }

    private static boolean isEqual(Integer o1, Integer o2) {
        if (o1 == null && o2 != null) {
            return false;
        }
        if (o1 != null && o2 == null) {
            return false;
        }
        if (o1 == null && o2 == null) {
            return true;
        }
        return o1.equals(o2);
    }
}
