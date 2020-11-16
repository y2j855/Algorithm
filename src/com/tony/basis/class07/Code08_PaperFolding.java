package com.tony.basis.class07;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/16 20:57
 * Description: 折纸面试题
 * 利用中序递归遍历
 */
public class Code08_PaperFolding {

    public static void printAllFolds(int N) {
        printProcess(1, N, true);
    }

    private static void printProcess(int i, int times, boolean down) {
        if (i > times) {
            return;
        }
        printProcess(i + 1, times, true);
        System.out.print(down ? "凹 " : "凸 ");
        printProcess(i+1,times,false);
    }

    public static void main(String[] args) {
        printAllFolds(2);
    }


}
