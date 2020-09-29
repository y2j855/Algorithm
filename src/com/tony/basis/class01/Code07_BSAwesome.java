package com.tony.basis.class01;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/28 15:30
 * Description:二分法，局部最小问题
 * 无序数组，任意两个相邻的数都不相等，返回任何一个局部最小
 * 如果下面1,2都不满足肯定可以使用二分法来找中间的局部最小,
 * 因为它的是指增长趋势就是从下往上的抛物线。从而中间必有局部最小。
 *
 * 局部最小问题只要满足三种条件的任意一种
 * 1.头部最小：第一个元素小于第二个元素
 * 2.尾部最小：最后一个元素小于前一个元素
 * 3.中间最小：比前一个和后一个都小
 */
public class Code07_BSAwesome {
    public static int getLessIndex(int[] array) {
        if (array == null || array.length == 0) {
            return -1;
        }
        //头部最小
        if (array.length == 1 || array[0] < array[1]) {
            return 0;
        }
        //尾部最小
        if (array[array.length - 1] < array[array.length - 2]) {
            return array.length - 1;
        }
        //中间最小
        int L = 1;
        int R = array.length - 2;
        int mid = 0;
        while (L < R) {
            mid = (L + R) / 2;
            if (array[mid] > array[mid - 1]) {
                //左边二分
                R = mid - 1;
            } else if (array[mid] > array[mid + 1]) {
                //右边二分
                L = mid + 1;
            } else {
                return mid;
            }
        }
        return L;
    }

    public static void main(String[] args) {
        int[] testData = {9, 8, 7, 6, 9, 3, 8, 4, 5, 6};
        int index = getLessIndex(testData);
        System.out.println(index);
        System.out.println(index == 3 ? "true" : "error");

        int a = 10;
        System.out.println(10 | 1 | 1);
        System.out.println(10 + (~2 + 1));
    }
}
