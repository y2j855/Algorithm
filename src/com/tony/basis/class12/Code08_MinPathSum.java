package com.tony.basis.class12;

/**
 * @author: Tony.Chen
 * Create Time : 2020/12/5 18:00
 * Description: 动态规划
 * 给定一个矩阵m，从左上角开始每次只能向右或者向下走，最后到达右下角的位置，路径上所有的数字累加起来就是路径和。返回所有的路径中最小的路径和
 */
public class Code08_MinPathSum {

    /**
     * 动态规划第一版
     * 逻辑思路
     * 1.因为第一行和第一列的值只会经过一遍，所以行和列的值等于前一个值加上本身的值
     * 2.剩下的值是它上边和左边进行比较，取小的值。
     * 问题：当前格子的值只与上边和左边格子的值有关，其他格子就浪费了。所以可以进行优化
     *
     * @param m
     * @return
     */
    public static int dpWays(int[][] m) {
        if (m == null || m.length == 0) {
            return 0;
        }
        int row = m.length;
        int column = m[0].length;

        int[][] dp = new int[row][column];
        dp[0][0] = m[0][0];

        /**
         * 设置第一行第一列的值
         */
        for (int i = 1; i < column; i++) {
            dp[0][i] = dp[0][i - 1] + m[0][i];
        }

        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        /**
         * 设置其他行和列的值
         */
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + m[i][j];
            }

        }
        return dp[row - 1][column - 1];
    }

    /**
     * 动态规划，优化版本。解决空间浪费问题
     * 解题思路
     * 1.用一维数组替换二维数组，每遍历一行就代表当前行的值。
     * 2.可以再度优化，让空间更好，可以比较行列长度，设置一维数组的长度为长度短的。
     * @param m
     * @return
     */
    public static int dpWaysBest(int[][] m) {
        if (m == null || m.length == 0) {
            return 0;
        }
        int row = m.length;
        int column = m[0].length;
        int less = Math.min(row, column);

        int[] dp = new int[less];
        dp[0] = m[0][0];
        for (int i = 1; i < less; i++) {
            dp[i] = dp[i - 1] + m[0][i];
        }
        /**
         * 设置其他行和列的值
         */
        for (int i = 1; i < row; i++) {
            dp[0] = dp[0] + m[i][0];
            for (int j = 1; j < column; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + m[i][j];
            }

        }
        return dp[column - 1];
    }

    // for test
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] m = {{1, 3, 5}, {2, 6, 4}, {7, 0, 3}};
        int[][] m1 = {
                {7, 6, 3},
                {2, 5, 7},
                {3, 0, 4},
                {1, 2, 8},
                {4, 9, 2}};
        printMatrix(m1);
        System.out.println(dpWays(m1));
        System.out.println(dpWaysBest(m1));
    }
}
