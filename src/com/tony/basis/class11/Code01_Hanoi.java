package com.tony.basis.class11;

import java.util.Stack;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/27 09:56
 * Description: 汉诺塔递归
 */
public class Code01_Hanoi {

    /**
     * 递归解决汉诺塔问题
     * 思路：
     * 目标:所有盘子从left移动到right
     * 1.n-1个盘子从left移动到center，因为n-1不止一个盘子，所以肯定要借助right
     * 2.第n个盘子从left移动到right
     * 3.n-1个盘子再从center移动到right，肯定要借助left
     * 4.base case：当n==1，直接从left移动到right
     * @param n
     * @param left
     * @param center
     * @param right
     */
    public static void hanoi1(int n, String left, String center, String right) {
        if (n == 1) {
            System.out.println("Move 1 from " + left + " to " + right);
        }else {
            hanoi1(n - 1, left, right, center);
            System.out.println("Move " + n + " from " + left + " to " + right);
            hanoi1(n - 1, center, left, right);
        }
    }

    public static class Record {
        public boolean finish;
        public int base;
        public String from;
        public String to;
        public String other;

        public Record(boolean f1, int b, String f, String t, String o) {
            finish = f1;
            base = b;
            from = f;
            to = t;
            other = o;
        }
    }

    /**
     * 非递归解决汉诺塔问题
     * @param N`
     */
    public static void hanoi2(int N){
        if (N < 1) {
            return;
        }
        Stack<Record> stack = new Stack<>();
        stack.add(new Record(false, N, "left", "right", "mid"));
        while (!stack.isEmpty()) {
            Record cur = stack.pop();
            if (cur.base == 1) {
                System.out.println("Move 1 from " + cur.from + " to " + cur.to);
                if (!stack.isEmpty()) {
                    stack.peek().finish = true;
                }
            } else {
                if (!cur.finish) {
                    stack.push(cur);
                    stack.push(new Record(false, cur.base - 1, cur.from, cur.other, cur.to));
                } else {
                    System.out.println("Move " + cur.base + " from " + cur.from + " to " + cur.to);
                    stack.push(new Record(false, cur.base - 1, cur.other, cur.to, cur.from));
                }
            }
        }
    }

    public static void main(String[] args) {
        hanoi1(3, "左", "中", "右");
        System.out.println("===========");
        hanoi2(3);
    }
}
