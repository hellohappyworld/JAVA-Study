package com.gaowj.leetcode;

import java.util.HashSet;

/**
 * created by gaowj.
 * created on 2021-04-27.
 * function: 141. 环形链表
 * origin ->
 */
public class HasCycle {
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head))
                return true;
            head = head.next;
        }
        return false;
    }
}
