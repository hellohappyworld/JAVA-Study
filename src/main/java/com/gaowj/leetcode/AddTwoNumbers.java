package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2021-10-11.
 * function: 2. 两数相加
 * origin -> https://leetcode-cn.com/problems/add-two-numbers/solution/jian-dan-yi-dong-javacpythonjs-pei-yang-y2w6g/
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;

            int sum = x + y + carry;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            carry = sum / 10;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry != 0) curr.next = new ListNode(carry);
        return dummy.next;
    }
}
