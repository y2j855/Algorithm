package com.tony.basis.class11;

/**
 * @author: Tony.Chen
 * Create Time : 2020/12/1 10:40
 * Description: 暴力递归
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线，玩家A和玩家B依次拿走每张纸牌，规定玩家A先拿，玩家B后拿，
 * 但是每个玩家每次只能拿走最左或最右的纸牌，玩家A和玩家B都绝顶聪明。请返回最后获胜者的分数。
 */
public class Code07_PlayingCard {

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
        if(first > second){
            System.out.println("先手赢");
        }else{
            System.out.println("后手赢");
        }
        return Math.max(first, second);
    }

    /**
     * 先手情况
     * 1.如果只剩一张牌，直接返回
     * 2.如果大于一张张牌，先手拿，然后变成后手
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

    public static void main(String[] args) {
        int[] arr = { 1, 9, 1 };
        System.out.println(win(arr));
    }
}
