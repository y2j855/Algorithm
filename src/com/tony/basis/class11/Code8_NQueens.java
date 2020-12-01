package com.tony.basis.class11;

/**
 * @author: Tony.Chen
 * Create Time : 2020/12/1 11:24
 * Description: 暴力递归
 * N皇后问题是指在N*N的棋盘上要摆N个皇后，要求任何两个皇后不同行、不同列，也不在同一条斜线上。给定一个整数n，返回n皇后的摆法有多少种。
 * n=1，返回1。n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0。n=8，返回92。
 */
public class Code8_NQueens {

    public static int number1(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];

        return process1(0, record);
    }

    public static int number2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        //如果n=8，8皇后问题，limit最右8个1，其他都是0
        //(1<<n) -1 : 000..100000000 - 1 = 000...11111111
        //limit就是8皇后拍好后的结果
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    /**
     * N皇后问题
     * 逻辑思路
     * 1.利用record来记录皇后的位置，它的长度代表总行数(从0开始)，每个值代表对应行数的列(从0开始)
     * 2.任何两个皇后不能在同行，同列，同斜线
     * 例子：record[0]:0代表第0行，record[0]=1代表第1列，假设用a,b替代
     * record[1]:1代表第1行，record[1]=0代表第0列。假设用c,d替代
     * 如果b==d代表同列，如果|a-c| = |b-d|代表同一条斜线。
     * 3.根据2就能判断index位置放皇后是否有效，如果无效则继续换下一个列位置，如果有效记录。
     *
     * @param index  当前数组索引下标，行数
     * @param record index-1的已排好皇后位置的记录
     * @return
     */
    private static int process1(int index, int[] record) {
        //当前index已到最后一行，返回index-1的结果1。base case
        if (index == record.length) {
            return 1;
        }
        int result = 0;
        //当前行index，每一列的检查
        for (int i = 0; i < record.length; i++) {
            if (isVaild(record, index, i)) {
                record[index] = i;
                result += process1(index + 1, record);
            }
        }
        return result;
    }

    /**
     * 利用位运算解决N皇后问题，提高常数项的计算时间，时间复杂度O(N^N)
     * 逻辑思路
     * 1.利用一个000...11111111值代表N乘N的皇后，N是多少就代表有多少个1，它就是所有皇后放满的结果。也就是参数limit
     * 2.colLimit就是代表当前行皇后所在列的位置
     * 比如第一行的皇后位置就表示            000...01000000
     * 那么它的下一行左斜线限制leftDiaLim    000...10000000
     * 下一行右斜线限制rightDiaLim         000...00100000
     * 3.将列后8位 | left后8位 | right后8位 = 000...11100000就是下一行能放皇后的位置
     * 4.到了第二行时
     * 皇后的位置就变成                                        000...01000010
     * 下一行左斜线限制再上一行左斜线限制的基础上再同样加皇后的位置加1
     * left 000...1000010,然后统一左移一位<<1              ==  000...00000100
     * 下一行右斜线限制再上一行右斜线限制的基础上再同样加皇后的位置加1
     * right 000...00100010,然后统一右移一位>>>1(无符号)    ==  000...00010001
     * 以此类推，这样最后就是要的结果。
     *
     * @param limit       划定了问题的规模 -> 固定，就是最后皇后都放满的结果
     * @param colLim      列的限制，1的位置不能放皇后，0的位置可以
     * @param leftDiaLim  左斜线的限制，1的位置不能放皇后，0的位置可以
     * @param rightDiaLim 右斜线的限制，1的位置不能放皇后，0的位置可以
     * @return
     */
    public static int process2(int limit,
                               int colLim,
                               int leftDiaLim,
                               int rightDiaLim) {
        //如果列限制与limit一样，证明所有皇后都已排好
        if (colLim == limit) {
            return 1;
        }
        /**
         * 所有待选皇后的位置，都在pos上
         * 1.colLim | leftDiaLim | rightDiaLim 下一行可选皇后位置或叫总限制
         * 2.~(colLim | leftDiaLim | rightDiaLim) 左侧的一堆0是干扰位置变成1，右侧每个1是可尝试放皇后位置
         * 与(&)操作的目的：
         * 1).limit & 2,将左侧所有的1变为0。
         * 2).将移动左侧的1变为0，比如000...10000000,它的左斜线的1会移动到左侧，所以我们需要将1变为0
         */
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        int mostRightOne = 0;
        int result = 0;
        while (pos != 0) {
            //pos中提取最右侧的1.剩下都为0
            mostRightOne = pos & ((~pos) + 1);
            pos = pos - mostRightOne;

            result += process2(limit, colLim | mostRightOne,
                    (leftDiaLim | mostRightOne) << 1,
                    (rightDiaLim | mostRightOne) >>> 1);
        }
        return result;
    }

    /**
     * 判断当前皇后是否可以放置
     * 判断条件：
     * 1.是否同列
     * 2.是否在一条斜线上
     *
     * @param record
     * @param currentRow   当前行数
     * @param recordColumn 当前列数
     * @return
     */
    private static boolean isVaild(int[] record, int currentRow, int recordColumn) {
        //判断当前行当前列与之前所有行列的皇后进行检查
        for (int i = 0; i < currentRow; i++) {
            if (recordColumn == record[i] || (Math.abs(record[i] - recordColumn) == Math.abs(currentRow - i))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 14;

        long start = System.currentTimeMillis();
        System.out.println(number1(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(number2(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");
    }
}
