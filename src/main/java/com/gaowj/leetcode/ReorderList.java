package com.gaowj.leetcode;

import java.util.ArrayList;

/**
 * created by gaowj.
 * created on 2021-09-07.
 * function:143. 重排链表
 */
public class ReorderList {
    public void reorderList(ListNode head) {
        if (head == null)
            return;

        ListNode node = head;
        ArrayList<ListNode> list = new ArrayList<>();
        list.add(node);
        while (node.next != null) {
            list.add(node.next);
            node = node.next;
        }

        int size = list.size();
        int i = 0;
        int j = size - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j)
                break;
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }
}
