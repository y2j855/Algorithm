package com.tony.basis.class12;

/**
 * @author: Tony.Chen
 * Create Time : 2020/12/5 15:25
 * Description: 动态规划    三维数据缓存表
 * 请同学们自行搜索或者想象一个象棋的棋盘，然后把整个棋盘放入第一象限，
 * 棋盘的最左下角是(0,0)位置，那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域。
 * 给你三个参数 x，y，k，返回“马”从(0,0)位置出发，必须走k步。最后落在(x,y)上的方法数有多少种?
 */
public class Code07_HorseJump {

    /**
     * 棋盘是10*9
     * x轴0~9
     * y轴0~8
     *
     * @param x
     * @param y
     * @param k
     * @return
     */
    public static int ways(int x, int y, int k) {
        if (k <= 0) {
            return 0;
        }
        return process(0, 0, k, x, y);
    }

    /**
     * 暴力递归
     *
     * @param i    当前马在x轴的坐标
     * @param j    当前马在y轴的坐标
     * @param step 要走的步数
     * @param x    在棋盘中目标x轴目标
     * @param y    在棋盘中目标y轴目标
     * @return 走到目标点的方法数
     */
    private static int process(int i, int j, int step, int x, int y) {
        if (i < 0 || i > 9 || j < 0 || j > 8) {
            return 0;
        }
        if (step == 0) {
            return (i == x && j == y) ? 1 : 0;
        }
        return process(i + 1, j + 2, step - 1, x, y) + process(i + 1, j - 2, step - 1, x, y)
                + process(i - 1, j + 2, step - 1, x, y) + process(i - 1, j - 2, step - 1, x, y)
                + process(i + 2, j + 1, step - 1, x, y) + process(i + 2, j - 1, step - 1, x, y)
                + process(i - 2, j + 1, step - 1, x, y) + process(i - 2, j - 1, step - 1, x, y);
    }

    /**
     * 发现有很多重复子步骤，利用动态规划解题
     * 发现没走一步就会衍生出很多其他的选择，所以我们需要用三维表来存储数据
     *
     * @param x
     * @param y
     * @param step
     * @return
     */
    public static int dp(int x, int y, int step) {
        int[][][] dp = new int[10][9][step + 1];
        dp[x][y][0] = 1;
        //按层来，因为每走一步会出现很多可能性，所以把每一步想成一层
        for (int s = 1; s <= step; s++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 9; j++) {
                    dp[i][j][s] = getValue(dp, i + 1, j + 2, s - 1)
                            + getValue(dp, i + 1, j - 2, s - 1)
                            + getValue(dp, i - 1, j + 2, s - 1)
                            + getValue(dp, i - 1, j - 2, s - 1)
                            + getValue(dp, i + 2, j + 1, s - 1)
                            + getValue(dp, i + 2, j - 1, s - 1)
                            + getValue(dp, i - 2, j + 1, s - 1)
                            + getValue(dp, i - 2, j - 1, s - 1);
                }
            }
        }
        return dp[0][0][step];
    }

    private static int getValue(int[][][] dp, int i, int j, int step) {
        if (i < 0 || i > 9 || j < 0 || j > 8) {
            return 0;
        }
        return dp[i][j][step];
    }

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(ways(x, y, step));
        System.out.println(dp(x, y, step));
    }
}
