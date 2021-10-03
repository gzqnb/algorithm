package com.gzq.algorithm.linked_list;

import com.gzq.algorithm.string.RemoveDuplicateLetters;

import java.util.Stack;

public class RemoveNthFromEnd {
    //方法1:计算链表长度
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        //1.遍历链表，得到长度l
        int l = getLength(head);
        //2.从前到后继续遍历，找到正数第l-n+1个元素
        //定义一个哨兵节点，next指向头结点
        ListNode sentinel = new ListNode(-1, head);
        ListNode curr = sentinel;
        for (int i = 0; i < l - n; i++) {//到一个删除前面的一个节点
            curr = curr.next;
        }
        //找到第l-n个节点

        //跳过第l-n+1个节点
        curr.next = curr.next.next;
        return sentinel.next;
    }

    //定义一个计算链表长度的方法
    public static int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    //方法2:使用栈
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //定义一个哨兵节点，next指向头结点
        ListNode sentinel = new ListNode(-1, head);
        ListNode curr = sentinel;
        //定义一个栈
        Stack<ListNode> stack = new Stack<>();
        //1.将所有节点入栈
        while (curr != null) {
            stack.push(curr);
            curr = curr.next;
        }

        //2.弹栈n次
        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        stack.peek().next = stack.peek().next.next;
        return sentinel.next;
    }

    public static void main(String[] args) {
        //构建一个链表,把所有节点创建出来，然后连接
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = null;

        RemoveNthFromEnd removeNthFromEnd = new RemoveNthFromEnd();
        ListNode listNode = removeNthFromEnd.removeNthFromEnd(listNode1, 2);
        TestLinkedList.printList(listNode);
    }
}
