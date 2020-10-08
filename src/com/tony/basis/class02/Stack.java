package com.tony.basis.class02;

import com.tony.basis.GenericsUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Tony.Chen
 * Create Time : 2020/10/3 09:47
 * Description:不变长度数组实现栈
 */
public class Stack<T> {
    private static final int SIZE = 7;
    private T[] array;
    public int index = 0;

    List<String> stringList = new ArrayList<String>();
    List<Integer> integerList = new ArrayList<Integer>();

    public Stack(Class<T> type) {
        array = (T[])Array.newInstance(type,SIZE);
    }

    public void push(T value) {
        if (index == SIZE) {
            throw new RuntimeException("栈已满，请清除空间！");
        }
        array[index++] = value;
    }

    public T pop() {
        if (index == -1) {
            throw new RuntimeException("栈已空，请加入新数据！");
        }
        T value = array[--index];
        modifyArray(array);
        return value;
    }

    private T[] modifyArray(T[] array) {
//        array = Arrays.copyOf(array,size - 1);
        array[index] = null;
        return array;
    }

    public T peek(){
        if(index == -1){
            throw new RuntimeException("栈已空，请加入新数据！");
        }
        return array[index-1];
    }

    public void printData(){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("============");
    }

    public boolean isEmpty() {
        return index == 0;
    }

    public static void main(String[] args) throws NoSuchFieldException {
        Stack<Integer> stack = new Stack<Integer>(Integer.class);
//        stack.push(1);
//        stack.push(2);
//        stack.push(3);
//        stack.push(4);
//        stack.push(5);
//        stack.push(6);
//        stack.push(7);
//
//        System.out.println(stack.peek());
//        System.out.println(stack.pop());
//        System.out.println(stack.peek());
//
//        stack.push(8);
//        System.out.println(Arrays.toString(stack.array));
//        System.out.println(stack.peek());
//        System.out.println(stack.pop());
//        System.out.println(stack.peek());
//        System.out.println(stack.pop());
//        System.out.println(stack.peek());

        System.out.println("==========");

        System.out.println(GenericsUtils.getFieldGenricType(Stack.class,"stringList"));

        System.out.println(GenericsUtils.getFieldGenricType(Stack.class,"integerList"));

    }
}
