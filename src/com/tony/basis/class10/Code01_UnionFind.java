package com.tony.basis.class10;

import java.util.*;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/21 21:27
 * Description: 并查集
 */
public class Code01_UnionFind {



    public static List<UnionSet.Node> generateChildList(int parentId, int length){
        List<UnionSet.Node> list = new LinkedList<>();
        for (int i = 1; i <= length; i++) {
            list.add(new UnionSet.Node( i + 10 * parentId));
        }
        return list;
    }

    /**
     * 创建并查集子链表测试数据
     * @param set
     */
    private static void generateData(UnionSet<Integer> set) {
        for (int i = 1; i <= 10; i++) {
            set.addChild(generateChildList(i,5),i);
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
        UnionSet<Integer> set = new UnionSet<>(list);
        generateData(set);

        System.out.println(set.isSameSet(33,34));
        System.out.println(set.isSameSet(22,33));
        set.union(22,33);
        System.out.println(set.isSameSet(22,33));
    }

}
