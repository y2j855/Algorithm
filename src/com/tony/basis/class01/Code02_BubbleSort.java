package com.tony.basis.class01;

import com.tony.basis.Logarithm;
import com.tony.basis.StopWatch;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/28 11:07
 * Description:冒泡排序
 */
public class Code02_BubbleSort {
    public static void bubbleSort(int[] array){
        //0~1
        //1~2
        //2~3
        //i-1~i
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if(array[j] > array[j+1]){
                    swap(array,j,j+1);
                }
            }
        }
    }

    private static void swap(int[] array, int i, int j) {
//        int temp = array[i];
//        array[i] = array[j];
//        array[j] = temp;
        //异或操作实现2个数交换
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
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
            bubbleSort(arr1);
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
        bubbleSort(arr);
        Logarithm.printArray(arr);
    }
}
