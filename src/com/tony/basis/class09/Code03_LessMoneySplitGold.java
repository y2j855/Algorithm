package com.tony.basis.class09;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/20 10:16
 * Description:贪心算法分割金条
 * 一块金条切成两半，是需要花费和长度数值一样的铜板的。
 * 比如长度为20的金条，不管怎么切，都要花费20个铜板。 一群人想整分整块金条，怎么分最省铜板?
 * 例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为60，金条要分成10，20，30三个部分。
 * 如果先把长度60的金条分成10和50，花费60; 再把长度50的金条分成20和30，花费50;一共花费110铜板。
 * 但如果先把长度60的金条分成30和30，花费60;再把长度30金条分成10和20， 花费30;一共花费90铜板。
 * 输入一个数组，返回分割的最小代价。
 */
public class Code03_LessMoneySplitGold {

    /**
     * 暴力递归实现功能
     *
     * @param arr
     * @return
     */
    public static int lessMoney(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0);
    }

    /**
     * 递归逻辑思路
     * 1.取出数组下边为0，1的数，进行相加操作
     * 2.将数组大小减一，并把相加的值放到数组最后一个位置
     * 3.递归调用1，2步，当数组大小为1(出口/base case)，返回结果
     * 4.最后将所有相加过后的值累加，结果就是需要的最小代价
     * 时间复杂度O(N^2),不太适合实际使用。
     *
     * @param arr 需要计算的数组
     * @param pre 上一次计算的值
     * @return
     */
    private static int process(int[] arr, int pre) {
        if (arr.length == 1) {
            return pre;
        }
        int sum = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                sum = Math.min(sum, process(copyAndMergeTwo(arr, i, j), pre + arr[i] + arr[j]));
            }
        }
        return sum;
    }

    private static int[] copyAndMergeTwo(int[] arr, int i, int j) {
        int[] result = new int[arr.length - 1];
        int resultIndex = 0;
        for (int k = 0; k < arr.length; k++) {
            if (k != i && k != j) {
                result[resultIndex++] = arr[k];
            }
        }
        result[resultIndex] = arr[i] + arr[j];
        return result;
    }

    /**
     * 贪心算法实现获取切割金条，最少代价
     * 1.将数组全部放入小根堆
     * 2.每次取2个相加，然后相加的值放回小根堆,
     * 3.再取出2个相加，此时取出值很可能不是上一步相加的值，因为小根堆只会取最小值。以此类推
     * 4.最后所有相加值得总和就是最少代价
     * 原理就是霍夫曼树。
     * @param arr
     * @return
     */
    public static int lessMoneyUseGreedy(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int sum = 0;
        int current;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            queue.add(arr[i]);
        }
        while (queue.size() > 1) {
            current = queue.poll() + queue.poll();
            sum += current;
            queue.add(current);
        }
        return sum;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 6;
        int maxValue = 1000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            if (lessMoney(arr) != lessMoneyUseGreedy(arr)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
