package com.gaowj.leetcode;

import java.util.LinkedList;

/**
 * created by gaowj.
 * created on 2020-12-28.
 * function: 101. 对称二叉树(树)
 * origin -> https://leetcode-cn.com/problems/symmetric-tree/
 */
public class IsSymmetric {
    public boolean isSymmetric(TreeNode root) {
        return check1(root, root);
    }

    // 递归
    public static boolean check1(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        if (left == null || right == null)
            return false;
        return left.val == right.val && check1(left.left, right.right) && check1(left.right, right.left);
    }

    // 循环
    public static boolean check2(TreeNode leftNode, TreeNode rightNode) {
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.offer(leftNode);
        linkedList.offer(rightNode);
        while (!linkedList.isEmpty()) {
            TreeNode p = linkedList.poll();
            TreeNode q = linkedList.poll();

            if (p == null && q == null)
                continue;
            if ((p == null || q == null) || p.val != q.val)
                return false;

            linkedList.offer(p.left);
            linkedList.offer(q.right);

            linkedList.offer(p.right);
            linkedList.offer(q.left);
        }
        return true;
    }
}
