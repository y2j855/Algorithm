package com.tony.basis.class03.mergesort.v2;

/**
 * @author: Tony.Chen
 * Create Time : 2020/10/29 14:56
 * Description: 快速排序
 */
public class Code03_PartitionAndQuickSort {

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 给定一个数组arr，和一个整数num。num为数组最后一个元素
     * 请把小于等于num的数放在数组的左边，大于num的数放在数组的右边。(左右两边的数可以无序)
     *
     * @param arr 数组
     * @param L   数组第一个下标
     * @param R   数组最后一个下标
     * @return 分区划分好后，<=区数组的下标
     */
    public static int partition(int[] arr, int L, int R) {
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        //小于等于区的下标
        int lessEqual = L - 1;
        //数组的下标
        int index = L;
        /*
         *  我们用数组的最后一个元素当做要划分的值
         *  如果arr[index]小于等于arr[R]，arr[index]与arr[lessEqual]进行交换
         *  lessEqual++，向右扩一个区。
         *  index++
         *  如果arr[index]大于arr[R]，index++
         */
        while (index < R) {
            if (arr[index] <= arr[R]) {
                swap(arr, index, ++lessEqual);
            }
            index++;
        }
        swap(arr, ++lessEqual, R);
        return lessEqual;
    }

    /**
     * 荷兰国旗问题
     * 给定一个数组arr，和一个整数num。num为数组最后一个元素
     * 请把小于num的数放在数组的左边，等于num的数放在中间，大于num的数放在数组的右边。(左右两边的数可以无序)
     *
     * @param arr 数组
     * @param L   数组第一个下标
     * @param R   数组最后一个下标
     * @return 划分后的小于区和大于区小标的问题
     * 例如{1,2,3,3,3,4,7} 返回{2,4}
     */
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        //小于区的下标
        int less = L - 1;
        //大于区的下标,不减1的原因是[R]是我们要划分的值
        int more = R;
        //数组的下标
        int index = L;

        while (index < more) {
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] < arr[R]) {
                swap(arr, index++, ++less);
            } else {
                swap(arr, index, --more);
            }
        }
        swap(arr, more, R);
        return new int[]{less + 1, more};
    }

    /**
     * 快排1.0 每次根据partition返回一个划分值小于区的下标
     *
     * @param arr
     * @param L
     * @param R
     */
    public static void process1(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int M = partition(arr, L, R);
        process1(arr, L, M - 1);
        process1(arr, M + 1, R);
    }

    /**
     * 快排2.0，根据荷兰国旗问题，返回一个划分值小于区，大于区的小标数组
     *
     * @param arr
     * @param L
     * @param R
     */
    public static void process2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] equalArea = netherlandsFlag(arr, L, R);
        process2(arr, L, equalArea[0] - 1);
        process2(arr, equalArea[1] + 1, R);
    }

    /**
     * 快排3.0
     * 划分值从数组最后一个值变成随机指定数组的值
     * 使用荷兰国旗问题返回下标数组
     *
     * @param arr
     * @param L
     * @param R
     */
    public static void process3(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        /*
         *  随机将数组某个值交换到数组末尾
         *  为了实现随机获取划分值
         */
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalArea = netherlandsFlag(arr, L, R);
        process3(arr, L, equalArea[0] - 1);
        process3(arr, equalArea[1] + 1, R);
    }


    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 1, 7, 3, 3};
        quickSort3(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
