package com.tony.basis.class10;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/24 15:30
 * Description:图的边对象
 */
public class Edge {
    /**
     * height 权重值
     * from 边的开始顶点
     * to   边的结束顶点
     */
    private int height;
    private Vertex from;
    private Vertex to;

    public Edge(int height, Vertex from, Vertex to) {
        this.height = height;
        this.from = from;
        this.to = to;
    }
}
