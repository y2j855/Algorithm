package com.tony.basis.class08;

import java.util.*;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/18 11:38
 * Description:
 * 给定一棵二叉树的头节点head，和另外两个节点a和b。返回a和b的最低公共祖先
 */
public class Code07_LowestAncestor {

    /**
     * 返回给定2个节点的最低公共祖先
     * 1.将二叉树的所有子父关系保存到map中
     * 2.将节点1的所有父节点存到set中
     * 3.节点2与set比较，相等的就是结果
     *
     * @param head 头节点
     * @param o1   节点1
     * @param o2   节点2
     * @return null:不存在
     */
    public static TreeNode lowestAncestorUseMap(TreeNode head, TreeNode o1, TreeNode o2) {
        if (head == null || o1 == null || o2 == null) {
            return null;
        }
        TreeNode current = o1;
        Map<TreeNode, TreeNode> data = new HashMap<>();
        data.put(head, null);
        fillMap(head, data);

        //o1,o2不在二叉树里直接返回null
        if(data.get(o1) == null || data.get(o2) ==null){
            return null;
        }

        Set<TreeNode> parentData = new HashSet<>();
        parentData.add(o1);
        //将o1的所有父节点放到set里去
        while (data.get(current) != null) {
            parentData.add(data.get(current));
            current = data.get(current);
        }
        //o2与o1的父节点比较，如果找到就是最低公共祖先
        current = o2;
        while (!parentData.contains(current)) {
            current = data.get(current);
        }
        return current;
    }

    private static void fillMap(TreeNode head, Map data) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current.left != null) {
                queue.offer(current.left);
                data.put(current.left, current);
            }
            if (current.right != null) {
                queue.offer(current.right);
                data.put(current.right, current);
            }
        }
    }

    /**
     * 递归公共信息
     */
    private static class Info {
        private boolean isHasO1;
        private boolean isHasO2;
        private TreeNode ancestor;

        public Info(boolean isHasO1, boolean isHasO2, TreeNode ancestor) {
            this.isHasO1 = isHasO1;
            this.isHasO2 = isHasO2;
            this.ancestor = ancestor;
        }
    }

    public static TreeNode lowestAncestor(TreeNode head, TreeNode o1, TreeNode o2) {
        if (head == null || o1 == null || o2 == null) {
            return null;
        }
        return process(head, o1, o2).ancestor;
    }

    private static Info process(TreeNode head, TreeNode o1, TreeNode o2) {
        if (head == null) {
            return new Info(false, false, null);
        }

        Info left = process(head.left, o1, o2);
        Info right = process(head.right, o1, o2);

        boolean isHasO1 = head == o1 || left.isHasO1 || right.isHasO1;
        boolean isHasO2 = head == o2 || left.isHasO2 || right.isHasO2;

        TreeNode ancestor = null;

        /**
         *条件1，2不会进判断
         *1.o1,o2不在X树上
         *2.o1,o2只有一个在X树上
         *3.o1,o2都在X树上
         *  左树右树各一个
         *  都在左树
         *  都在右树
         *  x是o1或者o2
         */
//        if (left.isHasO1 && left.isHasO2) {
//            ancestor = left.ancestor;
//        }
//        if (right.isHasO1 && right.isHasO2) {
//            ancestor = right.ancestor;
//        }
//        if ((left.isHasO1 && right.isHasO2) || (left.isHasO2 && right.isHasO1)) {
//            ancestor = head;
//        }
//        if (head == o1 || head == o2) {
//            ancestor = head;
//        }

        /**
         * 这种更加好懂
         * 通过最低公共祖宗来判断
         * 1.如果左树有，最低公共祖宗等于左树的
         * 2.否则如果右树有就拿右边的
         * 3.如果左右都没有，并且有o1，o2那就证明是当前这个节点
         */
        if (left.ancestor != null) {
            ancestor = left.ancestor;
        } else if (right.ancestor != null) {
            ancestor = right.ancestor;
        }
        if (ancestor == null) {
            if (isHasO1 && isHasO2) {
                ancestor = head;
            }
        }

        return new Info(isHasO1, isHasO2, ancestor);
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

    // for test
    public static TreeNode pickRandomOne(TreeNode head) {
        if (head == null) {
            return null;
        }
        ArrayList<TreeNode> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    // for test
    public static void fillPrelist(TreeNode head, ArrayList<TreeNode> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(7);
        head.left = node1;
        head.right = node2;
        head.left.left = node3;
        head.left.right = node4;
        head.right.left = node5;
        head.right.right = node6;

        System.out.println(lowestAncestorUseMap(head, new TreeNode(99), new TreeNode(100)));
        System.out.println(lowestAncestor(head, new TreeNode(99), node4));

        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode root = generateRandomBST(maxLevel, maxValue);
            TreeNode o1 = pickRandomOne(root);
            TreeNode o2 = pickRandomOne(root);
            if (lowestAncestorUseMap(head, o1, o2) != lowestAncestor(head, o1, o2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
