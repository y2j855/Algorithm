package com.tony.basis.class08;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/16 21:25
 * Description: 给定一棵二叉树的头节点head，返回这棵二叉树是不是平衡二叉树
 */
public class Code01_IsBalanced {

    private static class Info{
        boolean isBalance;
        int height;

        public Info(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }

    public static boolean isBalanced1(TreeNode head){
        return process1(head).isBalance;
    }

    private static Info process1(TreeNode head) {
        if(head == null){
            return new Info(true,0);
        }
        Info leftInfo = process1(head.left);
        Info rightInfo = process1(head.right);
        int height = Math.max(leftInfo.height,rightInfo.height) + 1;
        boolean isBalanced = false;
        if(leftInfo.isBalance && rightInfo.isBalance && Math.abs(leftInfo.height - rightInfo.height) < 2){
            isBalanced = true;
        }
        return new Info(isBalanced,height);
    }

    public static boolean isBalanced2(TreeNode head) {
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process2(head, ans);
        return ans[0];
    }

    public static int process2(TreeNode head, boolean[] ans) {
        if (!ans[0] || head == null) {
            return -1;
        }
        int leftHeight = process2(head.left, ans);
        int rightHeight = process2(head.right, ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
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
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            if (isBalanced1(head) != isBalanced2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
