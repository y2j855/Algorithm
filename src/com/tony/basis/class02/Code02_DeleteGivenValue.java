package com.tony.basis.class02;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/29 15:52
 * Description:
 */
public class Code2_DeleteGivenValue {

    public static Node removeListValue(Node head, int number) {
        while (head != null) {
            if (head.value != number) {
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == number) {
                pre.next = cur.next;    //去掉要删除节点一下个的引用
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static DoubleNode removeDoubleListValue(DoubleNode head, int number) {
        while (head != null) {
            if (head.value != number) {
                break;
            }
            head = head.next;
            head.pre = null;
        }
        DoubleNode pre = head;
        DoubleNode cur = head;
        DoubleNode temp = head;
        while (cur != null) {
            if (cur.value == number) {
                pre.next = cur.next;    //去掉要删除节点一下个的引用
            } else {
                if(cur != head) {
                    cur.pre = pre;  //将没有删除的节点的前一个节点改为正确的节点引用
                }
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static void main(String[] args) {
        Node n1 = new Node(2);
        Node n2 = new Node(3);
        Node n3 = new Node(2);
        Node n4 = new Node(2);
        Node n5 = new Node(4);
        Node n6 = new Node(6);
        Node n7 = new Node(2);
        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);
        n4.setNext(n5);
        n5.setNext(n6);
        n6.setNext(n7);
        Node node = removeListValue(n1, 2);
        System.out.println("" + node. value);

        DoubleNode dn1 = new DoubleNode(2);
        DoubleNode dn2 = new DoubleNode(3);
        DoubleNode dn3 = new DoubleNode(2);
        DoubleNode dn4 = new DoubleNode(2);
        DoubleNode dn5 = new DoubleNode(4);
        DoubleNode dn6 = new DoubleNode(2);
        DoubleNode dn7 = new DoubleNode(6);
        dn1.setNext(dn2);

        dn2.setPre(dn1);
        dn2.setNext(dn3);

        dn3.setPre(dn2);
        dn3.setNext(dn4);

        dn4.setPre(dn3);
        dn4.setNext(dn5);

        dn5.setPre(dn4);
        dn5.setNext(dn6);

        dn6.setPre(dn5);
        dn6.setNext(dn7);

        dn7.setPre(dn6);
        DoubleNode dnode = removeDoubleListValue(dn1, 2);
        System.out.println("" + dnode.value);

    }
}
