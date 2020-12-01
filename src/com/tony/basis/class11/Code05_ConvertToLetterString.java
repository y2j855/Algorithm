package com.tony.basis.class11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/28 17:44
 * Description:
 * 规定1和A对应、2和B对应、3和C对应...,那么一个数字字符串比如“111”，就可以转化为："AAA"、"KA"和"AK"。
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 * <p>
 * 从左往右尝试的尝试模型
 */
public class Code05_ConvertToLetterString {

    public static int number(String str) {
        return process1(str.toCharArray(), 0);
    }

    private static String getLetter(int value) {
        Map<Integer, String> data = new HashMap<>();
        data.put(1, "A");
        data.put(2, "B");
        data.put(3, "C");
        data.put(4, "D");
        data.put(5, "E");
        data.put(6, "F");
        data.put(7, "G");
        data.put(8, "H");
        data.put(9, "I");
        data.put(10, "J");
        data.put(11, "K");
        data.put(12, "L");
        data.put(13, "M");
        data.put(14, "N");
        data.put(15, "O");
        data.put(16, "P");
        data.put(17, "Q");
        data.put(18, "R");
        data.put(19, "S");
        data.put(20, "T");
        data.put(21, "U");
        data.put(22, "V");
        data.put(23, "W");
        data.put(24, "X");
        data.put(25, "Y");
        data.put(26, "Z");

        return data.get(value);
    }

    /**
     * facebook面试原题
     * 逻辑思路
     * 最关键的是找出base case并且考虑特殊情况0
     *
     * @param chars
     * @param index
     * @return
     */
    private static int process1(char[] chars, int index) {
        /**
         * base case
         * 两种情况，理解哪种都可以
         * 例子：11，AA，K
         * 1.到了数组终止位置，没有字符了，认为空字符串也可以转化，所以返回1。(K这种情况)
         * 2.index-1位置已经成功转化了，到了终止位置收集之前的答案，所以返回1。(AA这种情况)
         */
        if (index == chars.length) {
            return 1;
        }
        //不管之前的转化是否成功，当遇到0时就认为这条分支都没有转化成功
        if (chars[index] == '0') {
            return 0;
        }

        /**
         * 判断字符为1或者2的情况
         * 如果为'1'，需要计算当前字符，当前字符和下个字符组合的情况之和
         * 如果为'2'，需要计算当前字符，当前字符和下个字符不超过'0'~'6'的情况之和，因为Z字符为26。
         */
        if (chars[index] == '1') {
            int res = process1(chars, index + 1);
            if (index + 1 < chars.length) {
                res += process1(chars, index + 2);
            }
            return res;
        }
        if (chars[index] == '2') {
            int res = process1(chars, index + 1);
            if (index + 1 < chars.length && (chars[index + 1] >= '0' && chars[index + 1] <= '6')) {
                res += process1(chars, index + 2);
            }
            return res;
        }
        return process1(chars, index + 1);
    }

    //TODO 通过动态规划解决此问题

    public static void main(String[] args) {
        int number = number("11131");
        System.out.println(number);
    }

    private static int covertInt(char ch) {
        int num = 0;
        if (Character.isDigit(ch)) {  // 判断是否是数字
            num = (int) ch - (int) ('0');
        }
        return num;
    }
}
