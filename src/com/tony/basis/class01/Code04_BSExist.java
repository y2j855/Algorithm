package com.tony.basis.class01;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/28 14:33
 * Description:有序数组二分查找，数值是否存在
 */
public class Code04_BSExist {
    /**
     * @param sortedArray 要查找的有序数组
     * @param num   是否包含的数值
     * @return  true/false
     *
     */
    public static boolean exist(int[] sortedArray, int num) {
        if (sortedArray == null || sortedArray.length == 0) {
            return false;
        }
        //最左侧位置
        int L = 0;
        //最右侧位置
        int R = sortedArray.length - 1;
        //中间位置
        int middle = 0;
        //L..R
        while (L < R) {
            //(L + R) / 2;
            // L + ((R - L) / 2) '/2' -> '>> 1'
            // '+ 1' -> '| 1'
            //L + ((R - L) >> 1)
            middle = (L + R) / 2;
            System.out.println("middle=" + middle);
            if (sortedArray[middle] == num) {
                return true;
            } else if (sortedArray[middle] > num) {
                R = middle - 1;
            } else if(sortedArray[middle] < num){
                L = middle + 1;
            }
        }
        System.out.println("left indxe=" +L);
        System.out.println("right indxe=" +R);
        return sortedArray[R] == num;
    }

    public static void main(String[] args) {
        int[] testData = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        boolean exist = exist(testData, 10);
        System.out.println(exist);

    }
}
