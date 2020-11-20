package com.tony.basis.class09;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/20 22:58
 * Description:
 * 输入: 正数数组costs、正数数组profits、正数K、正数M
 * costs[i]表示i号项目的花费，profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * K表示你只能串行的最多做k个项目，M表示你初始的资金
 * 说明: 每做完一个项目，马上获得的收益，可以支持你去做下一个项目。
 * 不能并行的做项目。输出：你最后获得的最大钱数。
 */
public class Code05_IPO {

    private static class Program{
        private int cost;   //花费
        private int profit; //利润

        public Program(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    /**
     * 1.一个小根堆存花费，一个大根堆存利润
     * 2.将项目存放到小根堆，然后根据初始资金放到大根堆
     * 3.初始资金 = 初始资金 + 大根堆的利润，再去循环。
     * 4.能做几个项目就找几次,最后获得最大钱数
     * @param K         串行能做的项目个数
     * @param M         初始资金
     * @param costs     项目的花费数组
     * @param profits   项目的利润数组
     * @return          获得最大钱数(费用+利润)
     */
    public static int findMaximizedCapital(int K, int M, int[] costs, int[] profits){
        PriorityQueue<Program> minCostQ = new PriorityQueue<>(new MyProgramCostComparator());
        PriorityQueue<Program> maxProfitQ = new PriorityQueue<>(new MyProgramProfitComparator());
        for (int i = 0; i < costs.length; i++) {
            minCostQ.add(new Program(costs[i],profits[i]));
        }

        for (int i = 0; i < K; i++) {
            while (!minCostQ.isEmpty()) {
                Program pro = minCostQ.poll();
                if (pro.cost <= M) {
                    maxProfitQ.add(pro);
                }
            }
            if(maxProfitQ.isEmpty()){
                return M;
            }
            M += maxProfitQ.poll().profit;
        }
        return M;
    }

    private static class MyProgramCostComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.cost - o2.cost;
        }
    }

    private static class MyProgramProfitComparator implements Comparator<Program>{

        @Override
        public int compare(Program o1, Program o2) {
            return o2.profit - o1.profit;
        }
    }

    public static void main(String[] args) {
        int[] costs = {10,10};
        int[] profit = {10,20};
        Program p1 = new Program(10,10);
        Program p2 = new Program(10,20);
        Program[] pros = {p1,p2};
        System.out.println(findMaximizedCapital(2,10,costs,profit));
    }
}
