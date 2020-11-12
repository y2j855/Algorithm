package com.tony.basis.class04;

import com.tony.basis.Logarithm;

/**
 * @author: Tony.Chen
 * Create Time : 2020/10/31 10:40
 * Description:
 */
public class Code03_HeapSort {


    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = Logarithm.generateRandomArray(maxSize, maxValue);
            int[] arr2 = Logarithm.copyArray(arr1);
            MyMaxHeap heap = new MyMaxHeap(arr1.length);
            heap.heapSort(arr1);
            Logarithm.comparator(arr2);
            if (!Logarithm.isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = Logarithm.generateRandomArray(maxSize, maxValue);

        MyMinHeap heap = new MyMinHeap(arr.length);
        Logarithm.printArray(arr);
        heap.heapSort(arr);
        Logarithm.printArray(arr);



    }
}
