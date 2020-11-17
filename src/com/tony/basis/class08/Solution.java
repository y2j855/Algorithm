package com.tony.basis.class08;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/17 11:06
 * Description:
 */
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node(int val){
        this.val = val;
    }
}
class ReturnType {
    public int min;
    public int max;
    public Node maxBSTHead;
    public int maxBSTSize;

    public ReturnType(Node maxBSTHead,int maxBSTSize,int min,int max){
        this.maxBSTHead = maxBSTHead;
        this.maxBSTSize = maxBSTSize;
        this.min = min;
        this.max = max;
    }
}

public class Solution {
    //获取当前结点X的maxBST
    //情况1，maxBST来自（注意，不是 “是“）X的左子树
    //情况2，maxBST是X的右子树
    //情况3，maxBST是X为根的整棵树
    public ReturnType process(Node X){
        //base case
        if(X == null){
            //注意，空结点的ReturnTyoe
            //min为MAX_VALUE, 保证所有的val都比它小
            //max则反之
            return new ReturnType(null, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        //获取左右子树的ReturnTyoe
        ReturnType left = process(X.left);
        ReturnType right = process(X.right);
        //信息整合
        //如果是情况1或情况2
        Node maxBSTHead = (left.maxBSTSize >= right.maxBSTSize) ? X.left : X.right;
        int maxBSTSize = (maxBSTHead == X.left) ? left.maxBSTSize : right.maxBSTSize;
        int min = Math.min(X.val, Math.min(left.min, right.min));
        int max = Math.max(X.val, Math.max(left.max, right.min));
        //如果是情况3，有两个条件
        //条件1，X的左子树和右子树都是maxBST
        //条件2，X.val 比左子树的val都大，比右子树的都小
        //如果X是叶结点，自身就是BST
        if(left.maxBSTHead == X.left && right.maxBSTHead == X.right
                && X.val > left.max && X.val < right.min){
            maxBSTHead = X;
            maxBSTSize = left.maxBSTSize + right.maxBSTSize + 1;
        }
        //返回ReturnTyoe
        return new ReturnType(maxBSTHead, maxBSTSize, min,max);
    }
    public Node getMaxBST(Node head){
        return process(head).maxBSTHead;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        Node head2 = new Node(11);
        head2.left = new Node(63);
        head2.left.left = new Node(49);
        head2.left.right = new Node(69);
        head2.left.right.left = new Node(57);

        System.out.println(s.getMaxBST(head2).val);
    }
}
