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
    protected int height;
    protected Vertex from;
    protected Vertex to;

    public Edge(int height, Vertex from, Vertex to) {
        this.height = height;
        this.from = from;
        this.to = to;
    }

    public Edge(int from, int to) {
        this.from = new Vertex(from);
        this.to = new Vertex(to);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (!from.equals(edge.from)) return false;
        return to.equals(edge.to);
    }

    @Override
    public int hashCode() {
        int result = from.hashCode();
        result = 31 * result + to.hashCode();
        return result;
    }
}
