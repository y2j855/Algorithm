package com.tony.basis.class07;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/16 10:57
 * Description:
 */
public class ConstructTree {
    public static Node constructTree(Integer[] nums){
        if (nums.length == 0) return new Node(0);
        Queue<Node> nodeQueue = new LinkedList<>();
        // 创建一个根节点
        Node root = new Node(nums[0]);
        nodeQueue.offer(root);
        Node cur;
        // 记录当前行节点的数量（注意不一定是2的幂，而是上一行中非空节点的数量乘2）
        int lineNodeNum = 2;
        // 记录当前行中数字在数组中的开始位置
        int startIndex = 1;
        // 记录数组中剩余的元素的数量
        int restLength = nums.length - 1;

        while(restLength > 0) {
            // 只有最后一行可以不满，其余行必须是满的
//            // 若输入的数组的数量是错误的，直接跳出程序
//            if (restLength < lineNodeNum) {
//                System.out.println("Wrong Input!");
//                return new TreeNode(0);
//            }
            for (int i = startIndex; i < startIndex + lineNodeNum; i = i + 2) {
                // 说明已经将nums中的数字用完，此时应停止遍历，并可以直接返回root
                if (i == nums.length) return root;
                cur = nodeQueue.poll();
                if (nums[i] != null) {
                    cur.left = new Node(nums[i]);
                    nodeQueue.offer(cur.left);
                }
                // 同上，说明已经将nums中的数字用完，此时应停止遍历，并可以直接返回root
                if (i + 1 == nums.length) return root;
                if (nums[i + 1] != null) {
                    cur.right = new Node(nums[i + 1]);
                    nodeQueue.offer(cur.right);
                }
            }
            startIndex += lineNodeNum;
            restLength -= lineNodeNum;
            lineNodeNum = nodeQueue.size() * 2;
        }

        return root;
    }

    public static void main(String[] args) {
        Integer[] nums = {5,4,8,11,null,13,4,7,2,null,null,null,1};
        Node root = ConstructTree.constructTree(nums);
        System.out.println(root);
    }
}
