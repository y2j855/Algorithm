package com.tony.basis.class06;


/**
 * @author: Tony.Chen
 * Create Time : 2020/11/12 22:03
 * Description: 单向链表根据pivot进行小于，等于，大于的分区
 */
public class Code03_SmallerEqualBigger {

    /**
     * 笔试用
     * 利用数组，partition分区，基准值pivot进行分区
     * 缺点：额外空间复杂度O(N),由于使用数组排序是不稳定的
     *
     * @param head
     * @param pivot
     * @return
     */
    public static Node listPartition(Node head, int pivot) {
        if (head == null) {
            return null;
        }

        int arraySize = 0;
        Node current = head;
        while (current != null) {
            arraySize++;
            current = current.next;
        }
        Node[] array = new Node[arraySize];
        current = head;
        for (int i = 0; i < array.length; i++) {
            array[i] = current;
            current = current.next;
        }
        partition(array, pivot);

        //重新修改单向链表分好区的指针指向
        for (int i = 1; i < array.length; i++) {
            array[i - 1].next = array[i];
        }
        array[arraySize - 1].next = null;
        return array[0];
    }

    /**
     * 将原来的链表数组根据基准值进行分区
     *
     * @param array
     * @param pivot
     */
    private static void partition(Node[] array, int pivot) {
        int index = 0;
        int less = index - 1;
        int more = array.length;
        //[i]==pivot,i++;
        //[i]<pivot,[i]与小于区的下一个交换,小于区下标++,i++
        //[i]>pivot,[i]与大于区的上一个交换，大于区下标--,i不变
        for (int i = 0; i < array.length; i++) {
            if (array[index].value < pivot) {
                swap(array, index++, ++less);
            } else if (array[index].value > pivot) {
                swap(array, index, --more);
            } else {
                index++;
            }
        }

    }

    /**
     * 面试用
     * 用六个变量来做，额外空间复杂度为O(1)
     * @param head
     * @param pivot
     * @return
     */
    public static Node listPartitionByIndex(Node head, int pivot) {
        if (head == null) {
            return null;
        }
        Node current = null;

        Node smallHead = null;
        Node smallTail = null;

        Node equalHead = null;
        Node equalTail = null;

        Node bigHead = null;
        Node bigTail = null;

        while (head != null) {
            /**
             *  用变量接原头节点的下一个，目的是将原头节点的next指针引用清空
             *  保证原链表next指针位置清空
             */
            current = head.next;
            head.next = null;

            if (head.value == pivot) {
                if (equalHead == null) {
                    equalHead = head;
                } else {
                    equalTail.next = head;
                }
                equalTail = head;
            } else if (head.value < pivot) {
                if (smallHead == null) {
                    smallHead = head;
                } else {
                    smallTail.next = head;
                }
                smallTail = head;
            } else if (head.value > pivot) {
                if (bigHead == null) {
                    bigHead = head;
                } else {
                    bigTail.next = head;
                }
                bigTail = head;
            }
            head = current;
        }

        current = null;
        /**
         * 判断逻辑,理清思路
         *
         * 如果小于区头不为空，小于区尾连接等于区头
         * 如果等于区头不为空，等于区尾连接大于区头
         * 如果等于区头为空，小于区尾连接大于区头
         *
         * 如果等于区头不为空，等于区尾连接大于区头
         *
         * 如果大于区头不为空，直接返回大于区
         */
        current = connectLinkedList1(smallHead, smallTail, equalHead, equalTail, bigHead);

        return current;
    }

    /**
     * 将链表按照小于区，等于区，大于区连接成新的链表
     * 逻辑判断思路
     * 1.先判断小于区是否为空，如果不为空，将小于区尾连接等于区头，然后判断等于区尾是否为空，如果为空将小于区尾赋值给等于区尾，如果不为空，继续使用等于区尾
     * 2.判断等于区尾是否为空，如果为空证明小于区等于区都没有数据，如果不为空将等于区尾连接到大于区头
     * 3.最后判断小于区头是否为空，如果不为空直接返回小于区头，如果为空，在判断等于区头是否为空，如果不为空将等于区头赋值给小于区头，如果为空，直接返回大于区头
     * 这种判断逻辑的好处，将逻辑判断也顺成一条逻辑线，不用这么多if...else,先判断小于区，如果有值，无论等于区有没有值，都将等于区尾节点赋值
     * 这样的好处就是下一个判断就用等于区尾节点进行判断就可以了，如果空证明小于区，等于区都没有值，如果不为空继续下边的逻辑。
     * 在返回头节点是要做三层判断返回头节点。
     * @param smallHead
     * @param smallTail
     * @param equalHead
     * @param equalTail
     * @param bigHead
     */
    private static void connectLinkedList2(Node smallHead, Node smallTail, Node equalHead, Node equalTail, Node bigHead) {
        if (smallHead != null) {
            smallTail.next = equalHead;
            equalTail = equalTail == null ? smallTail : equalTail;
        }
        if (equalTail != null) {
            equalTail.next = bigHead;
        }

        smallHead = smallHead != null ? smallHead : (equalHead != null ? equalHead : bigHead);
    }

    /**
     * 将链表按照小于区，等于区，大于区连接成新的链表(自己写的)
     * 逻辑思路
     * 1.先判断小于区头是否为空，不为空，将小于区--->等于区--->大于区的逻辑都处理了。
     * 2.如果小于区没有值，判断等于区是否为空，不为空，将等于区--->大于区的逻辑处理。
     * 3.如果小于区等于区都没有值，判断大于区是否为空，不为空，直接返回大于区
     * 这种逻辑判断的好处:思路比较简单清晰，代码容易阅读。
     * 坏处：代码量比较多，需要一个额外变量。
     * @param smallHead
     * @param smallTail
     * @param equalHead
     * @param equalTail
     * @param bigHead
     * @return
     */
    private static Node connectLinkedList1(Node smallHead, Node smallTail, Node equalHead, Node equalTail, Node bigHead) {
        Node current = null;
        if (smallHead != null) {
            smallTail.next = equalHead;
            if (equalHead != null) {
                equalTail.next = bigHead;
            } else {
                smallTail.next = bigHead;
            }
            current = smallHead;
        }
        if (current == null && equalHead != null) {
            equalTail.next = bigHead;
            current = equalHead;
        }

        if (current == null && bigHead != null) {
            current = bigHead;
        }
        return current;
    }

    private static void swap(Node[] array, int i, int j) {
        Node temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        Node test = null;
        test = new Node(3);
        test.next = new Node(2);
        test.next.next = new Node(1);
        test.next.next.next = new Node(3);
        test.next.next.next.next = new Node(4);
        test.next.next.next.next.next = new Node(6);
        test.next.next.next.next.next.next = new Node(8);

        Node node = listPartitionByIndex(test, 3);
        System.out.println(node.value);
    }
}
