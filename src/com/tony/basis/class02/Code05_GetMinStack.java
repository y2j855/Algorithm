package com.tony.basis.class02;

import java.util.Arrays;

/**
 * @author: Tony.Chen
 * Create Time : 2020/10/1 20:27
 * Description:
 */
public class Code05_GetMinStack {

    /**
     * 用空间换时间
     * 第一个新的数组，往里存当前栈的最小值，长度与数据数组相等
     * 逻辑
     * 每次判断，往数据数组存放的值是否为栈当前最小值，如果是将此值放到最小值数组中，
     * 如果不是将最小值数组的值在放放一边。
     * 例如：data:9,5,7,6,4,3,2
     * min:9,5,5,5,4,3,2
     */
    public static class MyStack1 {
        private static final int SIZE = 7;
        private int[] array = new int[SIZE];
        public int index = 0;
        private int[] minArray = new int[SIZE];

        public void push(int value) {
            if (index == SIZE) {
                throw new RuntimeException("栈已满，请清除空间！");
            }
            if (index > 0) {
                minArray[index] = value < minArray[index - 1] ? value : minArray[index - 1];
            } else {
                minArray[index] = value;
            }
            array[index++] = value;
        }

        public int pop() {
            if (index == -1) {
                throw new RuntimeException("栈已空，请加入新数据！");
            }
            //下标从0开始，长度从1开始。
            int arrayIndex = --index;
            int[] tempArray = minArray;
            minArray = null;
            minArray = new int[arrayIndex];
            System.arraycopy(tempArray, 0, minArray, 0, arrayIndex);
            return array[arrayIndex];
        }

        public int getMinValue() {
            if (minArray.length <= 0) {
                throw new RuntimeException("栈已空，请加入新数据！");
            }
            return minArray[minArray.length - 1];
        }

        public void printMinArray() {
            for (int i = 0; i < minArray.length; i++) {
                System.out.print(minArray[i] + " ");
            }
        }
    }

    /**
     * 用时间换空间
     * 1.用数组实现栈时，发现代码很多都重复，并且逻辑关系比较混乱，所以将栈的实现抽取到单独的类。
     * 2.实现为固定长度数组，所以都是用index来判断，push(index++)，pop(--index),peek(index-1)
     * 3.数组的扩容和减小本来想实现，但发现减小数组作用不大，因为每次push都要再增加数组长度。
     * 4.读了jdk源码stack的实现方式，发现stack也没有做太多，都是底层的vector来实现。
     *      a.完成了数组的动态扩容
     *      b.用elementData来存放内容，用iterator来遍历展现内容
     *      c.elementCount类似我自己的index，不过由于是底层实现所以更加复杂一点。
     * 总结：jdk的实现更加体现了封装，继承复用的好处。
     * stack(FILO),queue(FIFO)本身就是逻辑关系的体现，具体的实现还是有底层来实现。
     */
    public static class MyStack2 {
        Stack<Integer> dataStack = new Stack<>(Integer.class);
        Stack<Integer> minStack = new Stack<>(Integer.class);

        public void push(int value) {
            if (minStack.index == 0) {
                minStack.push(value);
            } else if (minStack.peek() >= value) {
                minStack.push(value);
            }
            dataStack.push(value);
        }

        private int peek() {
            return dataStack.peek();
        }

        public int pop() {
            int value = dataStack.pop();
            if (minStack.peek() == value) {
                minStack.pop();
            }
            return value;
        }

        public int getMinValue() {
            if(minStack.isEmpty()){
                throw new RuntimeException("stack is null");
            }
            return minStack.peek();
        }

        public void printMinArray() {
            minStack.printData();
        }

        public void printDataArray() {
            dataStack.printData();
        }

    }

    public static void main(String[] args) {
//        testMyStatck1();
        testMyStatck2();
    }

    private static void testMyStatck1() {
        MyStack1 stack = new MyStack1();
        stack.push(9);
        stack.push(5);
        stack.push(7);
        stack.push(6);
        stack.push(4);
        stack.push(3);
        stack.push(2);

        System.out.println(stack.getMinValue());
        stack.printMinArray();
        System.out.println();
        stack.pop();
        stack.printMinArray();

        System.out.println();
        System.out.println(stack.getMinValue());

        stack.pop();
        stack.printMinArray();
        System.out.println();
        System.out.println(stack.getMinValue());

        stack.pop();
        stack.printMinArray();
        System.out.println();
        System.out.println(stack.getMinValue());

        stack.pop();
        stack.printMinArray();
        System.out.println();
        System.out.println(stack.getMinValue());

        stack.pop();
        stack.printMinArray();
        System.out.println();
        System.out.println(stack.getMinValue());

        stack.pop();
        stack.printMinArray();
        System.out.println();
        System.out.println(stack.getMinValue());

        stack.pop();
        stack.printMinArray();
        System.out.println();
        System.out.println(stack.getMinValue());

        stack.pop();
        stack.printMinArray();
        System.out.println();
        System.out.println(stack.getMinValue());
    }

    private static void testMyStatck2() {
        MyStack2 stack = new MyStack2();
        stack.push(9);
        stack.push(5);
        stack.push(2);
        stack.push(6);
        stack.push(4);
        stack.push(3);
        stack.push(2);

        stack.printDataArray();
        stack.printMinArray();
        System.out.println(stack.getMinValue());

        stack.pop();
        stack.printDataArray();
        stack.printMinArray();
        System.out.println(stack.getMinValue());

        stack.pop();
        stack.printDataArray();
        stack.printMinArray();
        System.out.println(stack.getMinValue());

        stack.pop();
        stack.printDataArray();
        stack.printMinArray();
        System.out.println(stack.getMinValue());

        stack.pop();
        stack.printDataArray();
        stack.printMinArray();
        System.out.println(stack.getMinValue());

        stack.pop();
        stack.printDataArray();
        stack.printMinArray();
        System.out.println(stack.getMinValue());

        stack.pop();
        stack.printDataArray();
        stack.printMinArray();
        System.out.println(stack.getMinValue());

        stack.pop();
        stack.printDataArray();
        stack.printMinArray();



    }
}
