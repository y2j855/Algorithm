package com.tony.basis.class03.mergesort.v1;

import com.tony.basis.class03.mergesort.v2.Code01_MergeSort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: Tony.Chen
 * Create Time : 2020/10/28 11:11
 * Description:
 */
public class DataChecker {
    private static int[] generateRandomArray() {
        Random r = new Random();

        int[] arr = new int[10000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(10000);
        }
        return arr;
    }

    static void check() {
        int[] arr = generateRandomArray();
        int[] arr2 = new int[arr.length];
        System.arraycopy(arr, 0, arr2, 0, arr.length);

        Arrays.sort(arr);
//        MergeSort.sort(arr2, 0, arr2.length - 1);
        Code01_MergeSort.mergeSort1(arr2);
        boolean same = true;
        for (int i = 0; i < arr2.length; i++) {
            if (arr[i] != arr2[i]) {
                same = false;
            }
        }

        System.out.println(same == true ? "right" : "wrong");
    }

    public static void main(String[] args) {
        check();
    }
}
