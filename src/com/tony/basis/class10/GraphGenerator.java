package com.tony.basis.class10;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/24 15:39
 * Description: 图的转化
 * 根据客户端给的数据结构，转换成图的数据结构
 * {
 * {7(权重值),0(from顶点),4(to顶点)},
 * {4,1,3},
 * {3,2,3},
 * {8,2,4}
 * }
 */
public class GraphGenerator {

    /**
     * 客户端给的数据格式，生成对应的图结构
     * @param matrix
     * @return
     */
    public static Graph generatorGraph(int[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            int height = matrix[i][0];
            int fromValue = matrix[i][1];
            int toValue = matrix[i][2];
            if (!graph.nodes.containsKey(fromValue)) {
                graph.nodes.put(fromValue, new Vertex(fromValue));
            }
            if (!graph.nodes.containsKey(toValue)) {
                graph.nodes.put(toValue, new Vertex(toValue));
            }
            Vertex fromVertex = graph.nodes.get(fromValue);
            Vertex toVertex = graph.nodes.get(toValue);
            Edge edge = new Edge(height, fromVertex, toVertex);
            fromVertex.out++;
            fromVertex.nexts.add(graph.nodes.get(toValue));
            fromVertex.edges.add(edge);
            toVertex.in++;

            graph.edges.add(edge);
        }
        return graph;
    }

    /**
     * 通过邻接表生成图结构
     * @return
     */
    public static Graph generatorGraph(){
        Graph graph = new Graph();
        return graph;
    }

    public static void main(String[] args) {
        int[][] matrix = {{7, 0, 4}, {4, 1, 3}, {3, 2, 3}, {8, 2, 4}};
        Graph graph = generatorGraph(matrix);
        System.out.println(graph);
    }

}
