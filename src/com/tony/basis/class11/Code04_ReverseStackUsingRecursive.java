package com.tony.basis.class11;

import java.util.Stack;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/27 10:42
 * Description:
 * 给你一个栈，请你逆序这个栈，不能申请额外的数据结构，只能使用递归函数。
 */
public class Code04_ReverseStackUsingRecursive {

    /**
     * 方法功能：栈{3,2,1},返回3,栈{2,1}
     * 1.递归调用,找到base case：当栈没有数据了返回弹出的栈顶值
     * 2.用两个变量存栈的相关值
     * number:当前栈弹出的栈顶值，last：递归调用函数返回第一个弹出的栈顶值
     *
     * @param stack
     * @return
     */
    public static int f(Stack<Integer> stack) {
        int number = stack.pop();
        if (stack.isEmpty()) {
            return number;
        } else {
            int last = f(stack);
            stack.push(number);
            return last;
        }
    }

    /**
     * 利用递归将栈内容反转
     * 栈:{1,2,3} => {3,2,1}
     * @param stack
     */
    public static void reverse(Stack<Integer> stack) {
        int number = f(stack);
        if (!stack.isEmpty()) {
            reverse(stack);
        }
        stack.push(number);

    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(2);
        stack.push(1);

        reverse(stack);
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
}
