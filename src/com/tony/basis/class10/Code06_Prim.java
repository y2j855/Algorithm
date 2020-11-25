package com.tony.basis.class10;

import java.util.*;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/25 11:10
 * Description: 图的最小生成树prim算法
 */
public class Code06_Prim {

    /**
     * 逻辑思路
     * 1.定义小根堆，以权重值排序
     * 2.防止森林出现，循环遍历图上每个顶点
     * 3.随机选取任意一个点，然后加入到判重的set集合中，没有加入过加入此顶点
     * 4.遍历此顶点的所有边，将所有边加入到小根堆中，然后也是重复的不能加入。
     * 5.循环遍历小根堆，因为from点一开始就加入到set集合中了，所以只需要获取边的to顶点
     * 6.判断to顶点是否加入过，如果没有此条对应的边就可以放入结果中，然后再去将to顶点的所有边加入小根堆
     * 7.以此循环，循环结束后所有加入结果的边就是最小生成树。
     *
     * 注意：如果是有向图，方向很重要，如果方向没有设计好，一样取不到最小生成树。
     * 推荐使用无向图进行生成最小生成树
     * @param graph
     * @return
     */
    public static Set<Edge> primMST(Graph graph) {
        Set<Edge> result = new HashSet<>();
        PriorityQueue<Edge> queue = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.height - o2.height;
            }
        });
        Set<Vertex> addedVertex = new HashSet<>();
        Set<Edge> addedEdge = new HashSet<>();

        //为了防止森林的出现，就是一个图包含多个独立的图
        for (Vertex vertex : graph.nodes.values()) {
            if (!addedVertex.contains(vertex)) {
                addedVertex.add(vertex);
                for (Edge edge : vertex.edges) {
                    if (!addedEdge.contains(edge)) {
                        queue.add(edge);
                        addedEdge.add(edge);
                    }
                }
                while (!queue.isEmpty()) {
                    Edge current = queue.poll();
                    Vertex to = current.to;
                    if (!addedVertex.contains(to)) {
                        addedVertex.add(to);
                        result.add(current);
                        for (Edge edge : to.edges) {
                            queue.add(edge);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {{100, 1, 2}, {50, 2, 3}, {3, 3, 4}, {1, 2, 4}, {2, 1, 4}};
        Graph graph = GraphGenerator.generatorGraph(matrix);
        Set<Edge> edges = primMST(graph);
        Iterator<Edge> iterator = edges.iterator();
        while (iterator.hasNext()) {
            Edge next = iterator.next();
            System.out.print(next.height + " vertex from=" + next.from.value + " to=" + next.to.value);
            System.out.println();
        }
    }
}
