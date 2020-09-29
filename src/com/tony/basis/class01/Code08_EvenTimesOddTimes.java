package com.tony.basis.class01;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/28 22:48
 * Description:异或运算
 */
public class Code08_EvenTimesOddTimes {

    /**
     * 数组中，只有一种数出现奇数次，其他都是偶数次
     *
     * @param array 需要运算的数组
     */
    public static void printOddTimesNum1(int[] array) {
        int eor = 0;
        for (int i = 0; i < array.length; i++) {
            eor ^= array[i];
        }
        System.out.println(eor);
    }

    /**
     * 方法思路
     * 1.将整个数组进行异或操作，得到2个奇数次的eor
     * 2.将这个eor算出最右侧为1的二进制数
     * 3.在用一个变量，遍历数组中所有最右侧为1的数。
     * 又因为eor!=0,所以这两个数的最右侧只有一个为1，一个为0这一种情况。
     * 4.这样遍历出来的值就是其中一个奇数次的值，再用eor^这个值就得到了另一个值。
     * @param array 需要运算的数组
     */
    public static void printOddTimesNum2(int[] array) {
        int eor = 0;
        for (int i = 0; i < array.length; i++) {
            eor ^= array[i];
        }

        int rightOne = eor & ((~eor) + 1);  //获取二进制最右侧是1的值
        int onlyOne = 0;//eor'  //获取所有最右侧是1的值，包括偶数次。

        for (int i = 0; i < array.length; i++) {
            if ((array[i] & rightOne) != 0) {
                onlyOne ^= array[i];
            }
        }
        System.out.println(onlyOne + " " + (eor ^ onlyOne));
    }

    /**
     * 打印整型二进制有多少个1
     *
     * @param number
     * @return
     */
    public static int bit1Counts(int number) {
        int count = 0;
        //10111011100
        //00000000100最右侧的1
        //10111011000异或

        //10111011000
        //00000001000
        //10111010000
        //...

        while (number != 0) {
            //取出二进制最右侧的1
            int rightOne = number & ((~number) + 1);
            count++;
            //将最右侧的1变为0
            number ^= rightOne;
            //number -= rightone 不减是因为考虑负数问题。
        }
        return count;
    }

    public static void main(String[] args) {
        int a = 5;
        int b = 7;

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println(a);
        System.out.println(b);

        int[] arr1 = {3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1};
        printOddTimesNum1(arr1);

        int[] arr2 = {4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2};
        printOddTimesNum2(arr2);

        int number = 1500;
        System.out.println(Integer.toBinaryString(number));
        System.out.println(bit1Counts(number));

    }

}
