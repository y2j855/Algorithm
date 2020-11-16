package com.tony.basis.class07;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/16 12:28
 * Description: 打印二叉树
 * 两个打印形式，个人偏向第一种，更好理解。
 * <p>
 * 第一种思路
 * 按层遍历一层一层的打印
 * 1.对外接口show，定义二维数组
 * 2.递归调用将二叉树内容写到二维数组中
 * 3.循环遍历数组打印最后的字符串
 */
public class Code05_PrintBinaryTree {
    /*第一种
    树的结构示例：
              1
            /   \
          2       3
         / \     / \
        4   5   6   7
    */

    /*第二种
    Binary Tree:
    v66v
            v3v
                                     ^55555555^
    H1H
                   ^-222222222^
    v777v
                                    ^-2147483648^
    */

    /**
     * 返回二叉树的层数
     *
     * @param head
     * @return
     */
    public static int getTreeDepth(Node head) {
        return head == null ? 0 : (1 + Math.max(getTreeDepth(head.left), getTreeDepth(head.right)));
    }

    public static void show(Node head) {
        if (head == null) {
            return;
        }
        //二叉树的层数
        int treeDepth = getTreeDepth(head);

        //数组的高度
        /**
         * 数组的宽度为2^(n-1)*3+1
         * 2是因为是二叉树，3是因为每行值与值得间隔是3个空格
         */
        int arrayHeight = (treeDepth << 1) - 1;
        int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
        //用二维数组来存放二叉树的所有元素
        String[][] result = new String[arrayHeight][arrayWidth];

        //将二维数组的所有值初始化为" "
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = " ";
            }

        }

        //从根节点开始，递归处理整棵树
        writeArray(head, 0, arrayWidth >> 1, result, treeDepth);

        //数组中已有所有要展示元素，将其拼接打印
        for (String[] line : result) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < line.length; i++) {
                sb.append(line[i]);
            }
            System.out.println(sb.toString());
        }
    }

    /**
     * 按层递归增加要显示的元素
     * @param currentNode   当前节点
     * @param rowIndex      当前节点行索引
     * @param columnIndex   当前节点列索引
     * @param result        存放元素的二维数组
     * @param treeDepth     树的深度
     */
    private static void writeArray(Node currentNode, int rowIndex, int columnIndex, String[][] result, int treeDepth) {
        if (currentNode == null) {
            return;
        }
        //将当前节点值给数组
        result[rowIndex][columnIndex] = String.valueOf(currentNode.value);

        //计算当前位于树的第几层
        int currentLevel = (rowIndex + 1) >> 1;
        //如果到了最后一层，返回 base case
        if (currentLevel == treeDepth) {
            return;
        }

        //计算当前行到下一行，每个元素之间的间隔(下一行的列索引与当前元素的列索引之间的间隔)
        int gap = treeDepth - currentLevel - 1;

        /**
         * 如果左子有值，下一行添加'/'
         * 再下一行添加元素，让递归执行
         * 右子同理
         */
        if (currentNode.left != null) {
            result[rowIndex + 1][columnIndex - gap] = "/";
            writeArray(currentNode.left, rowIndex + 2, columnIndex - gap*2, result, treeDepth);
        }
        if (currentNode.right != null) {
            result[rowIndex + 1][columnIndex + gap] = "\\";
            writeArray(currentNode.right, rowIndex + 2, columnIndex + gap*2, result, treeDepth);
        }

    }


    /**
     * 中序排序打印二叉树
     * @param head
     */
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    /**
     * 中序递归遍历，但为了满足打印顺序，故意将中序倒序打印
     * 因为倒序，所以先右后左
     * @param head
     * @param height
     * @param to
     * @param len
     */
    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        // 根据给定的数组创建一棵树
        Node root = Code04_SerializeAndReconstructTree.leveUnSerialUseArray(new Integer[] {1,2,3,4,5,6,7});
        // 将刚刚创建的树打印出来
        show(root);
        printTree(root);
    }
}
