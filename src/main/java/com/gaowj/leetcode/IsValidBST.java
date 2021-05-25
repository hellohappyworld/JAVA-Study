package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2021-04-02.
 * function: 98. 验证二叉搜索树(二叉搜索树)
 * origin ->
 */
public class IsValidBST {
    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        if ((root.left != null && root.left.val < root.val) && (root.right != null && root.val < root.right.val)) {
            return isValidBST(root.left) && isValidBST(root.right);
        } else
            return false;
    }
}
