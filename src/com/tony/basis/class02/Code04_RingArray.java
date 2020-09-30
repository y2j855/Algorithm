package com.tony.basis.class02;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/30 14:21
 * Description:静态数组实现队列 ring buffer；环状缓存
 * 栈比较好实现
 */
public class Code04_RingArray {

    public static class MyStack {
        private static final int SIZE = 7;
        private int[] array = new int[SIZE];
        public int index = 0;

        public void push(int value) {
            if (index == SIZE) {
                throw new RuntimeException("栈已满，请清除空间！");
            }
            array[index++] = value;
        }

        public int pop() {
            if (index == -1) {
                throw new RuntimeException("栈已空，请加入新数据！");
            }
            return array[--index];
        }
    }

    public static class MyQueue {
        //存放内容的数组
        private int[] array;
        //放数据的index
        private int pushIndex;
        //取数据的index
        private int pollIndex;
        //数组当前大小，用来解耦pushIndex与pollIndex关系
        private int size;
        //数组最大容量
        private final int limit;

        public MyQueue(int limit) {
            array = new int[limit];
            pushIndex = 0;
            pollIndex = 0;
            size = 0;
            this.limit = limit;
        }

        /**
         * 往队列放数据
         * @param value
         */
        public void enqueue(int value){
            if(size == limit){
                throw new RuntimeException("队列满了，不能再加了");
            }
            size++;
            array[pushIndex] = value;
            pushIndex = nextIndex(pushIndex);
        }

        /**
         * 从队列里取数据
         * @return
         */
        public int dequeue(){
            if(size == 0){
                throw new RuntimeException("队列为空，不能再拿了");
            }
            size--;
            int value = array[pollIndex];
            array[pollIndex] = 0;
            pollIndex = nextIndex(pollIndex);
            return value;
        }

        private int nextIndex(int index) {
            return index< limit - 1 ? index + 1 : 0;
        }

        public boolean isEmpty(){
            return size == 0;
        }

        public void printValue(){
            for (int i = 0; i < array.length; i++) {
                System.out.print(array[i] + " ");
            }
        }

    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue(7);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
//        queue.dequeue();
//        queue.enqueue(7);
//        queue.enqueue(8);
//        queue.enqueue(9);
//        queue.enqueue(10);
//        queue.enqueue(11);



//        queue.dequeue();
//        queue.dequeue();
//        queue.dequeue();
//        queue.dequeue();
//        queue.dequeue();
//        queue.dequeue();
        queue.printValue();

//        MyStack stack = new MyStack();
//        stack.push(1);
//        stack.push(2);
//        stack.push(3);
//        stack.push(4);
//        stack.push(5);
//        stack.push(6);
//        stack.push(7);
//        System.out.println(stack.index);
//
//        stack.pop();
//        stack.pop();
//        stack.pop();
//        stack.pop();
//        stack.pop();
//        stack.pop();
//        stack.pop();
//        System.out.println(stack.index);
    }
}
