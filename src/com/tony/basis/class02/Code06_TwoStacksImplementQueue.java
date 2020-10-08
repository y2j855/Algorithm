package com.tony.basis.class02;

/**
 * @author: Tony.Chen
 * Create Time : 2020/10/8 16:51
 * Description:
 */
public class Code06_TwoStacksImplementQueue {

    private static class TwoStacksQueue {

        private Stack<Integer> pushStack;
        private Stack<Integer> popStack;

        public TwoStacksQueue() {
            this.pushStack = new Stack<>(Integer.class);
            this.popStack = new Stack<>(Integer.class);
        }

        /**
         * psuh栈往pop栈里放数据
         * 满足条件
         * 1.pop栈必须为空
         * 2.push栈一次性放入所有数据
         */
        private void pushToPop () {
            if (popStack.isEmpty()) {
                while (!pushStack.isEmpty()) {
                    popStack.push(pushStack.pop());
                }
            }
        }

        public void enqueue ( int value){
            pushStack.push(value);
            pushToPop();
        }

        public int dequeue () {
            if (popStack.isEmpty() && pushStack.isEmpty()) {
                throw new RuntimeException("Queue is Empty!");
            }
            pushToPop();
            return popStack.pop();
        }

        public int peek () {
            if (popStack.isEmpty() && pushStack.isEmpty()) {
                throw new RuntimeException("Queue is Empty!");
            }
            pushToPop();
            return popStack.peek();
        }

        public void printPushStack(){
            pushStack.printData();
        }

        public void printPopStack(){
            popStack.printData();
        }

        public static void main (String[]args){
            TwoStacksQueue test = new TwoStacksQueue();
            test.enqueue(1);
            test.enqueue(2);
            test.enqueue(3);

            System.out.println(test.peek());
            System.out.println(test.dequeue());
            System.out.println(test.peek());
            System.out.println(test.dequeue());
            System.out.println(test.peek());
            System.out.println(test.dequeue());
        }
    }
}
