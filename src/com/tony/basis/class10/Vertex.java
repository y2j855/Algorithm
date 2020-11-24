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
    protected int value;
    protected int in;
    protected int out;
    protected List<Vertex> nexts;
    protected List<Edge> edges;

    public Vertex(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        return value == vertex.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
