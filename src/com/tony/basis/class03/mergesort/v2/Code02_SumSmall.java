package com.tony.basis.class03.mergesort.v2;

/**
 * @author: Tony.Chen
 * Create Time : 2020/10/28 21:34
 * Description:
 * 求数组小和问题
 * 在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫数组小和。求数组小和。
 * 例子：[1,3,4,2,5]
 * 1左边比1小的数：没有
 * 3左边比3小的数：1
 * 4左边比4小的数：1，3
 * 2左边比2小的数：1
 * 5左边比5小的数：1，3，4，2
 * 所以数组的小和为：1+1+3+1+1+3+4+2=16
 *
 * 解题思路
 * 1.当左组数 < 右组数，产生小和。将左组数拷贝到数组中
 * 2.当左组数>= 右组数,不产生小和。将右组数拷贝到数组中
 * 3.产生小和的个数是根据右组有几个比左组的大，有几个产生几个左组数
 */
public class Code02_SumSmall {

    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int L, int R) {
        if (L == R) {   //base case
            return 0;
        }
        int middle = L + ((R - L) >> 1);
        return process(arr, L, middle) + process(arr, middle + 1, R) + merge(arr, L, middle, R);
    }

    private static int merge(int[] arr, int L, int M, int R) {
        //临时数组，存放排序号的内容
        int[] help = new int[R - L + 1];
        //左组下标
        int p1 = L;
        //右组下标
        int p2 = M + 1;
        //临时数组下标
        int h = 0;

        //数组小和值
        int value = 0;

        while (p1 <= M && p2 <= R) {
            /*
                左组数小于右组数，产生当前右组数到数组末尾的总个数的小和
                因为左组和右组已经是有序的数了。
                所以当前右组数大，意味着后边的数都大。
             */
            value += arr[p1] < arr[p2] ? (R - p2 + 1) * arr[p1] : 0;
            help[h++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= M) {
            help[h++] = arr[p1++];
        }

        while (p2 <= R) {
            help[h++] = arr[p2++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }

        return value;
    }

    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
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

    // for test
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

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (smallSum(arr1) != comparator(arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");
    }
}
