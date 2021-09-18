package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2021-09-18.
 * function:109. 有序链表转换二叉搜索树
 * origin ->https://www.bilibili.com/video/BV1QA411F7Pb?from=search&seid=1419307196362144642&spm_id_from=333.337.0.0
 */
public class SortedListToBST {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;
        if (head.next == null) {
            TreeNode root = new TreeNode(head.val);
            return root;
        }

        return helper(head);
    }

    private TreeNode helper(ListNode head) {
        if (head == null)
            return null;

        //快慢指针用于找到中间节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode rootNode = slow.next;//slow.next就是中间节点，即是树的根节点
        TreeNode root = new TreeNode(rootNode.val);
        slow.next = null;
        ListNode leftHead = dummy.next;
        ListNode rightHead = rootNode.next;
        root.left = helper(leftHead);
        root.right = helper(rightHead);

        return root;
    }
}
