package com.tony.basis.class12;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author: Tony.Chen
 * Create Time : 2020/12/3 20:19
 * Description: 动态规划
 * 给定一个字符串str，给定一个字符串类型的数组arr。arr里的每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来。
 * 返回需要至少多少张贴纸可以完成这个任务。例子：str= "babac"，arr = {"ba","c","abcd"},至少需要两张贴纸"ba"和"abcd"，
 * 因为使用这两张贴纸，把每一个字符单独剪开，含有2个a、2个b、1个c。是可以拼出str的。所以返回2。
 */
public class Code02_StickersToSpellWord {

    public static int minStickers(String[] stickers, String target) {
        if (stickers == null || stickers.length == 0 || target == null || target.equals("")) {
            return 0;
        }

        Set<Character> set = new HashSet<>();
        int[][] map = new int[stickers.length][26];
        for (int i = 0; i < map.length; i++) {
            char[] currentChars = stickers[i].toCharArray();
            for (int j = 0; j < currentChars.length; j++) {
                map[i][currentChars[j] - 'a']++;
                set.add(currentChars[j]);
            }
        }
        if (!isValid(target, set)) {
            return 0;
        }
        Map<String, Integer> dp = new HashMap<>();
        dp.put("", 0);

        return process(target, map, dp);
    }

    /**
     * 判断贴纸中是否有目标字符串的字符
     * @param target    目标字符串
     * @param stickers  可用的贴纸
     * @return
     */
    private static boolean hasChar(int[] target,int[] stickers){
        int number = 0;
        for (int i = 0; i < target.length; i++) {
            /**
             * 属于业务逻辑
             * 因为2个int数组都是通过字符转过来的，所以下标0~25代表a~z的位置
             * 值代表包含对应字母的个数
             * 判断当前字符是否存在，如果存在，用当前目标的字符减去贴纸的字符如果不等于目标字符的个数，
             * 证明贴纸中也包含目标的字符。
             */
            if(target[i] != 0 && target[i] - stickers[i] != target[i]){
                number++;
            }
        }
        if(number != 0){
            return true;
        }
        return false;
    }


    /**
     * 暴力递归加记忆化搜索   返回最小贴纸数
     * 分析问题发现不用转成动态规划，因为可变参数的范围太大。
     * 逻辑思路
     * 将目标字符串，通过贴纸进行递归遍历
     * 递归遍历方式是，目标字符串对每个贴纸遍历一遍，子步骤也是将目标字符串的每个贴纸遍历一遍
     * aaa,abc,ccc
     *              aaabbbc
     *             /   |   \
     *         aaa/ abc|    \ccc
     *           /     |     \
     *         bbbc  aabb   aaabbb  以此类推
     *
     * @param rest
     * @param map
     * @param dp
     * @return
     */
    private static int process(String rest, int[][] map, Map<String, Integer> dp) {
        if (dp.containsKey(rest)) {
            return dp.get(rest);
        }
        /**
         * 1.将目标字符串也转换成一位数组，数组里存放26的字母出现的次数，下标代表哪个字母
         * 2.利用map与目标字符数组相减，得到剩余的部分。
         * 3.将剩余部分转成字符串传入子步骤中
         */
        int result = Integer.MAX_VALUE;
        int N = map.length;
        char[] target = rest.toCharArray();
        int[] tmap = new int[26];
        for (char c : target) {
            tmap[c - 'a']++;
        }
        for (int i = 0; i < N; i++) {
            /**
             * 判断当前目标字符串的首字符是否包含在贴纸中，如果不包含，继续下张贴纸检查。
             * 这样防止了死循环，比如如果当前字符串为bbc,当前贴纸为aaa，这样如果不做处理
             * 程序会进入死循环，导致堆栈溢出错误。
             */
            if (map[i][target[0] - 'a'] == 0) {
                continue;
            }

            //判断当前贴纸是否包含当前字符串的字符，只要有一个字符就算包含,与上边的代码功能一样。
//            if(!hasChar(tmap,map[i])){
//                continue;
//            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; j++) {
                if (tmap[j] > 0) {
                    for (int k = 0; k < Math.max(0, tmap[j] - map[i][j]); k++) {
                        sb.append((char) (j + 'a' ));
                    }
                }
            }
            String s = sb.toString();
            int temp = process(s, map, dp);
            if (temp != -1) {
                result = Math.min(result, 1 + temp);
            }
        }
        dp.put(rest, result == Integer.MAX_VALUE ? -1 : result);
        return dp.get(rest);
    }

    /**
     * 判断目标字符串是否存在贴纸中不包含的字符
     *
     * @param target
     * @param set
     * @return
     */
    private static boolean isValid(String target, Set<Character> set) {
        char[] targetChars = target.toCharArray();
        for (int i = 0; i < targetChars.length; i++) {
            if (!set.contains(targetChars[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] stickers = {"aaa", "abc", "bbb", "cdef"};
        System.out.println(minStickers(stickers, "aaabbc"));
    }
}
