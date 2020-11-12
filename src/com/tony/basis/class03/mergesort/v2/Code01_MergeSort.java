package com.tony.basis.class03.mergesort.v2;

import com.tony.basis.Logarithm;

/**
 * @author: Tony.Chen
 * Create Time : 2020/10/28 11:23
 * Description: 归并排序
 */
public class Code01_MergeSort {

    /**
     * 合并方法
     * 将左右两边的数组进行合并，实现归并排序
     *
     * @param arr 数组
     * @param L   数组最左边的下标
     * @param M   数组中间的下标
     * @param R   数组最右边的下标
     */
    public static void merge(int[] arr, int L, int M, int R) {
        //临时数组
        int[] help = new int[R - L + 1];
        //左组下标
        int p1 = L;
        //右组下标
        int p2 = M + 1;
        //临时数组下标
        int i = 0;
        //进行左右比较大小
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        //当左右任意一边出现下标越界，将没有越界的剩余剩余数进行拷贝
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }

        /*
            将临时数组排序好的数放到arr数组中
            L+j:因为是递归调用，每个数组分段都不一样，所以要L+j
         */
        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }
    }

    /**
     * 递归调用实现归并排序   自上而下的
     *
     * T(N) = 2*(N/2) + O(N)
     * 根据公式：T(N) = a*T(N/b) + O(N^d)
     * a=2，b=2，d=1 满足了(log2,2 = 1),所以
     * 时间复杂度：O(N*logN)
     * @param arr 数组
     * @param L   数组第一个下标
     * @param R   数组最后一个下标
     */
    private static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        //将数组分成2份
        int mid = L + ((R - L) >> 1);
        //左边进行排序
        process(arr, L, mid);
        //右边进行排序
        process(arr, mid + 1, R);
        //合并左右两边
        merge(arr, L, mid, R);
    }

    /**
     * 归并排序，对外接口
     *
     * @param arr
     */
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    /**
     * 非递归归并排序  自下而上的
     *
     * @param arr
     */
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //获取数组长度
        int N = arr.length;
        //定义mergeSize,初始化左组的长度
        int mergeSize = 1;
        //外循环将mergeSize翻倍，内循环计算左组，右组和中间数的下标
        while (mergeSize < N) {
            //代表左组的下标，默认从0开始
            int L = 0;
            while (L < N) {
                //计算中间数的下标
                int M = L + mergeSize - 1;
                //当M大于等于N，证明已经没有左组数了
                if (M >= N) {
                    break;
                }
                //如果右组数够，R = M + mergeSize，如果不够N-1，取小的
                int R = Math.min(M + mergeSize, N - 1);
                //进行整合
                merge(arr, L, M, R);
                L = R + 1;
            }
            //防止溢出，没有任何算法上的作用。防止超出int范围
            if (mergeSize > (N >> 1)) {
                break;
            }
            mergeSize <<= 1;
        }
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = Logarithm.generateRandomArray(maxSize, maxValue);
            int[] arr2 = Logarithm.copyArray(arr1);
            mergeSort1(arr1);
            mergeSort2(arr2);
            if (!Logarithm.isEqual(arr1, arr2)) {
                succeed = false;
                Logarithm.printArray(arr1);
                Logarithm.printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");

        int[] arr = Logarithm.generateRandomArray(maxSize,maxValue);
        Logarithm.printArray(arr);
        mergeSort1(arr);
        Logarithm.printArray(arr);
    }
}
