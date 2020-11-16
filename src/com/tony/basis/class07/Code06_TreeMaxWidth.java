package com.tony.basis.class07;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/16 17:35
 * Description: 求二叉树最宽的层有多少个节点
 */
public class Code06_TreeMaxWidth {

    /**
     * 按层遍历记录最大宽度
     * 逻辑思路
     * 1.通过map将每个节点对应的层数记录
     * 2.通过三个变量记录，当前层，当前层节点数，最大节点数
     * 3.每次判断当前节点是否是当前层的
     * 是：当前层节点数加1
     * 不是：更新最大值，将当前层数加1，当前层节点数变为1
     * 4.因为下一层更新上一层最大值，所以跳出循环后还要更新一次最大值
     *
     * @param head
     * @return
     */
    public static int getMaxWidthUseMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        /**
         * 当前层
         * 当前层节点数
         * 最大节点数
         */
        int curLevel = 1;
        int curLevelNodes = 0;
        int max = 0;
        Map<Node, Integer> map = new HashMap<>();
        map.put(head, curLevel);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            Integer level = map.get(current);
            if (current.left != null) {
                queue.offer(current.left);
                map.put(current.left, curLevel + 1);
            }
            if (current.right != null) {
                queue.offer(current.right);
                map.put(current.right, curLevel + 1);
            }
            if (level == curLevel) {
                curLevelNodes++;
            } else {
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }

    /**
     * 不用map实现获取二叉树最大宽度
     * 逻辑思路
     * 1.定义三个变量，当前层最右节点，下一层最右节点，当前层最大节点个数
     * 2.当前层最右节点设为头节点，按层遍历，当左右节点不为空，将下一层最右节点设置
     * 3.下一层左节点不为空时，设置下一层最右节点为左节点的原因是有可能没有右节点。
     * 4.每一层走完更新最大值。
     * @param head
     * @return
     */
    public static int getMaxWidth(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        int max = 0;
        Node curEnd = head;
        Node nextEnd = null;
        int curLevelNodes = 0;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.left != null) {
                //考虑不存在右节点
                nextEnd = current.left;
                queue.offer(current.left);
            }
            if (current.right != null) {
                nextEnd = current.right;
                queue.offer(current.right);
            }
            if (curEnd == current) {
                max = Math.max(max, curLevelNodes);
                curLevelNodes=0;
                curEnd = nextEnd;
            }
            curLevelNodes++;

        }
        return max;
    }

    public static void main(String[] args) {
        // 根据给定的数组创建一棵树
        Integer[] data = {1, 2, 3, 4, 5, 6, 7, 11, 12, 13, 14, 15, 16, 17, 18};
//        Integer[] data = {1,2,null,3,4,null,null};
        Node head = Code04_SerializeAndReconstructTree.leveUnSerialUseArray(data);
        System.out.println(getMaxWidth(head));
    }

}
