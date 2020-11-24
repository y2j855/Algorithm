package com.tony.basis.class10;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/24 20:14
 * Description: 图宽度优先遍历
 * 逻辑思路
 * 利用队列实现
 * 从源节点开始依次按照宽度进队列，然后弹出
 * 每弹出一个点，把该节点所有没有进过队列的邻节点放入队列
 * 直到队列边控
 */
public class Code02_BFS {
    public static void bsf(Vertex vertex) {
        if (vertex == null) {
            return;
        }
        Queue<Vertex> queue = new LinkedList<>();
        Set<Vertex> checkRepeat = new HashSet<>();
        queue.add(vertex);
        checkRepeat.add(vertex);
        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            System.out.println(current.value);
            for (Vertex next : current.nexts) {
                if (!checkRepeat.contains(next)) {
                    queue.add(next);
                    checkRepeat.add(next);
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
        bsf(vertex);
    }
}
