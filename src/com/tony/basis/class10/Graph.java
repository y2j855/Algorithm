package com.tony.basis.class10;

import java.util.*;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/24 15:31
 * Description: 图对象
 */
public class Graph implements IGraph<Integer> {
    /**
     * nodes 所有顶点
     * edges 所有边
     */
    protected Map<Integer, Vertex> nodes;
    protected Set<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }

    @Override
    public int getNumOfVertex() {
        return nodes.size();
    }

    @Override
    public boolean insertVex(Integer v) {
        if (nodes.containsKey(v)) {
            return false;
        }
        nodes.put(v, new Vertex(v));
        return true;
    }

    @Override
    public boolean deleteVex(Integer v) {
        if (!nodes.containsKey(v)) {
            return false;
        }
        Vertex vertex = nodes.get(v);
        if (vertex.in != 0 || vertex.out != 0) {
            return false;
        }
        return nodes.remove(v) != null;
    }

    @Override
    public int indexOfVex(Integer v) {
        return 0;
    }

    @Override
    public Integer valueOfVex(int v) {
        if (!nodes.containsKey(v)) {
            return null;
        }
        return v;
    }

    @Override
    public boolean insertEdge(int v1, int v2, int weight) {
        if (nodes.containsKey(v1) && nodes.containsKey(v2)) {
            return false;
        }

        Vertex from = new Vertex(v1);
        Vertex to = new Vertex(v2);

        if (nodes.containsKey(v1) && !nodes.containsKey(v2)) {
            from = nodes.get(v1);
            from.out++;
            to.in++;
        } else if (!nodes.containsKey(v1) && nodes.containsKey(v2)) {
            from.out++;
            to = nodes.get(v2);
            to.in++;
        } else {
            from.out++;
            to.in++;
        }
        nodes.put(v1, from);
        nodes.put(v2, to);

        Edge newEdge = new Edge(weight, from, to);
        edges.add(newEdge);
        return true;
    }

    @Override
    public boolean deleteEdge(int v1, int v2) {
        if(!nodes.containsKey(v1)|| !nodes.containsKey(v2)) {
            return false;
        }
        Vertex from = nodes.get(v1);
        Vertex to = nodes.get(v2);
        Edge newEdge = new Edge(v1, v2);
        for (Edge edge : edges) {
            if (edge.equals(newEdge)) {
                edges.remove(edge);
                from.out--;
                to.in--;
                nodes.put(v1,from);
                nodes.put(v2,to);
                break;
            }
        }
        return true;
    }

    @Override
    public int getEdge(int v1, int v2) {
        int height = 0;
        Edge newEdge = new Edge(v1, v2);
        for (Edge edge : edges) {
            if (edge.equals(newEdge)) {
                height = edge.height;
                break;
            }
        }
        return height;
    }

    @Override
    public String depthFirstSearch(int v) {
        return null;
    }

    @Override
    public String breadFirstSearch(int v) {
        return null;
    }

    @Override
    public int[] dijkstra(int v) {
        return new int[0];
    }
}
