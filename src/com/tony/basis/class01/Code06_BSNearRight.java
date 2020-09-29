package com.tony.basis.class01;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/28 14:59
 * Description:二分查找，有序数组，找<=某个数最左侧的位置
 */
public class Code06_BSNearRight {
    public static int nearestIndex(int[] array, int value) {
        int L = 0;
        int R = array.length - 1;
        int index = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (array[mid] <= value) {
                index = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int[] testData = {1, 2, 2, 2, 3, 3, 3, 4, 5, 6};
        int index = nearestIndex(testData, 2);
        System.out.println(index == 3 ? "true" : "error");
    }
}
