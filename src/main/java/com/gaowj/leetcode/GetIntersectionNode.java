package com.gaowj.leetcode;

import java.util.HashSet;

/**
 * created by gaowj.
 * created on 2021-05-08.
 * function: 160. 相交链表
 * origin ->
 */
public class GetIntersectionNode {
    //哈希表法
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode node = headA;
        while (node != null) {
            set.add(node);
            node = node.next;
        }
        node = headB;
        while (node != null) {
            if (set.contains(node))
                return node;
            node = node.next;
        }
        return null;
    }

    //双指针法
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        ListNode node1 = headA;
        ListNode node2 = headB;
        while (node1 != node2) {
            if (node1 != null)
                node1 = node1.next;
            else
                node1 = headB;
            if (node2 != null)
                node2 = node2.next;
            else
                node2 = headA;
        }
        return node1;
    }
}
