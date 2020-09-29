package com.tony.basis.class01;


import java.util.Arrays;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/27 11:29
 * Description:
 */
public class Code01_SelectionSort {
    public static void selectionSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        //0~n-1
        //1~n-1
        //2~n-1
        for (int i = 0; i < array.length - 1; i++) {
            //最小值在哪个位置上 i~n-1
            int minIndex = i;
            //i+1~n上找最小值的下标
            for (int j = i + 1; j < array.length; j++) {
                //最原始的写法，为了让代码更好读，把j与mindIndex交换。
//                minIndex = array[minIndex] > array[j] ? j : minIndex;
                minIndex = array[j] < array[minIndex] ? j : minIndex;
            }
            swap(array, i, minIndex);
        }
    }

    //数值交换位置
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        StopWatch watch = new StopWatch();
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = Logarithm.generateRandomArray(maxSize, maxValue);
            int[] arr2 = Logarithm.copyArray(arr1);
            selectionSort(arr1);
            Logarithm.comparator(arr2);
            if (!Logarithm.isEqual(arr1, arr2)) {
                succeed = false;
                Logarithm.printArray(arr1);
                Logarithm.printArray(arr2);
                break;
            }
        }
        double time = watch.elapsedTime();
        System.out.println("run time=" + time);
        System.out.println(succeed ? "Nice!" : "arr error");

        int[] arr = Logarithm.generateRandomArray(maxSize, maxValue);
        Logarithm.printArray(arr);
        selectionSort(arr);
        Logarithm.printArray(arr);
    }


}
