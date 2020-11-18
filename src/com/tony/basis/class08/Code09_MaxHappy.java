package com.tony.basis.class08;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/18 17:17
 * Description:
 * 派对的最大快乐值
 * 公司的每个员工都符合 Employee 类的描述。整个公司的人员结构可以看作是一棵标准的、没有环的多叉树。树的头节点是公司唯一的老板。
 * 除老板之外的每个员工都有唯一的直接上级。 叶节点是没有任何下属的基层员工(subordinates列表为空)，除基层员工外，每个员工都有一个或多个直接下级。
 * 这个公司现在要办party，你可以决定哪些员工来，哪些员工不来，规则：
 * 1.如果某个员工来了，那么这个员工的所有直接下级都不能来
 * 2.派对的整体快乐值是所有到场员工快乐值的累加
 * 3.你的目标是让派对的整体快乐值尽量大
 * 给定一棵多叉树的头节点boss，请返回派对的最大快乐值。
 */
public class Code09_MaxHappy {

    private static class Employee {
        public int happy;
        public List<Employee> nexts;

        public Employee(int h) {
            happy = h;
            nexts = new ArrayList<>();
        }

    }

    private static class Info{
        private int yes;
        private int no;

        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }

    public static int getMaxHappy(Employee boss){
        if(boss == null){
            return 0;
        }
        Info all = process(boss);
        return Math.max(all.yes,all.no);
    }

    private static Info process(Employee boss) {
        if(boss.nexts.isEmpty()){
            return new Info(boss.happy, 0);
        }
        int yes = 0;
        int no = 0;

        for (Employee employee: boss.nexts) {
            Info nextEmployee = process(employee);
            yes += nextEmployee.no;
            no += Math.max(nextEmployee.no,nextEmployee.yes);
        }
        return new Info(yes,no);
    }

    // for test
    public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
        genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    // for test
    public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
            e.nexts.add(next);
            genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
            System.out.println(getMaxHappy(boss));
        }
        System.out.println("finish!");
    }
}
