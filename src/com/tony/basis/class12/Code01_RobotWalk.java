package com.tony.basis.class12;

/**
 * @author: Tony.Chen
 * Create Time : 2020/12/1 20:38
 * Description:
 * 假设有排成一行的N个位置，记为1~N，N 一定大于或等于2。
 * 开始时机器人在其中的M位置上(M 一定是 1~N 中的一个)，如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走K步，最终能来到P位置(P也是1~N中的一个)的方法有多少种 给定四个参数 N、M、K、P，返回方法数。
 */
public class Code01_RobotWalk {

    /**
     * 计算机器人走到最终位置的方法数
     *
     * @param N 一行下多少个位置
     * @param M 开始时机器人在的位置
     * @param K 规定机器人必须走的步数
     * @param P 机器人最终到的位置
     * @return
     */
    public static int ways1(int N, int M, int K, int P) {
        if (N < 2 || M < 1 || M > N || K < 1 || P < 1 || P > N) {
            return 0;
        }
        //总共N个位置，从M点出发，还剩K步，返回最终能达到P点的方法数
        return walk1(N, M, K, P);
    }

    /**
     * 利用暴力递归解决
     * 问题：会重复执行很多执行过的步骤
     *
     * @param N       一行总共的位置，固定参数
     * @param current 当前位置，可变参数
     * @param rest    还剩rest步没有走，可变参数
     * @param P       最终位置，固定参数
     * @return 走到P位置总共的方法个数
     */
    private static int walk1(int N, int current, int rest, int P) {
        if (rest == 0) {
            return current == P ? 1 : 0;
        }
        if (current == 1) {
            return walk1(N, 2, rest - 1, P);
        }
        if (current == N) {
            return walk1(N, N - 1, rest - 1, P);
        }
        return walk1(N, current - 1, rest - 1, P) + walk1(N, current + 1, rest - 1, P);
    }

    /**
     * 利用缓存实现
     *
     * @param N
     * @param M
     * @param K
     * @param P
     * @return
     */
    public static int waysCache(int N, int M, int K, int P) {
        if (N < 2 || M < 1 || M > N || K < 1 || P < 1 || P > N) {
            return 0;
        }
        //根据行的个数，能走的步数建立一个二维数组，这样能把所有情况都包含在里。
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] = -1;
            }
        }
        return walkCache(N, M, K, P, dp);
    }

    /**
     * 利用数组充当缓存容器，解决重复执行步骤的问题。
     * 这就是最初级的动态规划，也叫记忆化搜索。
     *
     * @param N
     * @param current
     * @param rest
     * @param P
     * @param dp
     * @return
     */
    private static int walkCache(int N, int current, int rest, int P, int[][] dp) {
        if (dp[current][rest] != -1) {
            return dp[current][rest];
        }
        if (rest == 0) {
            dp[current][rest] = current == P ? 1 : 0;
            return dp[current][rest];
        }
        if (current == 1) {
            dp[current][rest] = walkCache(N, 2, rest - 1, P, dp);
            return dp[current][rest];
        }
        if (current == N) {
            dp[current][rest] = walkCache(N, N - 1, rest - 1, P, dp);
            return dp[current][rest];
        }
        dp[current][rest] = walkCache(N, current - 1, rest - 1, P, dp) + walkCache(N, current + 1, rest - 1, P, dp);
        return dp[current][rest];
    }

    /**
     * 利用动态规划解决此问题
     * <p>
     * 因为我们要一列一列的赋值，所以将列放在外层循环，行放在内层循环。也可将N、K互换(另一种解题思路)
     * 第一行我们不用管它，默认值为0.
     * 根据暴力队规的逻辑，分析出三种情况
     * 1.如果是第二行，也就是row==1，它的值依赖于下一行前一列的值。dp[1][1] = dp[2][0]
     * 2.如果是最后一行，它的值依赖于上一行前一列的值。dp[7][1] = dp[6][0]
     * 3.其他行依赖于上一行前一列的值 + 下一行前一列的值。dp[3][4] = dp[2][3] + dp[4][3]
     *
     * @param N 总共的位置
     * @param M 出发点
     * @param K 还剩的步数
     * @param P 终点P
     * @return 总共N个位置，从M点出发，还剩K步，返回最终能达到P点的方法数
     */
    public static int dpWay(int N, int M, int K, int P) {
        if (N < 2 || M < 1 || M > N || K < 1 || P < 1 || P > N) {
            return 0;
        }
        //根据多少个位置的个数，能走的步数建立一个二维数组，这样能把所有情况都包含在里。
        int[][] dp = new int[N + 1][K + 1];
        //将第一列等于1的值定位好，就是rest==0，current==P，值等于1
        dp[P][0] = 1;

        for (int col = 1; col <= dp[0].length - 1; col++) {
            for (int row = 1; row <= dp.length - 1; row++) {
                if (row == 1) {
                    dp[row][col] = dp[row + 1][col - 1];
                } else if (row == dp.length - 1 && col >= 1) {
                    dp[row][col] = dp[row - 1][col - 1];
                } else {
                    dp[row][col] = dp[row - 1][col - 1] + dp[row + 1][col - 1];
                }
            }
        }
        return dp[M][K];
    }

    /**
     * 动态规划
     * 与dpway逻辑完全一样，只是把二维表的结构转了90度，让行列互换，这样就能从行上边遍历。
     * @param N
     * @param M
     * @param K
     * @param P
     * @return
     */
    public static int dpWay2(int N, int M, int K, int P) {
        if (N < 2 || M < 1 || M > N || K < 1 || P < 1 || P > N) {
            return 0;
        }
        //根据多少个位置的个数，能走的步数建立一个二维数组，这样能把所有情况都包含在里。
        int[][] dp = new int[K + 1][N + 1];
        //将第一列等于1的值定位好，就是rest==0，current==P，值等于1
        dp[0][P] = 1;

        for (int row = 1; row <= K; row++) {
            for (int col = 1; col <= N; col++) {
                if (col == 1) {
                    dp[row][col] = dp[row - 1][2];
                } else if (col == N) {
                    dp[row][col] = dp[row - 1][col - 1];
                } else {
                    dp[row][col] = dp[row - 1][col - 1] + dp[row -1][col + 1];
                }
            }
        }
        return dp[K][M];
    }

    public static void main(String[] args) {
        System.out.println(ways1(7, 4, 9, 5));
        System.out.println(waysCache(7, 4, 9, 5));
        System.out.println(dpWay(7, 4, 9, 5));
        System.out.println(dpWay2(7, 4, 9, 5));
    }
}
