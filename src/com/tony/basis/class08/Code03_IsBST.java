package com.tony.basis.class08;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/17 21:09
 * Description:
 * 给定一棵二叉树的头节点head，返回这颗二叉树是不是搜索二叉树
 * 逻辑与4，5两题一样
 */
public class Code03_IsBST {
    private static class ReturnResult {
        private boolean isBST;
        private int bstSize;
        private int max;
        private int min;

        public ReturnResult(boolean isBST, int bstSize, int max, int min) {
            this.isBST = isBST;
            this.bstSize = bstSize;
            this.max = max;
            this.min = min;
        }
    }

    public static boolean isBST(TreeNode head) {
        if (head == null) {
            return true;
        }
        return process(head).isBST;
    }

    private static ReturnResult process(TreeNode head) {
        if (head == null) {
            return new ReturnResult(true, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        ReturnResult left = process(head.left);
        ReturnResult right = process(head.right);

        int bstSize = Math.max(left.bstSize, right.bstSize);
        int max = Math.max(head.value, Math.max(left.max, right.max));
        int min = Math.min(head.value, Math.min(left.min, left.min));

        boolean isBST = false;
        if (left.isBST && left.max < head.value
                && right.isBST && right.min > head.value) {
            isBST = true;
            bstSize = left.bstSize + right.bstSize + 1;
        }
        return new ReturnResult(isBST, bstSize, max, min);
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(0);
        head.left = new TreeNode(3);
        head.right = new TreeNode(4);
        head.right.right = new TreeNode(7);
        head.left.left = new TreeNode(2);
        head.left.right = new TreeNode(5);
        head.left.right.left = new TreeNode(6);

        TreeNode head2 = new TreeNode(11);
        head2.left = new TreeNode(63);
        head2.left.left = new TreeNode(49);
        head2.left.right = new TreeNode(69);
        head2.left.right.left = new TreeNode(64);

        System.out.println(isBST(head2));
    }
}
