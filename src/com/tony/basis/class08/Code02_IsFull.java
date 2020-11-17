package com.tony.basis.class08;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/17 19:38
 * Description:
 * 给定一棵二叉树的头节点head，返回这颗二叉树是不是满二叉树
 */
public class Code02_IsFull {
    private static class Info {
        private int size;
        private boolean isFull;
        private int height;
        private int nodes;

        /**
         * 通过大小，是否为满二叉树来实现
         *
         * @param size
         * @param isFull
         */
        public Info(int size, boolean isFull) {
            this.size = size;
            this.isFull = isFull;
        }

        /**
         * 通过节点个数与高度来来实现
         *
         * @param nodes
         * @param height
         */
        public Info(int nodes, int height) {
            this.nodes = nodes;
            this.height = height;
        }
    }

    public static boolean isFullTree(TreeNode head) {
        if (head == null) {
            return true;
        }
        return process(head).isFull;
    }

    public static boolean isFullTree1(TreeNode head){
        if(head == null){
            return true;
        }
        Info all = process1(head);
        return (1<< all.height) -1 == all.nodes;
    }

    /**
     * 左右子树是否为满二叉树
     * 通过大小判断左右子树是否相等
     *
     * @param head
     * @return
     */
    private static Info process(TreeNode head) {
        if (head == null) {
            return new Info(0, true);
        }
        Info left = process(head.left);
        Info right = process(head.right);
        int size = Math.max(left.size, right.size);
        boolean isFull = false;
        if (left.isFull && right.isFull && left.size == right.size) {
            isFull = true;
            size = left.size + 1;
        }
        return new Info(size, isFull);
    }

    /**
     * 通过左右子树的高度和节点个数实现
     * @param head
     * @return
     */
    private static Info process1(TreeNode head) {
        if (head == null) {
            return new Info(0, 0);
        }
        Info left = process1(head.left);
        Info right = process1(head.right);

        int nodes = left.nodes + right.nodes + 1;
        int height = Math.max(left.height, right.height) + 1;
        return new Info(nodes, height);
    }

    //for test
    public static boolean isFull1(TreeNode head) {
        if (head == null) {
            return true;
        }
        int height = h(head);
        int nodes = n(head);
        return (1 << height) - 1 == nodes;
    }

    public static int h(TreeNode head) {
        if (head == null) {
            return 0;
        }
        return Math.max(h(head.left), h(head.right)) + 1;
    }

    public static int n(TreeNode head) {
        if (head == null) {
            return 0;
        }
        return n(head.left) + n(head.right) + 1;
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
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(6);
        head.right.right = new TreeNode(7);

        System.out.println(isFullTree(head));
        System.out.println(isFullTree1(head));

        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode root = generateRandomBST(maxLevel, maxValue);
            if (isFull1(root) != isFullTree(root)) {
                System.out.println("Oops!");
            }
            if(isFull1(root) != isFullTree1(root)){
                System.out.println("Oops1!");
            }
        }
        System.out.println("finish!");
    }
}
