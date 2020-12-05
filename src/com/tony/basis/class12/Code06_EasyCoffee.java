package com.tony.basis.class12;

/**
 * @author: Tony.Chen
 * Create Time : 2020/12/4 21:56
 * Description: 动态规划    寻找业务限制的尝试模型
 * 给定一个数组，代表每个人喝完咖啡准备刷杯子的时间，只有一台咖啡机，一次只能洗一个杯子，
 * 时间耗费a，洗完才能洗下一杯。每个咖啡杯也可以自己挥发干净，时间耗费b，咖啡杯可以并行挥发，
 * 返回让所有咖啡杯变干净的最早完成时间。三个参数：int[] arr、int a、int b
 */
public class Code06_EasyCoffee {

    /**
     * 有一个隐含的前提要求,数组中的值是从小到大有序的
     *
     * @param arr 代表每个人喝完咖啡准备刷杯子的时间
     * @param a   咖啡机洗杯子需要的时间
     * @param b   咖啡杯自己挥发需要的时间
     * @return
     */
    public static int minTime(int[] arr, int a, int b) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, a, b, 0, 0);
    }

    /**
     * 暴力递归解决问题
     * 解题思路
     * 1.洗到最后一个杯子时，它的时间是先从用咖啡机洗的时间去选择
     * 取咖啡机最后一个杯子可洗的时间与咖啡机可洗的时间去比较，取大的值。例如：咖啡机最后一个杯子5点可以洗，咖啡机可以洗杯子的时间是6点，
     * 所以最后一个杯子只能从6点开始洗。
     * 再算最后一个杯子干洗需要的时间，取这两个值中的最小值。这就是递归的出口(base case)。
     * 2.剩下的杯子分两种情况
     * 情况1：如果当前杯子是用咖啡机洗的
     * 1)当前杯子用咖啡机洗需要的时间
     * 2)剩下的杯子用咖啡机洗需要的时间
     * 3)取1，2的最大值，就是用咖啡机洗杯子的总时间
     * <p>
     * 情况2：如果当前杯子是用咖啡挥发的
     * 1)当前杯子咖啡挥发的时间
     * 2)剩下的杯子用咖啡机洗需要的时间
     * 3)取1，2的最大值，就是咖啡挥发干净的总时间
     * <p>
     * 两种情况去小的值就是总共用的最少时间
     *
     * @param drinks   每个人喝完咖啡准备刷杯子的时间
     * @param a        咖啡机洗杯子需要的时间
     * @param b        咖啡杯自己挥发需要的时间
     * @param index    arr的当前下标位置
     * @param washLine 咖啡机可以洗杯子的时间
     * @return
     */
    private static int process(int[] drinks, int a, int b, int index, int washLine) {
        //base case
        if (index == drinks.length - 1) {
            return Math.min(Math.max(drinks[index], washLine) + a,
                    (drinks[index] + b));
        }

        int wash = Math.max(drinks[index], washLine) + a;
        int next1 = process(drinks, a, b, index + 1, wash);
        int p1 = Math.max(wash, next1);

        int dry = drinks[index] + b;
        int next2 = process(drinks, a, b, index + 1, washLine);
        int p2 = Math.max(dry, next2);

        return Math.min(p1, p2);
    }

    /**
     * 动态规划解决问题
     *
     * @param drinks
     * @param a
     * @param b
     * @return
     */
    public static int dp(int[] drinks, int a, int b) {
        int N = drinks.length;
        if (drinks == null || N == 0) {
            return 0;
        }
        //如果咖啡机洗杯子的时间大于咖啡杯挥发的时间，那就不需要用咖啡机洗杯子了。
        if (a >= b) {
            return drinks[N - 1] + b;
        }
        //咖啡机最晚的使用时间
        int limit = 0;
        for (int i = 0; i < N; i++) {
            limit = Math.max(limit, drinks[i]) + a;
        }
        int[][] dp = new int[N][limit + 1];
        //给最后一行赋值。
        for (int washLine = 0; washLine <= limit; washLine++) {
            dp[N - 1][washLine] = Math.min(Math.max(drinks[N - 1], washLine) + a, drinks[N - 1] + b);
        }
        //从倒数第二行赋值
        for (int index = N - 2; index >= 0; index--) {
            for (int washLine = 0; washLine <= limit; washLine++) {
                int p1 = Integer.MAX_VALUE;
                int wash = Math.max(drinks[index], washLine) + a;
                if (wash <= limit) {
                    p1 = Math.max(wash, dp[index + 1][wash]);
                }
                int p2 = Math.max(drinks[index] + b, dp[index + 1][washLine]);
                dp[index][washLine] = Math.min(p1, p2);
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] test = {1, 1, 1, 7};
        int a = 3;
        int b = 10;
        System.out.println(minTime(test, a, b));
        System.out.println(dp(test, a, b));
    }

}
