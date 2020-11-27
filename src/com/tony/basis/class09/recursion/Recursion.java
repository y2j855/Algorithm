package com.tony.basis.class09.recursion;

import com.tony.basis.StopWatch;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/20 21:39
 * Description:
 * 递归的核心思想
 * 1.找出一个方法的规律，递归关系，自上向下的思考
 * 2.找出出口(base case)，当满足什么条件结束递归调用
 * <p>
 * 练习题
 * 1.n！阶乘   1*2*3*4*5
 * 2.等差数列
 * 1+3+5+7+9
 * 2+4+6+8+10
 * 3.斐波那契数列 1+1+2+3+5+8+13
 * 4.循环转递归 1+2+3+4+5+...+100
 * 5.求数组最大值
 * 6.求数组总和
 * 7.冒泡排序递归实现
 * 8.最大公约数
 * 9.汉诺塔
 */
public class Recursion {

    /**
     * 1.n！阶乘   1*2*3*4*5
     * 逻辑思路
     * 1.10! = 10 * 9!,公式:f(n) = n * f(n-1)
     * 2.当n=1，f(1) = 1  出口/base case
     *
     * @param n
     * @return
     */
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    /**
     * 尾递归实现
     *
     * @param n
     * @param result
     * @return
     */
    public static int factorial1(int n, int result) {
        if (n == 1) {
            return result;
        }
        return factorial1(n - 1, n * result);
    }

    /**
     * 2.等差数列
     * 1+3+5+7+9
     * 2+4+6+8+10
     * 逻辑思路
     * 1.f(n) = f(n-1) + 2
     * 2.f(1) = 1
     * 等差数列和：f(n) = f(n-1) + (n-1) * 2 + 1
     *
     * @param a 开头的数
     * @param n 数的个数
     * @param d 等差额
     * @return 等差数列总和
     */
    public static int arithmeticSequence(int a, int n, int d) {
        if (n == 1) {
            return a;
        }
        return a + (n - 1) * d + arithmeticSequence(a, n - 1, d);
    }

    /**
     * 正常递归
     * 3.菲波那切数列 1+1+2+3+5+8+...+n
     * 1.f(0) = 0
     * 2.f(1) = 1
     * 3.f(2) = f(0) + f(1) = 1
     * 4.f(3) = f(1) + f(2) = 2
     * 5.f(n) = f(n-2) + f(n-1) = n
     *
     * @param n
     * @return
     */
    public static int fibonacci1(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fibonacci1(n - 1) + fibonacci1(n - 2);
    }

    /**
     * 尾递归实现，性能比1要好
     *
     * @param n     个数
     * @param res1  f(0) = 0
     * @param res2  f(1) = 1
     * @return
     */
    public static int fibonacci2(int n, int res1, int res2) {
        if (n == 0) {
            return res1;
        }
        return fibonacci2(n - 1, res2, res1 + res2);
    }

    /**
     * 斐波那契数列求和
     * 1+1+2+3+5+8+13+...+n
     * 1.f(4) = 1+1+2+3
     * 2.f(5) = 1+1+2+3+5
     * 3.f(6) = 1+1+2+3+5+8
     *
     * @param n
     * @return
     */
    public static int fibonacciSum(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += fibonacci2(i, 0, 1);
        }
        return sum;
    }

    /**
     * 4.循环转递归
     * 1+2+3+4+5+6+...+n
     * 1.f(n) = 1+2+3+4+5+6+...+n
     * 2.f(100) = 1+2+3+4+5+6+...+100
     * 3.f(n) = f(n-1) + n
     * 4.f(1) = 1
     *
     * @return
     */
    public static int recursionSum(int n) {
        if (n == 1) {
            return 1;
        }
        return recursionSum(n - 1) + n;
    }


    /**
     * 5.求数组最大值
     * 递归获取数组最大值
     * 1.找到递归的规律，假设数组arr，我们需要先判断最后与前边的位置比较
     * arr[index-1] > arr[index]
     * arr[index-1] < arr[index]
     * 2.然后找出口,也就是base case，当只有一个元素就直接返回
     * index=0,返回arr[0]
     * 必须有出口这个递归才算成立。
     *
     * @param arr
     * @param index
     * @return
     */
    public static int findMax2(int[] arr, int index) {
        if (index == 0) {
            return arr[index];
        } else if (findMax2(arr, index - 1) > arr[index]) {
            return findMax2(arr, index - 1);
        } else {
            return arr[index];
        }
    }

    /**
     * 5.求数组最大值
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int findMax1(int[] arr, int L, int R) {
        if (arr == null || arr.length == 0 || L > R) {
            return -1;
        }
        if (L == R) {
            return arr[L];
        } else if (findMax1(arr, L, R - 1) > arr[R]) {
            return findMax1(arr, L, R - 1);
        } else {
            return arr[R];
        }
    }

    /**
     * 6.求数组总和 arr[8] = {1,4,7,8,9,4,7,9} n=length-1
     * 1.sum(arr,n) = sum(arr,n-1) + arr[n]
     * 2.sum(arr,0) = arr[0]
     *
     * @param arr
     * @return
     */
    public static int arraySum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        return arraySum(mergeArray(arr)) + arr[arr.length - 1];
    }

    /**
     * 尾递归实现
     *
     * @param arr
     * @param result
     * @return
     */
    public static int arraySum1(int[] arr, int result) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return result;
        }
        return arraySum1(mergeArray(arr), result + arr[arr.length - 1]);
    }

    private static int[] mergeArray(int[] arr) {
        int[] result = new int[arr.length - 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = arr[i];
        }
        return result;

    }

    /**
     * 7.冒泡排序递归实现   arr[] = {3,4,6,7,8,10,4,6,23} n=arr.length - 1
     * 1.让数组按照冒泡排序走一次，找到最大值
     * 2.sort(arr,n) = sort(arr,n-1),arr[n]
     * 3.sort(arr,0) = arr[0]
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr, int L, int R) {
        if (L > R) {
            return;
        }
        if (L == R) {
            return;
        }
        for (int i = 0; i <= R - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                swap(arr, i, i + 1);
            }
        }
        bubbleSort(arr, L, R - 1);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 8.求两个数的最大公约数
     * 例子: 72 56
     * a        b
     * 72   /   56  1   余16
     * 56   /   16  3   余8
     * 16   /   8   2   余0
     * 当余为0，8就是最大公约数
     * 1.r = a%b ,b / r
     * 2.r==0,return b
     *
     * @param num1
     * @param num2
     * @return
     */
    public static int greatestCommonDivisor(int num1, int num2) {
        int r = num1 % num2;
        if (r == 0) {
            return num2;
        }
        return greatestCommonDivisor(num2, r);
    }

    /**
     * 9.汉诺塔问题
     * 将最长的看成一块，其他的看成另一块
     * 1.n==1,证明只有一块，出口(base case)就是从A移动到C
     * 2.剩下看做一个整体，先从A-B，然后最长那块从A-C，然后在将这个整体从B移到C
     * 时间复杂度O(2^n)
     * 如果是n层，移动步数一定是2^n-1
     * @param n 层数
     * @return 移动步数
     */
    public static void towerOfHanoi(int n, char A, char B, char C) {
        if (n == 1) {
            move(A, C);
        } else {
            towerOfHanoi(n - 1, A, C, B);
            move(A, C);
            towerOfHanoi(n - 1, B, A, C);
        }
    }

    private static void move(char c1, char c2) {
        System.out.println(c1 + "->" + c2);
    }

    /**
     * 一只青蛙可以一次跳1级台阶或一次跳2级台阶，例如：跳上第一级台阶只有一种跳法：直接跳1级即可。
     * 跳上第二级台阶有两种跳法：每次跳1级，跳两次，或者一次跳2级。问要跳上第n级台阶有多少种跳法？
     * @param n
     * @return
     */
    public static int stepUp(int n){
        if(n==1){
            return 1;
        }
        if(n==2){
            return 1;
        }
        return stepUp(n-1) + stepUp(n-2);
    }

    public static void main(String[] args) {
        int[] arr = {9, 45, 12, 13, 5, 16};
        System.out.println("阶乘递归=" + factorial(4));
        System.out.println("阶乘尾递归=" + factorial1(4, 1));
        System.out.println("等差队列求和=" + arithmeticSequence(2, 2, 2));
        StopWatch stop1 = new StopWatch();
        System.out.println("斐波那契数列递归=" + fibonacci1(45));
        System.out.println("fibonacci1 运行时间=" + stop1.elapsedTime());
        StopWatch stop2 = new StopWatch();
        System.out.println("斐波那契数列尾递归=" + fibonacci2(45, 0, 1));
        System.out.println("fibonacci2 运行时间=" + stop2.elapsedTime());
        System.out.println("斐波那契数列求和=" + fibonacciSum(6));
        System.out.println("1~n数的总和递归=" + recursionSum(100));
        System.out.println("数组找最大值=" + findMax1(arr, 0, 5));
        System.out.println("数组找最大值带下标的=" + findMax2(arr, 5));
        System.out.println("数组求和递归=" + arraySum(arr));
        System.out.println("数组求和尾递归=" + arraySum1(arr, arr[0]));

        System.out.println("递归冒泡排序");
        bubbleSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        System.out.println("两个数的最大公约数递归=" + greatestCommonDivisor(72, 56));

        System.out.println("汉诺塔游戏");
        towerOfHanoi(3, '左', '中', '右');

        System.out.println("青蛙跳台阶的方法="+stepUp(5));
    }
}
