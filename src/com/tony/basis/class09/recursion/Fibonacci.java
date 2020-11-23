package com.tony.basis.class09.recursion;

import com.tony.basis.StopWatch;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/22 21:05
 * Description: 斐波那契数列
 * 1.递归时间，时间复杂度O(N^3),时间复杂度不好
 * 2.使用哈希表/数组，降低时间复杂度
 * 3.循环遍历，自下而上
 * 4.尾递归
 * 5.数学公式
 */
public class Fibonacci {
    Map<Integer, Long> cache = new HashMap<>();

    /**
     * 通过递归计算斐波那契数列第n项的值
     * 时间复杂度高O(2^n)
     *
     * @param n
     * @return
     */
    public long fibonacci1(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fibonacci1(n - 1) + fibonacci1(n - 2);
    }

    /**
     * 利用map，缓存结果，防止重复调用，降低时间复杂度O(N)
     * 空间换时间，但空间复杂度也上升了变成O(N)
     *
     * @param n
     * @return
     */
    public long fibonacci2(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        long result = fibonacci2(n - 1) + fibonacci2(n - 2);
        cache.put(n, result);
        return result;
    }

    /**
     * 循环遍历，利用自下而上的解题思路。
     * 时间复杂度O(n),空间复杂度O(1)
     *
     * @param n
     * @return
     */
    public long fibonacci3(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 1;
        }
        long result = 0;
        long pre = 1;
        long next = 1;

        for (int i = 3; i <= n; i++) {
            result = pre + next;
            pre = next;
            next = result;
        }
        return result;
    }

    /**
     * 尾递归实现，因为是尾递归，会把上次的计算结果传递到下一次中，所以不需要再回调。
     * 每次的fist就是要求当前的返回值，当执行到n==0的时候，此时first就是我们要求第n个数的值
     * 初始值得设定是根据斐波那契数列的特性决定的
     * f(0)=0
     * f(1)=1
     * f(2)=1
     * f(3)=2...
     * <p>
     * fibonacci(5,0,1) ==> fibonacci(4,1,1)    1
     * fibonacci(4,1,1) ==> fibonacci(3,1,2)    1
     * fibonacci(3,1,2) ==> fibonacci(2,2,3)    2
     * fibonacci(2,2,3) ==> fibonacci(1,3,5)    3
     * fibonacci(1,3,5) ==> fibonacci(0,5,8)    5
     *
     * @param n
     * @param first  当前层要返回的值，初始为0    f(0)=0
     * @param second 下一层要返回的值，初始为1    f(1)=1
     * @return
     */
    public long fibonacci(int n, long first, long second) {
        if (n == 0) {
            return first;
        }
        return fibonacci(n - 1, second, first + second);
    }

    /**
     * 利用jdk提供的公式实现
     *
     * @param n
     * @return
     */
    public long fibonacci4(int n) {
        double c = Math.sqrt(5);
        return (long) ((Math.pow((1 + c) / 2, n) - Math.pow((1 - c) / 2, n)) / c);
    }

    public static void main(String[] args) {
        Fibonacci f = new Fibonacci();
        StopWatch stop1 = new StopWatch();
        System.out.println("递归======斐波那契数列计算第n个值=" + f.fibonacci1(50));
        System.out.println(stop1.elapsedTime());

        stop1 = new StopWatch();
        System.out.println("递归Map======斐波那契数列计算第n个值=" + f.fibonacci2(50));
        System.out.println(stop1.elapsedTime());

        stop1 = new StopWatch();
        System.out.println("利用循环遍历======斐波那契数列计算第n个值=" + f.fibonacci3(50));
        System.out.println(stop1.elapsedTime());

        stop1 = new StopWatch();
        System.out.println("尾递归======斐波那契数列计算第n个值=" + f.fibonacci(50, 0, 1));
        System.out.println(stop1.elapsedTime());

        stop1 = new StopWatch();
        System.out.println("数学公式======斐波那契数列计算第n个值=" + f.fibonacci4(50));
        System.out.println(stop1.elapsedTime());
    }
}
