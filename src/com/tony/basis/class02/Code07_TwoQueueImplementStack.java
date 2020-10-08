package com.tony.basis.class02;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: Tony.Chen
 * Create Time : 2020/10/8 17:39
 * Description:
 */
public class Code07_TwoQueueImplementStack {

    private static class TwoQueueStack<T>{
        private Queue<T> dataQueue;
        private Queue<T> helpQueue;

        public TwoQueueStack() {
            this.dataQueue = new LinkedList<>();
            this.helpQueue = new LinkedList<>();
        }

        public void push(T value){
            dataQueue.offer(value);
        }

        /**
         * 逻辑思路：
         * 1.将dataQueue的size-1的数据全部导入到helpQueue中
         * 2.将dataQueue最后一个数据返回给客户端
         * 3.将dataQueue与helpQueue进行交换
         * @return
         */
        public T pop(){
            while (dataQueue.size() > 1){
                helpQueue.offer(dataQueue.poll());
            }
            T value = dataQueue.poll();
            Queue<T> temp = dataQueue;
            dataQueue = helpQueue;
            helpQueue = temp;
            return value;
        }

        public T peek(){
            while (dataQueue.size() > 1){
                helpQueue.offer(dataQueue.poll());
            }
            T value = dataQueue.peek();
            Queue<T> temp = dataQueue;
            dataQueue = helpQueue;
            helpQueue = temp;
            dataQueue.offer(value);
            return value;
        }

        public static void main(String[] args) {
            TwoQueueStack<Integer> test = new TwoQueueStack<>();
            test.push(1);
            test.push(2);
            test.push(3);

            System.out.println(test.peek());
            System.out.println(test.pop());
            System.out.println(test.peek());
            System.out.println(test.pop());
            System.out.println(test.peek());
            System.out.println(test.pop());
        }
    }
}
