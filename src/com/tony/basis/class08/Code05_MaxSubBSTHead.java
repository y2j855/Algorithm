package com.tony.basis.class08;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/17 13:09
 * Description:
 * 给定一棵二叉树的头节点head，返回这颗二叉树中最大的二叉搜索子树的头节点
 */
public class Code05_MaxSubBSTHead {
    private static class Info {
        private TreeNode subBSTHead;
        private int maxSubBSTSize;
        private int max;
        private int min;

        public Info(TreeNode subBSTHead, int maxSubBSTSize, int max, int min) {
            this.subBSTHead = subBSTHead;
            this.maxSubBSTSize = maxSubBSTSize;
            this.max = max;
            this.min = min;
        }
    }

    /**
     * 获取二叉树最大的二叉搜索子树的头节点
     * @param head
     * @return
     */
    public static TreeNode maxSubBSTHead(TreeNode head) {
        if (head == null) {
            return null;
        }
        return process1(head).subBSTHead;
    }

    /**
     * 信息对象不为空
     * @param head
     * @return
     */
    private static Info process(TreeNode head) {
        if (head == null) {
            return new Info(null, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        Info left = process(head.left);
        Info right = process(head.right);

        TreeNode subBSTHead = (left.maxSubBSTSize >= right.maxSubBSTSize ? left.subBSTHead : right.subBSTHead);
        int maxSubBSTSize = (subBSTHead == head.left) ? left.maxSubBSTSize : right.maxSubBSTSize;
        int max = Math.max(head.value, Math.max(left.max, right.max));
        int min = Math.min(head.value, Math.min(left.min, right.min));

        if (left.subBSTHead == head.left && left.max < head.value
                && right.subBSTHead == head.right && right.min > head.value) {
            subBSTHead = head;
            maxSubBSTSize = left.maxSubBSTSize + 1 + right.maxSubBSTSize;
        }
        return new Info(subBSTHead, maxSubBSTSize, max, min);
    }

    /**
     * 信息对象为空
     * @param head
     * @return
     */
    private static Info process1(TreeNode head) {
        if (head == null) {
            return null;
        }
        Info left = process1(head.left);
        Info right = process1(head.right);

        TreeNode subBSTHead = null;
        int maxSubBSTSize = 0;
        int max = head.value;
        int min = head.value;
        if (left != null) {
            subBSTHead = left.subBSTHead;
            maxSubBSTSize = left.maxSubBSTSize;
            min = Math.min(min,left.min);
            max = Math.max(max, left.max);
        }
        if (right != null) {
            subBSTHead = right.subBSTHead;
            maxSubBSTSize = right.maxSubBSTSize;
            min = Math.min(min, right.min);
            max = Math.max(max,right.max);
        }

        boolean leftCheck = left == null ? true :
                (left.subBSTHead == head.left && left.max < head.value);

        boolean rightCheck = right == null ? true :
                (right.subBSTHead == head.right && right.min > head.value);

        if (leftCheck && rightCheck) {
            subBSTHead = head;
            maxSubBSTSize = (left != null ? left.maxSubBSTSize : 0) + 1 +
                    (right != null ? right.maxSubBSTSize : 0);
        }
        return new Info(subBSTHead, maxSubBSTSize, max, min);
    }

    public static void main(String[] args) {
        TreeNode head2 = new TreeNode(11);
        head2.left = new TreeNode(63);
        head2.left.left = new TreeNode(49);
        head2.left.right = new TreeNode(69);
        head2.left.right.left = new TreeNode(64);

        System.out.println(maxSubBSTHead(head2).value);
    }
}
