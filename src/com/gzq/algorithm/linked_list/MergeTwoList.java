package com.gzq.algorithm.linked_list;

//21
public class MergeTwoList {
    //方法1:迭代法
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        //首先定义一个哨兵节点，它的next指向最终结果的头结点
        ListNode sentinel = new ListNode(-1);
        //保存当前结果链表里的最后一个节点，作为判断比较的前一个节点
        ListNode prev = sentinel;
        //迭代遍历两个链表，直到至少有一个为null
        while (l1 != null && l2 != null) {
            //比较当前两个链表的头结点，较小的那个就接在结果链表末尾
            if (l1.val <= l2.val) {
                prev.next = l1;//将l1头结点连接到prev后面
                prev = l1; //更新指针，以下一个节点作为链表头结点
                l1 = l1.next;
            } else {
                prev.next = l2;
                prev = l2;
                l2 = l2.next;
            }
        }
        //循环结束最多还有一个链表没有遍历完成，因为已经有序，可以直接把剩余节点接到结果链表上
        if (l1 != null) {
            prev.next = l1;
        }
        if (l2 != null) {
            prev.next = l2;
        }
        return sentinel.next;
    }

    //方法2:递归
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //基准情况
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        //比较头结点
        if (l1.val <= l2.val) {
            //l1头结点较小，直接提取出来，剩下的l1和l2进行合并，接在l1后边
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            //l2头结点较小，直接提取出来，剩下的l1和l2进行合并，接在l2后边
            l2.next = mergeTwoLists(l2.next, l1);
            return l2;
        }
    }

    public static void main(String[] args) {
        //构建一个链表,把所有节点创建出来，然后连接
        ListNode listNode11 = new ListNode(1);
        ListNode listNode12 = new ListNode(2);
        ListNode listNode13 = new ListNode(4);
        ListNode listNode21 = new ListNode(1);
        ListNode listNode22 = new ListNode(3);
        ListNode listNode23 = new ListNode(4);

        listNode11.next = listNode12;
        listNode12.next = listNode13;
        listNode13.next = null;
        listNode21.next = listNode22;
        listNode22.next = listNode23;
        listNode23.next = null;

        MergeTwoList mergeTwoList = new MergeTwoList();
        ListNode listNode = mergeTwoList.mergeTwoLists(listNode11, listNode21);
        TestLinkedList.printList(listNode);
    }
}
