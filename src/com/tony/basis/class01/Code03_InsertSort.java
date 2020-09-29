package com.tony.basis.class01;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/28 11:59
 * Description:
 */
public class Code03_InsertSort {
    public static void insertSort(int[] array){
        for (int i = 1; i < array.length; i++) {
            for (int j = i-1; j >=0 && array[j] > array[j+1] ; j--) {
                swap(array,j,j+1);
            }
        }
    }

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
            insertSort(arr1);
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
        insertSort(arr);
        Logarithm.printArray(arr);
    }
}
