package com.tony.basis.class10;

import java.util.*;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/24 20:40
 * Description: 图深度优先遍历
 * 逻辑思路
 * 利用栈实现
 * 从源节点开始把节点按照深度放入栈，然后弹出
 * 每弹出一个点，把该节点下一个没有进过栈的邻接点放入栈
 * 直到栈变空
 */
public class Code03_DFS {

    public static void dfs(Vertex vertex){
        if(vertex == null){
            return;
        }
        Stack<Vertex> stack = new Stack<>();
        Set<Vertex> checkRepeat = new HashSet<>();

        stack.push(vertex);
        checkRepeat.add(vertex);
        System.out.println(vertex.value);
        while (!stack.isEmpty()){
            Vertex current = stack.pop();
            for (Vertex next: current.nexts) {
                if(!checkRepeat.contains(next)){
                    stack.push(current);
                    stack.push(next);
                    checkRepeat.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{7, 1, 2}, {4, 1, 3}, {3, 1, 4},
                {14, 2, 3}, {18, 2, 5},
                {10, 3, 1}, {15, 3, 2}, {16, 3, 4}, {12, 3, 5}, {13, 5, 3},};
        Graph graph = GraphGenerator.generatorGraph(matrix);
        Vertex vertex = graph.nodes.get(1);
        dfs(vertex);
    }
}
