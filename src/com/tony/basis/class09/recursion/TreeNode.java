package com.tony.basis.class09.recursion;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/23 12:03
 * Description: 二叉树反转，利用递归实现
 */
public class TreeNode {
    private int value;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }

    /**
     * 递归翻转二叉树
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root){
        if(root ==null){
            return null;
        }
        if(root.left == null && root.right==null){
            return root;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        root.invertTree(root);
    }
}
