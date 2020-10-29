package com.tony.basis.class03.mergesort.v1;

/**
 * @author: Tony.Chen
 * Create Time : 2020/10/28 10:11
 * Description:
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {1, 4, 7, 8, 3, 6, 9, 7};
        sort(arr, 0, arr.length - 1);

        print(arr);

    }

    /**
     * 递归排序，实现归并排序
     *
     * @param arr   数组
     * @param left  数组第一个位置下标
     * @param right 数组最后一个位置下标
     */
    public static void sort(int[] arr, int left, int right) {
        //base code
        if (left == right) {
            return;
        }
        //分成2半
        int mid = left + ((right - left) >> 1);
        //左边排序
        sort(arr, left, mid);
        //右边排序
        sort(arr, mid + 1, right);
        //左右进行合并
        merge(arr, left, mid + 1, right);
    }

    /**
     * 前提：左右两边已经排好序
     * 归并方法
     *
     * @param arr          需要被排序的数组
     * @param leftPointer  需要被排序的数组下标第一个位置
     * @param rightPointer 需要被排序的数组下标中间位置+1
     * @param rightBound   需要被排序的数组下标最后一个位置
     */
    private static void merge(int[] arr, int leftPointer, int rightPointer, int rightBound) {
        //数组的中间位置，因为传过来的参数是中间位置+1，所以-1
        int mid = rightPointer - 1;
        //左组下标
        int i = leftPointer;
        //右组下标
        int j = rightPointer;
        //临时数组下标
        int k = 0;
        //临时数组 最后一个位置下标 - 第一个位置下标 + 1=数组长度
        int[] temp = new int[rightBound - leftPointer + 1];

        while (i <= mid && j <= rightBound) {
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= rightBound) {
            temp[k++] = arr[j++];
        }

        for (int l = 0; l < temp.length; l++) {
            //从分好数组的起始位置开始拷贝
            arr[leftPointer + l] = temp[l];
        }
    }

    private static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
