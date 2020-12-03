package com.tony.basis.class12;

/**
 * @author: Tony.Chen
 * Create Time : 2020/12/2 10:07
 * Description: 动态规划
 * 给定两个长度都为N的数组weights和values，weights[i]和values[i]分别代表i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，你装的物品不能超过这个重量。返回你能装下最多的价值是多少？(经典背包问题)
 */
public class Code03_Knapsack {

    public static int getMaxValue(int[] weight, int[] values, int bag) {
        if (weight == null || weight.length == 0) {
            return 0;
        }
        if (values == null || values.length == 0) {
            return 0;
        }

        return process(weight, values, 0, bag);
    }

    /**
     * 暴力递归计算背包问题
     * 利用index，alreadyW:背包已装入的货物重量来计算最大价值
     *
     * @param weight
     * @param values
     * @param index
     * @param alreadyW
     * @param bag
     * @return
     */
    private static int process(int[] weight, int[] values, int index, int alreadyW, int bag) {
        if (alreadyW > bag) {
            return -1;
        }
        if (index == weight.length) {
            return 0;
        }
        //不要当前货物的价值，计算之后货物的价值
        int p1 = process(weight, values, index + 1, alreadyW, bag);
        //要当前货物的价值，计算之后的货物价值
        int p2Next = process(weight, values, index + 1, weight[index] + alreadyW, bag);
        //p2计算当前价值和之后价值的总和
        int p2 = -1;
        if (p2Next != -1) {
            p2 = values[index] + p2Next;
        }
        return Math.max(p1, p2);
    }

    /**
     * 暴力递归计算背包最大价值问题
     * 利用index，rest:剩余重量,来解决背包问题
     * index的含义：只考虑从index开始的货物重量和价值
     * 只有2种情况需要考虑，要index+1的货物和价值，不要index+1的货物和价值
     *
     * @param weight
     * @param values
     * @param index
     * @param rest
     * @return
     */
    private static int process(int[] weight, int[] values, int index, int rest) {
        if (rest <= 0) {
            return 0;
        }
        if (index == weight.length) {
            return 0;
        }
        int p1 = process(weight, values, index + 1, rest);
        int p2 = Integer.MIN_VALUE;
        if (rest >= weight[index]) {
            p2 = values[index] + process(weight, values, index + 1, rest - weight[index]);
        }
        return Math.max(p1, p2);
    }

    /**
     * 动态规划解决问题
     * 逻辑思路
     * 1.确定需要变化的参数有哪些
     * 2.然后确定暴力递归是否会出现重复操作
     * 3.通过变化的参数建立一张表(一维，二维，根据变化的参数确定)，来存储所有可能的值
     * 4.将暴力递归的代码改成非递归的就是最后要的结果。
     * @param weight
     * @param values
     * @param bag
     * @return
     */
    public static int dpWay(int[] weight, int[] values, int bag) {
        int N = weight.length;
        int[][] dp = new int[N + 1][bag + 1];
        //如果dp[N] = weight.length,dp[N][0~bag]=0
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 1; rest <= bag; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - weight[index] >= 0) {
                    dp[index][rest] = Math.max(dp[index][rest], values[index] + dp[index + 1][rest - weight[index]]);
                }
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7};
        int[] values = {5, 6, 3, 19};
        int bag = 11;
        System.out.println(getMaxValue(weights, values, bag));
        System.out.println(dpWay(weights, values, bag));
    }
}
