package com.tony.basis.class12;

/**
 * @author: Tony.Chen
 * Create Time : 2020/12/3 16:57
 * Description:
 */
public class Code04_PlayingCard {
    /**
     * 逻辑思路
     * 1.定义先手，后手两个情况
     * 2.先手，如果先手拿牌，考虑让后手拿到最小的可能性
     *
     * @param arr
     * @return
     */
    public static int win(int[] arr) {
        int first = firstHand(arr, 0, arr.length - 1);
        int second = backHand(arr, 0, arr.length - 1);
        if (first > second) {
            System.out.println("先手赢");
        } else {
            System.out.println("后手赢");
        }
        return Math.max(first, second);
    }

    /**
     * 先手情况
     * 1.如果只剩一张牌，直接返回
     * 2.如果大于一张张牌，先手拿，然后变成后手
     *
     * @param arr
     * @return
     */
    private static int firstHand(int[] arr, int L, int R) {
        //先手只剩一张牌,base case
        if (L == R) {
            return arr[L];
        }
        return Math.max(arr[L] + backHand(arr, L + 1, R), arr[R] + backHand(arr, L, R - 1));
    }

    /**
     * 后手情况，要站在先手的角度考虑问题
     * 1.如果只剩一张排，后手就拿不到了，所以返回0
     * 2.如果大于一张排，让先手先拿，然后后手就变成先手，拿牌的范围就是从L+1~R或从L~R-1
     * 3.又因为后手安排的情况是先手帮选择，所以结果肯定是最差的
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int backHand(int[] arr, int L, int R) {
        //后手只剩一张排，base case
        if (L == R) {
            return 0;
        }
        return Math.min(firstHand(arr, L + 1, R), firstHand(arr, L, R - 1));
    }

    /**
     * 动态规划解题
     * 在按照暴力递归解题时，还要考虑一个问题。
     * 因为我们见得是二维正方形，从斜中线右边取值时，任何一个值按照它的左方和下方取，
     * 所以如果按行遍历赋值会出现，它的下放还没有赋值的问题。
     * 解决这个问题有2中方式
     * 1.所有的值都是斜向赋值
     * 2.按列，从下到上，从左到右赋值。
     * @param arr
     * @return
     */
    public static int dpWay(int[] arr) {
        int N = arr.length;
        int[][] f = new int[N][N];
        int[][] s = new int[N][N];

        for (int i = 0; i < N; i++) {
            f[i][i] = arr[i];
        }

        /**
         * 斜向取值
         */
        for (int i = 1; i < N; i++) {
            int L = 0;
            int R = i;
            while (L < N && R < N) {
                f[L][R] = Math.max(arr[L] + s[L + 1][R], arr[R] + s[L][R - 1]);
                s[L][R] = Math.min(f[L + 1][R], f[L][R - 1]);
                L++;
                R++;
            }
        }

//        coloumnFromLToR(arr, N, f, s);
        return Math.max(f[0][N - 1], s[0][N - 1]);
    }

    /**
     * 按照列的从下到上，从左到右取值。
     * @param arr
     * @param n
     * @param f
     * @param s
     */
    private static void coloumnFromLToR(int[] arr, int n, int[][] f, int[][] s) {
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
//                if (i < j) {
//                    System.out.print("(" + j + "," + i + "), ");
                s[j][i] = Math.min(f[j + 1][i], f[j][i - 1]);
                f[j][i] = Math.max(arr[j] + s[j + 1][i], arr[i] + s[j][i - 1]);
//                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 7, 9, 100};
        System.out.println(win(arr));
        System.out.println(dpWay(arr));
    }
}
