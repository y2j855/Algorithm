package com.tony.basis.class10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/21 21:27
 * Description: 并查集
 */
public class Code01_UnionFind {

    /**
     * 并查集对象
     *
     * @param <V>
     */
    public static class UnionSet<V> {
        private class Node<V> {
            private V value;

            public Node(V value) {
                this.value = value;
            }
        }

        //存放所有节点的容器
        private Map<V, Node<V>> nodes = new HashMap<>();
        //存放节点与代表点关系的容器
        private Map<Node<V>, Node<V>> parents = new HashMap<>();
        //存放代表点大小的容器
        private Map<Node<V>, Integer> sizeMap = new HashMap<>();

        public UnionSet(List<V> list) {
            for (V value : list) {
                Node<V> current = (Node<V>) new Node<>(value);
                nodes.put(value, current);
                parents.put(current, current);
                sizeMap.put(current, 1);
            }
        }

        /**
         * TODO
         * 1.创建子节点
         * 2.是否用单链表更好？如何添加内容
         * 3.写对数器，保证并查集功能正确
         */
        public void addChild(V value) {
            Node<V> node = new Node<>(value);
            nodes.put(value, node);
            parents.put(node, node);
            sizeMap.put(node, 1);
        }

        /**
         * 查看x，y是否属于一个集合
         *
         * @param x
         * @param y
         * @return
         */
        public boolean isSameSet(V x, V y) {
            if (!nodes.containsKey(x) || !nodes.containsKey(y)) {
                return false;
            }
            return findParent(nodes.get(x)) == findParent(nodes.get(y));
        }

        /**
         * 1.找到父节点
         * 2.将链表变成扁平化
         *
         * @param node
         * @return
         */
        private Node<V> findParent(Node<V> node) {
            List<Node> list = new ArrayList<>();
            while (node != parents.get(node)) {
                list.add(node);
                node = parents.get(node);
            }
            for (int i = 0; i < list.size(); i++) {
                parents.put(list.get(i), node);
            }
            return node;
        }

        /**
         * 合并x，y相关的所有集合
         *
         * @param x
         * @param y
         */
        public void union(V x, V y) {
            if (!nodes.containsKey(x) || !nodes.containsKey(y)) {
                return;
            }
            Node<V> xHead = findParent(nodes.get(x));
            Node<V> yHead = findParent(nodes.get(y));

            if (xHead != yHead) {
                int xSize = sizeMap.get(xHead);
                int ySize = sizeMap.get(yHead);
                if (xSize >= ySize) {
                    parents.put(yHead, xHead);
                    sizeMap.put(xHead, xSize + ySize);
                    sizeMap.remove(yHead);
                } else {
                    parents.put(xHead, yHead);
                    sizeMap.put(yHead, xSize + ySize);
                    sizeMap.remove(xHead);
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        UnionSet<Integer> set = new UnionSet<>(list);
    }

}
