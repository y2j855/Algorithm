package com.tony.basis.class07;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/16 19:25
 * Description: 中序遍历获取后继节点
 * 后继节点：一棵二叉树在中序遍历中这个节点后边的节点为后继节点
 * 例子：[4,2,5,1,6,3,7] 4的后继节点2...
 */
public class Code07_SuccessorNode {

    private static class TreeNode {
        private int value;
        private TreeNode left;
        private TreeNode right;
        private TreeNode parent;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    /**
     * 获取二叉树中序遍历某个节点的后继节点
     * @param node
     * @return
     */
    public static TreeNode getSuccessorNode(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeNode successorNode = null;
        if (node.right != null) {
            return getLeftMost(node.right);
        } else {
            /**
             * 如果x没有右树，x要往上找。
             * 如果它是它父节点的左节点，那它的父就是它的后继节点，
             * 如果不是继续往上找，直到找到符合这个条件的，那父节点就是它的后继节点
             * 反过来说，如果x是左树最后一个节点，那么这棵左树的父节点就是x的后继节点
             */
            TreeNode current = node.parent;
            while (current != null) {
                if (current.left == node) {
                    successorNode = current;
                    break;
                } else {
                    node = current;
                }
                current = current.parent;
            }
        }
        return successorNode;
    }

    /**
     * 节点右树的最左节点，后继节点
     * 原理：如果x有右树，它的后继节点一定在它右树的最左节点。
     * 因为中序遍历是左头右，把x假设成头，那它的右树也是左右头遍历的，
     * 所以x的后继节点肯定是右树的最左节点。
     * @param right
     * @return
     */
    private static TreeNode getLeftMost(TreeNode right) {
        TreeNode successorNode = null;
        while (right != null) {
            if (right.left == null) {
                successorNode = right;
            }
            right = right.left;
        }
        return successorNode;
    }

    /**
     * 中序遍历获取某个节点的前驱节点
     * @param node
     * @return
     */
    public static TreeNode getPrecursorNode(TreeNode node){
        if (node == null) {
            return null;
        }
        TreeNode precursorNode = null;
        if (node.left != null) {
            return getRightMost(node.left);
        } else {
            TreeNode current = node.parent;
            while (current != null) {
                if (current.right == node) {
                    precursorNode = current;
                    break;
                } else {
                    node = current;
                }
                current = current.parent;
            }
        }
        return precursorNode;
    }

    /**
     * 节点左树的最右节点，前驱节点
     * 后继逻辑的反向
     * @param left
     * @return
     */
    private static TreeNode getRightMost(TreeNode left) {
        TreeNode precursorNode = null;
        while (left != null) {
            if (left.right == null) {
                precursorNode = left;
            }
            left = left.right;
        }
        return precursorNode;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(6);
        head.parent = null;
        head.left = new TreeNode(3);
        head.left.parent = head;
        head.left.left = new TreeNode(1);
        head.left.left.parent = head.left;
        head.left.left.right = new TreeNode(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new TreeNode(4);
        head.left.right.parent = head.left;
        head.left.right.right = new TreeNode(5);
        head.left.right.right.parent = head.left.right;
        head.right = new TreeNode(9);
        head.right.parent = head;
        head.right.left = new TreeNode(8);
        head.right.left.parent = head.right;
        head.right.left.left = new TreeNode(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new TreeNode(10);
        head.right.right.parent = head.right;

        inorderTraversal(head);
        System.out.println();

        TreeNode test = head.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        System.out.println(test.value + " pre: " + getPrecursorNode(test));
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        System.out.println(test.value + " pre: " + getPrecursorNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        System.out.println(test.value + " pre: " + getPrecursorNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        System.out.println(test.value + " pre: " + getPrecursorNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        System.out.println(test.value + " pre: " + getPrecursorNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        System.out.println(test.value + " pre: " + getPrecursorNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        System.out.println(test.value + " pre: " + getPrecursorNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        System.out.println(test.value + " pre: " + getPrecursorNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        System.out.println(test.value + " pre: " + getPrecursorNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getSuccessorNode(test));
        System.out.println(test.value + " pre: " + getPrecursorNode(test).value);
    }

    private static void inorderTraversal(TreeNode head) {
        if (head == null) {
            return;
        }
        inorderTraversal(head.left);
        System.out.print(head.value + " ");
        inorderTraversal(head.right);
    }

}
