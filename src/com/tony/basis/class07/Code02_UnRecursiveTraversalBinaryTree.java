package com.tony.basis.class07;

import java.io.StringReader;
import java.util.Stack;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/15 09:46
 * Description: 二叉树非递归遍历，前序、中序、后序
 */
public class Code02_UnRecursiveTraversalBinaryTree {

    /**
     * 通过1个栈实现二叉树前序遍历，放入子节点时，先右后左
     *
     * @param head
     */
    public static void preorderTraversal(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            Node current = stack.pop();
            System.out.print(current.value + " ");
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
        System.out.println("非递归前序遍历");
    }

    /**
     * 二叉树非递归中序遍历
     * 1.将二叉树所有左子节点加入栈中
     * 2.如果没有左子节点，弹出打印，再将右子节点的左子节点放入栈中
     * 我的实现需要用一个标记来判断是否已经加过此节点为左子节点，
     * 比如"2"这个节点，第二次再进if判断时flag就是false，证明已被加过，
     * 所以要开始找右节点的左子节点
     * 缺点：逻辑有些混乱
     *
     *
     * 代码重构后，逻辑更加清晰
     * 1.定义栈之后不加入任何节点，利用head来判断
     * 2.如果头为空，证明2种情况，1.栈中还没有加入任何节点。2.左子节点已经没有了，需要遍历右节点的左子节点
     * 好处：不用在额外定义变量来判断是否已经遍历过这个节点为左子节点，
     * 逻辑比较清晰，if就是来判断是不是左子节点，并且加入栈中
     * else就是来判断是不是右子节点，如果是把它给head，让if继续判断它的逻辑
     * @param head
     */
    public static void inorderTraversal(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
//        while (!stack.isEmpty()) {
//            Node current = stack.peek();
//            if (current.left != null && flag) {
//                stack.push(current.left);
//            } else {
//                flag = false;
//                current = stack.pop();
//                System.out.println(current.value);
//                if (current.right != null) {
//                    flag = true;
//                    stack.push(current.right);
//                }
//            }
//        }

        while(!stack.isEmpty() || head != null){
            //再判断head是否为空，是因为左节点已经没有了，需要遍历右节点的左节点
            if(head != null){
                stack.push(head);
                head = head.left;
            }else{
                head = stack.pop();
                System.out.print(head.value + " ");
                head = head.right;
            }
        }
        System.out.println("非递归中序遍历");
    }


    /**
     * 二叉树非递归后序遍历，利用2个栈实现
     * 1.第一个栈，按照先左后右加入，形成的顺序就是后序的反向，
     * 2.第二个栈接第一个栈的数据，就是后序的正向。
     *
     * @param head
     */
    public static void postorderTraversal(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> input = new Stack<>();
        Stack<Node> output = new Stack<>();

        input.push(head);
        while (!input.isEmpty()) {
            Node current = input.pop();
            if (current.left != null) {
                input.push(current.left);
            }
            if (current.right != null) {
                input.push(current.right);
            }
            output.push(current);
        }

        while (!output.isEmpty()) {
            System.out.print(output.pop().value + " ");
        }
        System.out.println("非递归2个栈后序遍历");
    }

    /**
     * 二叉树非递归后序遍历,利用2个变量和1个栈解决
     * 逻辑思路
     * 1.head：代表头节点，代表已被处理过的节点
     * 2.untreated：未处理的节点
     * 3.先加入头节点，循环遍历栈，untreated获取栈顶数据，
     * 4.未处理节点是否有左节点，未处理节点的左节点是否已被处理过，未处理节点的右节点是否已被处理过
     * 5.未处理节点是否有右节点，未处理节点的右节点是否已被处理过
     * 6.弹出节点并打印，将弹出的节点标记为已处理节点
     * @param head
     */
    public static void postorderTraversal2(Node head){
        if(head == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node untreated = null;
        stack.push(head);
        while (!stack.isEmpty()){
            untreated = stack.peek();
            if(untreated.left != null && head != untreated.left && head != untreated.right){
                stack.push(untreated.left);
            }else if(untreated.right != null && head != untreated.right){
                stack.push(untreated.right);
            }else{
                System.out.print(stack.pop().value + " ");
                head = untreated;
            }
        }
        System.out.println("非递归变量后序遍历");
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        preorderTraversal(head);
        System.out.println("========");
        inorderTraversal(head);
        System.out.println("========");
        postorderTraversal(head);
        System.out.println("========");
        postorderTraversal2(head);
        System.out.println("========");
    }

}
