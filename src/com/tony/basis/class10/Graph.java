package com.tony.basis.class10;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/24 15:31
 * Description: 图对象
 */
public class Graph {
    /**
     * nodes 所有顶点
     * edges 所有边
     */
    private Map<Integer,Vertex> nodes;
    private Set<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
