package com.tony.basis.class08;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/17 21:23
 * Description:
 * 给定一棵二叉树的头节点head，返回这棵二叉树中是不是完全二叉树
 */
public class Code06_IsCBT {

    /**
     * 非递归实现按层遍历，判断整棵树是否为完全二叉树
     * 逻辑思路，不符合完全二叉树的条件
     * 1.只有右节点，没有左节点
     * 2.如果遇到左右节点不双全时，后续的所有节点必须是叶子节点
     *
     * @param head
     * @return
     */
    public static boolean isCBTUseLevel(TreeNode head) {
        if (head == null) {
            return true;
        }
        Queue<TreeNode> data = new LinkedList<>();
        data.offer(head);
        boolean leaf = false;
        while (!data.isEmpty()) {
            TreeNode current = data.poll();
            TreeNode left = current.left;
            TreeNode right = current.right;
            //只有右节点没有左节点
            if (left == null && right != null) {
                return false;
            }
            //当前节点不是叶子节点，可以与前边合并成一条判断
            if (leaf && (left != null || right != null)) {
                return false;
            }
            /**
             * 如果不是双全节点
             * 设置下一个节点为叶子节点，所以要写在最后
             * 如果写在判断前边会出问题
             */
            if (left == null || right == null) {
                leaf = true;
            }
            if (left != null) {
                data.offer(left);
            }
            if (right != null) {
                data.offer(right);
            }
        }
        return true;
    }

    private static class Info {
        private boolean isCBTTree = false;
        private boolean isFullTree = false;
        private int height;
        private int size;

        public Info(boolean isCBTTree, boolean isFullTree, int height, int size) {
            this.isCBTTree = isCBTTree;
            this.isFullTree = isFullTree;
            this.height = height;
            this.size = size;
        }
    }

    public static boolean isCBT(TreeNode head) {
        if (head == null) {
            return true;
        }
        return proces(head).isCBTTree;
    }

    /**
     * 递归判断是否为完全二叉树
     *
     * @param head
     * @return
     */
    private static Info proces(TreeNode head) {
        if (head == null) {
            return new Info(true, true, 0, 0);
        }
        Info left = proces(head.left);
        Info right = proces(head.right);

        int size = Math.max(left.size, right.size);
        int height = Math.max(left.height, right.height);

        boolean isFull = left.isFullTree && right.isFullTree && left.size == right.size ? true : false;
        boolean isCBT = false;

        /**
         * 判断是否为完全二叉树四种情况
         * 1.左右都满，左右高度相等
         * 2.左完全二叉树，右满二叉树，左高-右高=1
         * 3.左右都满，左高-右高=1
         * 4.左满二叉树，右完全二叉树，左右高度相等
         */
        if ((left.isFullTree && right.isFullTree && left.height == right.height)
                || (left.isCBTTree && right.isFullTree && left.height - right.height == 1)
                || (left.isFullTree && right.isFullTree && left.height - right.height == 1)
                || (left.isFullTree && right.isCBTTree && left.height == right.height)) {
            isCBT = true;
            height = height + 1;
            size = left.size + 1 + right.size;
        }
        return new Info(isCBT, isFull, height, size);
    }

    // for test
    public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static TreeNode generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode head = new TreeNode((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
//        root.right.left = new TreeNode(6);
        root.left.left.left = new TreeNode(8);
        root.left.left.right = new TreeNode(9);


        TreeNode test1 = new TreeNode(19);
        test1.left = new TreeNode(77);
        test1.left.left = new TreeNode(40);
        test1.left.right = new TreeNode(70);
        test1.left.left.left = new TreeNode(48);
        test1.left.left.right = new TreeNode(31);
        test1.left.right.right = new TreeNode(16);

        TreeNode test2 = new TreeNode(15);
        test2.left = new TreeNode(2);
        test2.left.left = new TreeNode(7);
        test2.left.right = new TreeNode(19);
        test2.left.left.right = new TreeNode(37);
        test2.left.left.right.right = new TreeNode(15);
        test2.left.right.right = new TreeNode(4);

        System.out.println(isCBTUseLevel(test1));
        System.out.println(isCBT(test2));
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            if (isCBTUseLevel(head) != isCBT(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
