package com.tony.basis.class07;

import java.util.*;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/15 19:49
 * Description: 序列化反序列化二叉树
 */
public class Code04_SerializeAndReconstructTree {

    /**
     * 先序序列化对外接口
     *
     * @param head
     * @return
     */
    public static Queue<Node> preorderSerial(Node head) {
        Queue<Node> serial = new LinkedList<>();
        preorderSerial(head, serial);
        return serial;
    }

    /**
     * 先序序列化二叉树
     * 用非递归的方式实现会有很多坑，果断采取递归方式序列化
     * 递归方法设计时，要考虑base case，就是当节点为空把null值赋值给数组保证二叉树的结构
     * 用队列存反序列化时比较方便
     *
     * @param head
     * @param serial
     */
    public static void preorderSerial(Node head, Queue<Node> serial) {
        //先序遍历二叉树，发现节点为null,给它用null赋值
        if (head == null) {
            serial.add(null);
        } else {
            serial.add(head);
            preorderSerial(head.left, serial);
            preorderSerial(head.right, serial);
        }
    }

    /**
     * 反序列化对外接口
     *
     * @param serial
     * @return
     */
    public static Node preorderUnSerial(Queue<Node> serial) {
        if (serial == null) {
            return null;
        }
        return preUnSerial(serial);
    }

    /**
     * 反序列化一定要与序列化匹配
     * 序列化用的是前序，反序列化也要用前序
     *
     * @param serial
     * @return
     */
    public static Node preUnSerial(Queue<Node> serial) {
        Node head = serial.poll();
        if (head == null) {
            return null;
        } else {//可以不写左半部，因为queue存的就是Node对象，如果只存值就要写
            head.left = preUnSerial(serial);
            head.right = preUnSerial(serial);
        }
        return head;
    }

    /**
     * 按层遍历二叉树序列化
     *
     * @param head
     * @return
     */
    public static Queue<String> levelSerial(Node head) {
        Queue<String> serial = new LinkedList<>();
        Queue<Node> data = new LinkedList<>();
        data.add(head);
        serial.add(String.valueOf(head.value));
        while (!data.isEmpty()) {
            Node current = data.poll();
            if (current.left != null) {
                data.add(current.left);
            }
            if (current.right != null) {
                data.add(current.right);
            }
            serial.add(current.left != null ? String.valueOf(current.left.value) : null);
            serial.add(current.right != null ? String.valueOf(current.right.value) : null);
        }
        return serial;
    }

    /**
     * 反序列化按层遍历二叉树
     * 1.重构代码的重要性
     * 2.序列化是什么方式，反序列化就是什么方式
     * 3.因为新加的节点左右节点肯定为空，这是按照这种逻辑做的，可能不太好理解
     *
     * @param serial
     * @return
     */
    public static Node levelUnSerial(Queue<String> serial) {
        Queue<Node> data = new LinkedList<>();
        if (serial == null || serial.isEmpty()) {
            return null;
        }
        Node head = generateNode(serial.poll());
        data.add(head);
        while (!data.isEmpty()) {
            Node current = data.poll();
//            if(current != null && current.left == null){
//                current.left = generateNode(serial.poll());
//                data.add(current.left);
//            }
//            if(current != null && current.right == null){
//                current.right = generateNode(serial.poll());
//                data.add(current.right);
//            }
            if (current != null) {
                current.left = generateNode(serial.poll());
                data.add(current.left);
                current.right = generateNode(serial.poll());
                data.add(current.right);
            }
        }
        return head;
    }

    /**
     * 完全按照按层遍历的逻辑进行反序列化
     * 拿到父节点，然后将左节点右节点赋值
     * 按照按层遍历逻辑进行判断
     *
     * @param serial
     * @return
     */
    public static Node levelUnSerial1(Queue<String> serial) {
        Queue<Node> data = new LinkedList<>();
        if (serial == null || serial.isEmpty()) {
            return null;
        }
        Node head = generateNode(serial.poll());
        data.add(head);
        while (!data.isEmpty()) {
            Node current = data.poll();
            current.left = generateNode(serial.poll());
            current.right = generateNode(serial.poll());
            if (current.left != null) {
                data.add(current.left);
            }
            if (current.right != null) {
                data.add(current.right);
            }
        }
        return head;
    }

    /**
     * 按层遍历反序列化，接收int类型数组，为打印二叉树使用
     *
     * @param serial
     * @return
     */
    public static Node leveUnSerialUseArray(Integer[] serial) {
        if (serial == null || serial.length == 0) {
            return null;
        }
        //1.判null
        //2.拿头节点
        //3.数组从1开始循环遍历，如果数组里变有null，做判断。
        Queue<Node> queue = new LinkedList<>();
        Node head = new Node(serial[0]);
        queue.offer(head);
        Node current = null;

        //当前层开始的下标位置
        int startIndex = 1;
        //当前层的节点个数
        int currentLevelNum = 2;
        //数组中剩余的节点个数
        int restLength = serial.length - 1;

        while(restLength > 0) {
            //i+2:因为按层遍历需要一次对左右节点进行同时操作
            for (int i = startIndex; i < startIndex + currentLevelNum; i = i + 2) {
                //已没有节点可以使用，直接跳出循环头节点
                if (i == serial.length) {
                    return head;
                }
                current = queue.poll();
                if (serial[i] != null) {
                    current.left = new Node(serial[i]);
                    queue.offer(current.left);
                    startIndex++;
                }
                if (i + 1 == serial.length) {
                    return head;
                }
                if (serial[i + 1] != null) {
                    current.right = new Node(serial[i + 1]);
                    queue.offer(current.right);
                    startIndex++;
                }
            }
            currentLevelNum = (startIndex - 1) << 1;
//            currentLevelNum = queue.size() << 1;
            restLength -= startIndex;
        }
        return head;
    }

    private static Node generateNode(String value) {
        if (value == null) {
            return null;
        }
        return new Node(Integer.valueOf(value));
    }


    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node left = new Node(2);
        Node right = new Node(3);
        head.left = left;
        left.right = right;

        Queue<Node> pre = preorderSerial(head);
        System.out.println(pre.size());

        Node head1 = preorderUnSerial(pre);
        System.out.println(head1);

        Queue<String> queue = levelSerial(head);
        System.out.println(queue.size());

        Node node = levelUnSerial1(queue);
        System.out.println(node);

        Integer[] numbers = {5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1};
        Node test = leveUnSerialUseArray(numbers);
        System.out.println(test);
    }
}
