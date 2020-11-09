package com.tony.basis.class05;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/9 19:44
 * Description: 基数排序
 * 代码思路
 * 前提：只接受非负的10进制整数。如果想要支持小数代码逻辑就要做大的修改
 * 1.找到数组中最大值，并确定它是几位数
 * 2.将数组中每个数的个位，十位，百位...单独生成对应的数，一共有几位是根据最大值生成的
 * 3.生成一个count数组，固定长度为10，因为我们默认所有数值都是10进制值的
 * 4.将count数组进行前缀累加生成count'数组
 * 5.help数组与原数组等长，用来从个位一直到最大数的最大位进行每个数的位置定位，存放到help数组里
 * 6.最后再将遍历好的help数组放到原数组里
 */
public class Code04_RadixSort {

    /**
     * only for no-negative value
     *
     * @param array
     */
    public static void radixSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        radixSort(array, 0, array.length - 1, maxbits(array));
    }

    /**
     * 核心就是用2个数组代替了10个桶
     * @param array 需要排序的数组
     * @param L 起始位置下标
     * @param R 结束位置下标
     * @param digit 最大值有几位
     */
    private static void radixSort(int[] array, int L, int R, int digit) {
        //因为是十进制，所以固定大小就是10
        final int radix = 10;
        int[] help = new int[R - L + 1];
        //[101,003,202,41,302]
        //保存每一位0~9出现的次数,比如按个位算
        //count  = {0,2,2,1,0,0,0,0,0,0},十位百位以此类推
        //count' = {0,2,4,5,5,5,5,5,5,5},将count数组前缀累加
        //count'值得含义 <=0 0个,<=1 2个,<=2 4个，以此类推
        for (int d = 1; d <= digit; d++) {
            int[] count = new int[radix];
            //记录排序的数组每一位数字出现的次数
            for (int i = 0; i < array.length; i++) {
                count[getDigit(array[i], d)]++;
            }
            //生成将count数组前缀累加后的count'数组
            int sum = 0;
            for (int i = 0; i < count.length; i++) {
                sum += count[i];
                count[i] = sum;
            }
            //count'数组记录的值，通过遍历从左到右遍历原数组将值放到help数组对应位置
            //然后count'对应的值--
            // help中'-1'是因为位置与下标差1，例如：第3位，下标对应就是2
            for (int i = array.length - 1; i >= 0; i--) {
                int bit = getDigit(array[i], d);
                help[count[bit] - 1] = array[i];
                count[bit]--;
            }

            for (int i = 0; i < array.length; i++) {
                array[i] = help[i];
            }
        }
    }

    /**
     * 数组中最大值的位数
     *
     * @param array
     * @return
     */
    private static int maxbits(int[] array) {
        int bit = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            max = Math.max(max, array[i]);
        }
        while (max > 0) {
            max /= 10;
            bit++;
        }
        return bit;
    }

    /**
     * 获取整数每一位单独的值
     *
     * @param x
     * @param d
     * @return
     */
    public static int getDigit(int x, int d) {
        //pow 10 ^ d-1
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }

    //计数器 for test
    public static void comparator(int[] arr){
        Arrays.sort(arr);
    }

    public static int[] generateRandomArray(int maxSize,int maxValue){
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    public static int[] copyArray(int[] arr){
        if(arr == null){
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static boolean isEqual(int[] arr1,int[] arr2){
        if((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)){
            return false;
        }
        if(arr1 == null && arr2 == null){
            return true;
        }
        if(arr1.length != arr2.length){
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if(arr1[i] != arr2[i]){
                return false;
            }
        }
        return true;
    }

    public static void printArrat(int[] arr){
        if(arr == null){
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * 用传统的方法，10个桶装入对应的值
     * @param arr
     */
    public static void sort(int[] arr){
        int length = arr.length;

        //最大值
        int max = arr[0];
        for(int i = 0;i < length;i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }
        //当前排序位置
        int location = 1;

        //桶列表
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<>();

        //长度为10 装入余数0-9的数据
        for(int i = 0; i < 10; i++){
            bucketList.add(new ArrayList());
        }

        while(true)
        {
            //判断是否排完
            int dd = (int)Math.pow(10,(location - 1));
            if(max < dd){
                break;
            }

            //数据入桶
            for(int i = 0; i < length; i++)
            {
                //计算余数 放入相应的桶
                int number = ((arr[i] / dd) % 10);
                bucketList.get(number).add(arr[i]);
            }

            //写回数组
            int nn = 0;
            for (int i=0;i<10;i++){
                int size = bucketList.get(i).size();
                for(int ii = 0;ii < size;ii ++){
                    arr[nn++] = bucketList.get(i).get(ii);
                }
                bucketList.get(i).clear();
            }
            location++;
        }
    }

    public static void main(String[] args) {
//        int testTime = 500000;
//        int maxSize = 100;
//        int maxValue = 100000;
//        boolean succeed = true;
//        for (int i = 0; i < testTime; i++) {
//            int[] arr1 = generateRandomArray(maxSize,maxValue);
//            int[] arr2 = copyArray(arr1);
//            radixSort(arr1);
//            comparator(arr2);
//            if(!isEqual(arr1,arr2)){
//                succeed = false;
//                printArrat(arr1);
//                printArrat(arr2);
//                break;
//            }
//        }
//        System.out.println(succeed ? "Nice!" : "function error!");
//
//        int[] arr = generateRandomArray(maxSize,maxValue);
//        printArrat(arr);
//        radixSort(arr);
//        printArrat(arr);

    }
}
