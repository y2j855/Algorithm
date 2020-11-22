package com.tony.basis.class10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/22 11:46
 * Description:
 */

/**
 * 并查集对象
 *
 * @param <V>
 */
public class UnionSet<V> {
    protected static class Node<V> {
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
     * 1.创建子节点  完成
     * 2.是否用单链表更好？如何添加内容 完成
     * 3.写对数器，保证并查集功能正确
     */
    public void addChild(List<Node> childList, V value) {
        Node<V> parentNode = nodes.get(value);
        for (int i = 0; i < childList.size(); i++) {
            Node node = childList.get(i);
            nodes.put((V) node.value, node);
            parents.put(node, parentNode);
        }
        sizeMap.put(parentNode, 1 + childList.size());
    }

    public int getHeadSize(){
        return sizeMap.size();
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

    public Node getNode(V value) {
        return nodes.get(value);
    }
}
