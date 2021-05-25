package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2021-04-25.
 * function: 206. 反转链表
 * origin ->
 */
public class ReverseList {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }
}
