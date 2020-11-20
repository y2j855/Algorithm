package com.tony.basis.class09;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/20 22:11
 * Description: 贪心算法
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
 * 给你每一个项目开始的时间和结束的时间,你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。返回最多的宣讲场次。
 */
public class Code04_BestArrange {

    private static class Program {
        private int startTime;
        private int endTime;

        public Program(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    /**
     * 暴力递归入口
     *
     * @param programs
     * @return
     */
    public static int bestArrangeUseRecursion(Program[] programs) {
        if (programs == null || programs.length == 0) {
            return 0;
        }
        return process(programs, 0, 0);
    }

    /**
     * 逻辑思路
     * 1.出口/base case:当没有项目需要安排会议室，返回已安排的次数
     * 2.如果有项目要安排，循环遍历，如果要安排的项目还没有过当前时间点，
     * 将要被安排的项目从数组中移除，更新已安排的数量，递归调用
     *
     * @param programs 需要开会的项目
     * @param done     已安排会议的数量
     * @param timeLine 目前的时间点
     * @return 能安排的场次
     */
    private static int process(Program[] programs, int done, int timeLine) {
        if (programs.length == 0) {
            return done;
        }
        int max = done;
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].startTime >= timeLine) {
                Program[] next = copyButExcept(programs, i);
                max = Math.max(max, process(next, done + 1, programs[i].endTime));
            }
        }
        return max;
    }


    /**
     * 将被安排的项目从数组中移除
     *
     * @param programs
     * @param useIndex
     * @return
     */
    private static Program[] copyButExcept(Program[] programs, int useIndex) {
        Program[] newData = new Program[programs.length - 1];
        int index = 0;
        for (int j = 0; j < programs.length; j++) {
            if (j != useIndex) {
                newData[index++] = programs[j];
            }
        }
        return newData;
    }

    public static int bestArrangeUseGreedy(Program[] programs) {
        if (programs == null || programs.length == 0) {
            return 0;
        }
        Arrays.sort(programs, new Comparator<Program>() {
            @Override
            public int compare(Program o1, Program o2) {
                return o1.endTime - o2.endTime;
            }
        });

        int result = 0;
        int timeLine = 0;
        for (int i = 0; i < programs.length; i++) {
            if(timeLine <= programs[i].startTime){
                result++;
                timeLine = programs[i].endTime;
            }
        }
        return result;
    }

    // for test
    public static Program[] generatePrograms(int programSize, int timeMax) {
        Program[] ans = new Program[(int) (Math.random() * (programSize + 1))];
        for (int i = 0; i < ans.length; i++) {
            int r1 = (int) (Math.random() * (timeMax + 1));
            int r2 = (int) (Math.random() * (timeMax + 1));
            if (r1 == r2) {
                ans[i] = new Program(r1, r1 + 1);
            } else {
                ans[i] = new Program(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int programSize = 12;
        int timeMax = 20;
        int timeTimes = 1000000;
        for (int i = 0; i < timeTimes; i++) {
            Program[] programs = generatePrograms(programSize, timeMax);
            if (bestArrangeUseRecursion(programs) != bestArrangeUseGreedy(programs)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
