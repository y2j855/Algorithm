package com.tony.basis.class12;

/**
 * @author: Tony.Chen
 * Create Time : 2020/12/3 10:47
 * Description: 动态规划
 * 给定数组arr，arr中所有的值都为正数且不重复，每个值代表一种面值的货币，每种面值的货币可以使用任意张，
 * 再给定一个整数 aim，代表要找的钱数，求组成 aim 的方法数。
 */
public class Code09_CoinWay {

    public static int ways(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    private static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int result = 0;
        //枚举每个硬币的个数
        for (int number = 0; arr[index] * number <= rest; number++) {
            result += process(arr, index + 1, rest - arr[index] * number);
        }
        return result;
    }

    public static int waysCache(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] cache = new int[arr.length + 1][aim + 1];
        for (int i = 0; i < cache.length; i++) {
            for (int j = 0; j < cache[i].length; j++) {
                cache[i][j] = -1;
            }
        }
        return process(arr, 0, aim, cache);
    }

    private static int process(int[] arr, int index, int rest, int[][] cache) {
        if (cache[index][rest] != -1) {
            return cache[index][rest];
        }
        if (index == arr.length) {
            cache[index][rest] = rest == 0 ? 1 : 0;
            return cache[index][rest];
        }
        int result = 0;
        //枚举每个硬币的个数
        for (int number = 0; arr[index] * number <= rest; number++) {
            result += process(arr, index + 1, rest - arr[index] * number);
            cache[index][rest] = result;
        }
        return cache[index][rest];
    }

    /**
     * 动态规划
     * 将暴力递归改成动态规划，但发现子步骤有枚举行为，需要再优化。
     * @param arr
     * @param aim
     * @return
     */
    public static int dpWays(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;

        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int result = 0;
                //枚举每个硬币的个数,在动态规划时需要优化
                for (int number = 0; arr[index] * number <= rest; number++) {
                    result += dp[index + 1][rest - (arr[index] * number)];
                }
                dp[index][rest] = result;
            }
        }
        return dp[0][aim];
    }

    /**
     * 动态规划最终版本
     * 解决子步骤枚举问题，将动态规划进行精细化组织。
     * 发现了for循环可以用当前值 = 下一行同一列 + 同一行arr[index]列的值
     * @param arr
     * @param aim
     * @return
     */
    public static int dpWaysFinal(int[] arr,int aim){
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;

        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                /**
                 * 发现枚举的关系
                 * 当前值= 下一行同一列的值 + 当前行前一个arr[index]列的值
                 */
                dp[index][rest] = dp[index+1][rest];
                if(rest - arr[index] >=0){
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int[] moneys = {10, 20, 50};
        int aim = 100;
        System.out.println(ways(moneys, aim));
        System.out.println(waysCache(moneys, aim));
        System.out.println(dpWays(moneys,aim));
        System.out.println(dpWaysFinal(moneys,aim));
    }
}
