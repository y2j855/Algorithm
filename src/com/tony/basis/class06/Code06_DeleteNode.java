package com.tony.basis.class06;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/14 09:58
 * Description: 删除单链表指定节点
 * 能不能不给单链表的头节点，只给想要删除的节点，就能做到在链表上把这个点删掉
 */
public class Code06_DeleteNode {
    /**
     * 删除单链表任意节点，如果是尾节点不可删除
     * 原因：如果node是尾节点，node传进来的是尾节点的引用
     * 如果把node指向null，只是把node与尾节点的关系去掉，但是对于尾节点本身还是和整个链表相连接的。所以是去不掉的
     * 这是和jvm内存结构有关的。
     * 所以尾节点是不能删除的，其他节点能够删除是因为你能够获取下一个节点的指针，所以能够改变链表next的指向
     * @param node
     * @return
     */
    public static boolean deleteNode(Node node){
        if(node.next != null) {
            node.value = node.next.value;
            node.next = node.next.next;
        }else{  //证明是尾节点
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        System.out.println(deleteNode(n4));
        System.out.println(n1);



    }
}
