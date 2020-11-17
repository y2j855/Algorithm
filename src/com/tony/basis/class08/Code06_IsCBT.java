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
     * 按层遍历，判断整棵树是否为完全二叉树
     * 逻辑思路，不符合完全二叉树的条件
     * 1.只有右节点，没有左节点
     * 2.如果遇到左右节点不双全时，后续的所有节点必须是叶子节点
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
             * right==null是因为前边已经判断过没有右节点的情况了
             */
            if (left != null && right == null) {
                leaf = true;
            }

            if(left != null){
                data.offer(left);
            }
            if(right != null){
                data.offer(right);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
//        root.left.left.left = new TreeNode(8);
//        root.left.left.right = new TreeNode(9);

        System.out.println(isCBTUseLevel(root));
    }
}
