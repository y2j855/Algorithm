package com.tony.basis.class10;

import java.util.*;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/24 21:04
 * Description: 图的拓扑排序算法
 * 逻辑思路
 * 1.在图中找到所有入度为0的顶点输出
 * 2.把所有入度为0的顶点在图中删掉，继续找入度为0的顶点输出，周而复始
 * 3.图的所有顶点都被删除后，依次输出的顺序就是拓扑排序
 */
public class Code04_TopologySort {

    public static List<Vertex> sortedTopology(Graph graph) {
        Map<Vertex,Integer> inMap = new HashMap<>();
        Queue<Vertex> zeroInGraph = new LinkedList<>();

        List<Vertex> vertices = new ArrayList<>();
        for (Vertex vertex: graph.nodes.values()) {
            inMap.put(vertex,vertex.in);
            if(vertex.in == 0){
                zeroInGraph.add(vertex);
            }
        }
        while (!zeroInGraph.isEmpty()){
            Vertex current = zeroInGraph.poll();
            vertices.add(current);
            for (Vertex next: current.nexts) {
                inMap.put(next,inMap.get(next) - 1);
                if(inMap.get(next) == 0){
                    zeroInGraph.add(next);
                }
            }
        }
        return vertices;
    }

    public static void main(String[] args) {
        int[][] matrix = {{7, 1, 2}, {4, 2, 3}, {3, 3, 4},
                {14, 3, 5}, {18, 4, 6},{10, 6, 5}};
        Graph graph1 = GraphGenerator.generatorGraph(matrix);
        List<Vertex> result1 = sortedTopology(graph1);
        for (int i = 0; i < result1.size(); i++) {
            System.out.println(result1.get(i).value);
        }

        int[][] test = {{1,1,4},{2,2,4},{3,3,4}};
        Graph graph2 = GraphGenerator.generatorGraph(test);
        List<Vertex> result2 = sortedTopology(graph2);
        for (Vertex v: result2) {
            System.out.println(v.value);
        }
    }
}
