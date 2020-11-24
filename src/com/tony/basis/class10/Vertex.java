package com.tony.basis.class10;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/24 15:27
 * Description: 图的顶点对象
 */
public class Vertex {
    /**
     * value 顶点的值
     * in 入度
     * out 出度
     * nexts 顶点对应的邻居顶点
     * edges 顶点与邻居顶点形成的边
     */
    private int value;
    private int in;
    private int out;
    private List<Vertex> nexts;
    private List<Edge> edges;

    public Vertex(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
