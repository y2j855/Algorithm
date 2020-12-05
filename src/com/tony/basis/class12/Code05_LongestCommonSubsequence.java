package com.tony.basis.class12;

/**
 * @author: Tony.Chen
 * Create Time : 2020/12/4 20:15
 * Description: 动态规划
 * 两个字符串的最长公共子序列问题  多样本位置全对应的尝试模型
 * 最长公共子序列: a1b2c3d4,e1f2g3h4, 最长公共子序列=1234
 */
public class Code05_LongestCommonSubsequence {

    /**
     * 逻辑思路
     * 1.首先将建立一张二维表行为s1长度，列为s2长度
     * 2.分析两个字符串最长公共子序列有四种情况
     * a.即不以s1[0...i]结尾，也不以s2[0...j]结尾,dp[i][j] = dp[i-1][j-1]
     * b.以s1[0...i]结尾，不以s2[0...j]结尾,dp[i][j] = dp[i][j-1]
     * c.不以s1[0...i]结尾，以s2[0...j]结尾,dp[i][j] = dp[i-1][j]
     * d,以s1[0...i]结尾，也以s2[0...j]结尾,那么s1[i]=s2[j],dp[i][j] = dp[i-1][j-1] + 1
     * @param s1
     * @param s2
     * @return
     */
    public static int lcse(String s1, String s2) {
        if (s1 == null || s1.equals("") || s2 == null || s2.equals("")) {
            return 0;
        }
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int[][] dp = new int[chars1.length][chars2.length];
        dp[0][0] = chars1[0] == chars2[0] ? 1 : 0;

        /**
         * 设置第一行，第一列
         */
        for (int i = 1; i < chars1.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i][0] = chars1[i] == chars2[0] ? 1 : 0);
        }

        for (int i = 0; i < chars2.length; i++) {
            dp[0][i] = Math.max(dp[0][i - 1], dp[0][i] = chars1[0] == chars2[i] ? 1 : 0);
        }


        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                /**
                 * 将可能性b,c,d考虑进去
                 * 之所以没有写可能性a，是因为可能性b，c不可能比可能性a小。
                 * 又因为题目要求返回最大值，所以不用考虑可能性a。
                 */
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (chars1[i] == chars2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }

        return dp[chars1.length - 1][chars2.length - 1];
    }

    public static void main(String[] args) {
        String s1 = "a123bc";
        String s2 = "12dea                                                                                 3f";
        System.out.println(lcse(s1, s2));
    }
}
