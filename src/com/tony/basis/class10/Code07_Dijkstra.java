package com.tony.basis.class10;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/25 21:35
 * Description:
 */
public class Code07_Dijkstra {

    /**
     * 1.建立map保存最小生成树
     * 2.通过源点找到源点到其他出度点的距离
     * 3.拿到下一个点，继续计算距离，如果比之前计算的距离短更新，否则不更改。
     * 4.标记源点为不可更改的点
     * 5.再将此点标记为不可更改的点，当所有点都被计算过后并都被标记为不可更改，停止计算，返回map
     * <p>
     * 如果是有向图，要保证是连通图。
     *
     * @param from
     * @return
     */
    public static Map<Vertex, Integer> dijkstra(Vertex from) {
        Map<Vertex, Integer> result = new HashMap<>();
        Set<Vertex> selectedVertex = new HashSet<>();
        result.put(from, 0);
        Vertex minVertex = from;

        //循环遍历找出最小权值出度点，如果结构set中不存在添加，如果存在更新为最小距离
        while (minVertex != null) {
            int distance = result.get(minVertex);
            //当前点有关边的所有的点，只要没被选中就会被循环到
            for (Edge edge : minVertex.edges) {
                Vertex to = edge.to;
                if (!result.containsKey(to)) {
                    result.put(to, distance + edge.height);
                } else {
                    result.put(to, Math.min(result.get(to), distance + edge.height));
                }
            }
            selectedVertex.add(minVertex);
            minVertex = getUnSelectedVertex(result, selectedVertex);
        }
        return result;
    }

    /**
     * 从全集里找到没有被选中的点，判断点对应的权值是不是最小的
     * 返回最小权重值对应的终点
     * minDistance:没有选中过的点最小的权重值，选中过的已经在之前更新为最小的权值了。
     * 可能会出现调点的情况，比如c,d，e三个点还没有被选中，但是d是最小的距离，所以跳过c直接返回d
     * 但c点在外层方法还是会遍历到，也就是说c点还是会被选中。
     *
     * @param result
     * @param selectedVertex
     * @return
     */
    private static Vertex getUnSelectedVertex(Map<Vertex, Integer> result, Set<Vertex> selectedVertex) {
        Vertex minVertex = null;
        Integer minDistance = Integer.MAX_VALUE;
        for (Entry<Vertex, Integer> entry : result.entrySet()) {
            Vertex key = entry.getKey();
            Integer distance = entry.getValue();
            if (!selectedVertex.contains(key) && distance < minDistance) {
                minVertex = key;
                minDistance = distance;
            }
        }
        return minVertex;
    }

    private static class VertexRecord {
        private Vertex vertex;
        private int distance;

        public VertexRecord(Vertex vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    /**
     * 自定义小根堆，功能
     * 1.添加新的VertexRecord
     * 2.更新VertexRecord信息，并重新排序
     * 3.用-1代表已经弹出堆的VertexRecord，并忽略它。
     */
    public static class VertexHeap {
        private Vertex[] vertices;//实际的堆结构
        //value 数组中的下标位置
        private Map<Vertex, Integer> heapIndexMap;
        //value 从源顶点出发到该顶点目前最小距离
        private Map<Vertex, Integer> distanceMap;
        private int size;

        public VertexHeap(int size) {
            vertices = new Vertex[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            this.size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        /**
         * 弹出堆元素
         *
         * @return 顶点记录对象(顶点对象, 距离)
         */
        public VertexRecord poll() {
            VertexRecord record = new VertexRecord(vertices[0], distanceMap.get(vertices[0]));
            swap(0, size - 1);
            //设置忽略标识
            heapIndexMap.put(vertices[size - 1], -1);
            distanceMap.remove(vertices[size - 1]);
            vertices[size - 1] = null;
            heapify(0, --size);
            return record;
        }

        /**
         * 添加，更新小顶堆，如果顶点出过堆忽略对它的操作
         * @param vertex
         * @param distance
         */
        public void addOrUpdateOrIgnore(Vertex vertex, int distance) {
            //判断顶点是否已出过堆，-1代表出过堆
            if (inHeap(vertex)) {
                distanceMap.put(vertex, Math.min(distanceMap.get(vertex), distance));
                heapInsert(heapIndexMap.get(vertex));
            }
            //新增顶点到堆
            if (!heapIndexMap.containsKey(vertex)) {
                vertices[size] = vertex;
                heapIndexMap.put(vertex, size);
                distanceMap.put(vertex, distance);
                heapInsert(size++);
            }
        }


        /**
         * 判断当前顶点是否在堆里，并且保证不是已弹出过
         *
         * @param vertex
         * @return
         */
        private boolean inHeap(Vertex vertex) {
            return heapIndexMap.containsKey(vertex) && heapIndexMap.get(vertex) != -1;
        }

        /**
         * 小根堆从顶自下堆化
         *
         * @param index
         * @param size
         */
        private void heapify(int index, int size) {
            int left = index * 2 + 1;
            while (left < size) {
                int smallest = left + 1 < size && distanceMap.get(vertices[left + 1]) < distanceMap.get(vertices[left])
                        ? left + 1
                        : left;
                smallest = distanceMap.get(vertices[smallest]) < distanceMap.get(vertices[index]) ? smallest : index;
                if (smallest == index) {
                    break;
                }
                swap(smallest, index);
                index = smallest;
                left = index * 2 + 1;
            }
        }

        /**
         * 小根堆从下自上堆化
         * @param index
         */
        public void heapInsert(int index) {
            while (distanceMap.get(vertices[index]) < distanceMap.get(vertices[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        /**
         * 小根堆顶点交换位置
         *
         * @param i
         * @param j
         */
        private void swap(int i, int j) {
            heapIndexMap.put(vertices[i], j);
            heapIndexMap.put(vertices[j], i);

            Vertex temp = vertices[i];
            vertices[i] = vertices[j];
            vertices[j] = temp;

        }
    }

    public static Map<Vertex, Integer> dijkstraUseHeap(Vertex from, int size) {
        VertexHeap heap = new VertexHeap(size);
        heap.addOrUpdateOrIgnore(from, 0);
        Map<Vertex, Integer> result = new HashMap<>();
        while (!heap.isEmpty()) {
            VertexRecord record = heap.poll();
            Vertex current = record.vertex;
            int distance = record.distance;
            for (Edge edge : current.edges) {
                heap.addOrUpdateOrIgnore(edge.to, edge.height + distance);
            }
            result.put(current, distance);
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 1, 2}, {7, 1, 3}, {1, 1, 4}, {2, 2, 3}, {170, 2, 5}, {2, 3, 4}, {23, 3, 5}, {4, 4, 5}};
        Graph graph = GraphGenerator.generatorGraph(matrix);
        Vertex from = graph.nodes.get(1);
        Map<Vertex, Integer> map = dijkstra(from);
        for (Entry<Vertex, Integer> entry : map.entrySet()) {
            System.out.println("从"+from.value+"点到" + entry.getKey().value +"点");
            System.out.println("距离=" + entry.getValue());
        }
        System.out.println();
        Map<Vertex, Integer> heapMap = dijkstraUseHeap(from, graph.nodes.size());
        for (Entry<Vertex, Integer> entry : heapMap.entrySet()) {
            System.out.println("从"+from.value+"点到" + entry.getKey().value +"点");
            System.out.println("距离=" + entry.getValue());
        }
    }
}
