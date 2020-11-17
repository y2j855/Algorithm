package com.tony.basis.class08;

import java.util.ArrayList;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/17 09:35
 * Description:
 * 给定一棵二叉树的头节点head，返回这颗二叉树中最大的二叉搜索子树的大小
 */
public class Code04_MaxSubBSTSize {

    /**
     * 通过递归套路，抽象出来的公共信息
     * 1.是否与X节点有关
     * 2.如果无关，max(左子树的大小,右子树的大小)
     * 3.如果有关，左子树的大小，左树是否是搜索二叉树，左树最大值，
     * 右子树的大小，右树是否是搜索二叉树，右树最小值
     * 左树最大值 < X < 右树最小值
     * 总体大小=左数大小+1+右树大小
     */
    private static class Info {
        private int maxSubBSTSize = 0;
        private boolean isBST = false;
        private int max = 0;
        private int min = 0;

        public Info(int maxSubBSTSize, boolean isBST, int max, int min) {
            this.maxSubBSTSize = maxSubBSTSize;
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public static int maxSubBSTSize(TreeNode head) {
        if (head == null) {
            return 0;
        }
        return process1(head).maxSubBSTSize;
    }

    /**
     * 节点为空时，设置信息本身不为空，属性为特殊值
     * @param head
     * @return
     */
    private static Info process(TreeNode head) {
        if (head == null) {
            return new Info(0, true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        int maxSubBSTSize = Math.max(leftInfo.maxSubBSTSize, rightInfo.maxSubBSTSize);

        int max = Math.max(head.value, Math.max(leftInfo.max, rightInfo.max));
        int min = Math.min(head.value, Math.min(leftInfo.min, rightInfo.min));

        boolean isBST = false;
        if (leftInfo.isBST && leftInfo.max < head.value &&
                rightInfo.isBST && rightInfo.min > head.value) {
            isBST = true;
            maxSubBSTSize = leftInfo.maxSubBSTSize + 1 + rightInfo.maxSubBSTSize;
        }

        return new Info(maxSubBSTSize, isBST, max, min);
    }

    /**
     * 节点为空时，设置整个信息对象为空，利用空来判断内容
     * @param head
     * @return
     */
    private static Info process1(TreeNode head) {
        if (head == null) {
            return null;
        }
        Info leftInfo = process1(head.left);
        Info rightInfo = process1(head.right);

        int maxSubBSTSize = 0;
        int max = head.value;
        int min = head.value;
        if (leftInfo != null) {
            maxSubBSTSize = Math.max(maxSubBSTSize, leftInfo.maxSubBSTSize);
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
        }
        if (rightInfo != null) {
            maxSubBSTSize = Math.max(maxSubBSTSize, rightInfo.maxSubBSTSize);
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
        }


        boolean isBST = false;

        boolean leftCheck = leftInfo == null ? true :
                (leftInfo.isBST && leftInfo.max < head.value);

        boolean rightCheck = rightInfo == null ? true :
                (rightInfo.isBST && rightInfo.min > head.value);

        if (leftCheck && rightCheck) {
            isBST = true;
            maxSubBSTSize = (leftInfo != null ? leftInfo.maxSubBSTSize : 0) + 1
                    + (rightInfo != null ? rightInfo.maxSubBSTSize : 0);
        }

        return new Info(maxSubBSTSize, isBST, max, min);
    }

    public static int getBSTSize(TreeNode head) {
        if (head == null) {
            return 0;
        }
        ArrayList<TreeNode> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(TreeNode head, ArrayList<TreeNode> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static int maxSubBSTSize1(TreeNode head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
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

        System.out.println(maxSubBSTSize(head2));

        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode root = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTSize1(root) != maxSubBSTSize(root)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");

    }
}
