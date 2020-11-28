package com.tony.basis.class11;

import java.util.*;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/27 12:28
 * Description:
 * 利用递归打印字符串的所有子序列
 * 例子:abc,打印:a,ab,ac,abc,b,bc,c,不能生成ba
 *
 * TODO 如果要求统计字面值不同的子序列个数，需要动态规划实现
 */
public class Code02_PrintAllSubSequences {

    /**
     * abc
     * a ab abc b bc c
     * 打印所有的子串
     *
     * @param str
     */
    public static void printSubString(String str) {
        char[] chars = str.toCharArray();
        subString(chars);
    }

    private static void subString(char[] chars) {
        if (chars.length == 1) {
            System.out.println(chars[0]);
        } else {
            String temp = "";
            for (int i = 0; i < chars.length; i++) {
                temp += chars[i];
                System.out.println(temp);
            }
            chars = changArray(chars);
            subString(chars);
        }
    }

    /**
     * abc
     * a,ab,ac,abc,b,bc,c
     * 不能生成ba
     * 打印所有的子序列
     *
     * @param str
     * @return
     */
    public static List<String> printSubSequence1(String str) {
        char[] chars = str.toCharArray();
        List<String> result = new ArrayList<>();
        String path = "";
        subSequence(chars, 0, result, path);
        return result;
    }

    /**
     * 打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
     * aac
     * a,aa,ac,c,aac
     *
     * @param str
     * @return
     */
    public static List<String> printSubSequence2(String str) {
        char[] chars = str.toCharArray();
        List<String> result = new ArrayList<>();
        Set<String> set = new HashSet<>();
        String path = "";
        subSequence(chars, 0, set, path);
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        return result;
    }


    /**
     * 不支持重复字符过滤功能
     * 递归深度优先遍历
     * 逻辑思路
     * 1.将字符串中的每个字符都分为要与不要两种情况
     * 2.要带着这个字符继续往下递归遍历，如果不要就不带着
     *
     * @param chars  要进行子序列的字符数组
     * @param index  字符数组的下标索引
     * @param result 存放子序列
     * @param path   记录上一次已加入的字符
     */
    private static void subSequence(char[] chars, int index, List<String> result, String path) {
        if (index == chars.length) {
            result.add(path);
            return;
        }
        //不要当前字符，继续往下递归
        subSequence(chars, index + 1, result, path);

        //要当前字符，继续往下递归
        path += chars[index];
        subSequence(chars, index + 1, result, path);
    }

    /**
     * 支持去掉重复的子序列
     *
     * @param chars
     * @param index
     * @param result
     * @param path
     */
    public static void subSequence(char[] chars, int index, Set<String> result, String path) {
        if (index == chars.length) {
            result.add(path);
            return;
        }
        //不要当前字符，继续往下递归
        subSequence(chars, index + 1, result, path);

        //要当前字符，继续往下递归
        path += chars[index];
        subSequence(chars, index + 1, result, path);
    }

    private static char[] changArray(char[] chars) {
        char[] newChars = new char[chars.length - 1];
        for (int i = 0; i < newChars.length; i++) {
            newChars[i] = chars[i + 1];
        }
        return newChars;
    }

    public static void main(String[] args) {
//        printSubString("abc");
        List<String> strs = printSubSequence1("aac");
        for (String str : strs) {
            System.out.println(str);
        }
        List<String> s2 = printSubSequence2("aac");
        for (String str : s2) {
            System.out.println(str);
        }
    }
}
