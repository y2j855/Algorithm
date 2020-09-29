package com.tony.basis.class01;

import java.util.Arrays;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/27 12:10
 * Description:对数器
 * 模拟大量数据，来测试方法是否有效。
 * 对数器设计思路：
 * 你想要测的方法a
 * 实现复杂度不好但是容易实现的方法b
 * 实现一个随机样本产生器
 * 把方法a和方法b跑相同的随机样本，看看得到的结构是否一样
 * 如果有一个随机样本使得比对结果不一致，打印样本进行人工干预，改对方法a和方法b
 * 当样本数量很多时比对测试依然正确，可以确定方法a已经正确。
 */
public final class Logarithm {
    //打印数组的值
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    //判断2个数组是否相等
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
    //使用jdk自带排序功能
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }
    //拷贝数组
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    //生成随机数组
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        //区间知识补充
        //[]代表开区间，包含等于
        //()代表闭区间，不包含等于
        //Math.random() [0,1)小数
        //Math.random() * N [0,N)小数
        //(int)(Math.random() * N) [0,N-1]四舍五入所以N-1
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            //[-?,?]整数
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }
}
