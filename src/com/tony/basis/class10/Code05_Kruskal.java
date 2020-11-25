package com.tony.basis.class10;

import java.util.*;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/24 21:51
 * Description: 图最小生成树kruskal算法
 * 逻辑思路
 * 1.小根堆将边从小到大排序
 * 2.用并查集合并，保证连通性，每次判断2个边是否已经是一个集合了，
 * 如果是丢弃，否则合并，并且保证不会形成环
 */
public class Code05_Kruskal {

    // Union-Find Set
    public static class UnionFind {
        // key 某一个节点， value key节点往上的节点
        private HashMap<Vertex, Vertex> fatherMap;
        // key 某一个集合的代表节点, value key所在集合的节点个数
        private HashMap<Vertex, Integer> sizeMap;

        public UnionFind() {
            fatherMap = new HashMap<Vertex, Vertex>();
            sizeMap = new HashMap<Vertex, Integer>();
        }

        public void makeSets(Collection<Vertex> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (Vertex node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        private Vertex findFather(Vertex v) {
            Stack<Vertex> path = new Stack<>();
            while (v != fatherMap.get(v)) {
                path.add(v);
                v = fatherMap.get(v);
            }
            while (!path.isEmpty()) {
                fatherMap.put(path.pop(), v);
            }
            return v;
        }

        public boolean isSameSet(Vertex a, Vertex b) {
            return findFather(a) == findFather(b);
        }

        public void union(Vertex a, Vertex b) {
            if (a == null || b == null) {
                return;
            }
            Vertex aDai = findFather(a);
            Vertex bDai = findFather(b);
            if (aDai != bDai) {
                int aSetSize = sizeMap.get(aDai);
                int bSetSize = sizeMap.get(bDai);
                if (aSetSize <= bSetSize) {
                    fatherMap.put(aDai, bDai);
                    sizeMap.put(bDai, aSetSize + bSetSize);
                    sizeMap.remove(aDai);
                } else {
                    fatherMap.put(bDai, aDai);
                    sizeMap.put(aDai, aSetSize + bSetSize);
                    sizeMap.remove(bDai);
                }
            }
        }
    }

    /**
     * krusal最小生成树，返回边对象
     * 它的最小维度是以边开始,经常用它来算权重值
     * @param graph
     * @return
     */
    public static Set<Edge> kruskalMST(Graph graph) {
        UnionFind find = new UnionFind();
        find.makeSets(graph.nodes.values());
        PriorityQueue<Edge> queue = new PriorityQueue(new Comparator<Edge>() {
            @Override
            public int compare(Edge e1, Edge e2) {
                return e1.height - e2.height;
            }
        });
        for (Edge edge : graph.edges) {
            queue.add(edge);
        }

        Set<Edge> result = new HashSet<>();
        while (!queue.isEmpty()) {
            Edge current = queue.poll();
            if (!find.isSameSet(current.from, current.to)) {
                find.union(current.from, current.to);
                result.add(current);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {{100, 1, 2}, {50, 2, 3}, {3, 3, 4}, {1, 2, 4}, {2, 1, 4}};
        Graph graph = GraphGenerator.generatorGraph(matrix);
        Set<Edge> edges = kruskalMST(graph);
        Iterator<Edge> iterator = edges.iterator();
        while (iterator.hasNext()){
            Edge next = iterator.next();
            System.out.print(next.height + " vertex from=" + next.from.value + " to=" + next.to.value);
            System.out.println();
        }
    }
}
