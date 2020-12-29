package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-12-28.
 * function: 112. 路径总和(树)
 * origin -> https://leetcode-cn.com/problems/path-sum/
 */
public class HasPathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        int sum1 = 0;
        return check(root, sum, sum1);
    }

    public static boolean check(TreeNode root, int sum, int sum1) {
        if (root == null)
            return false;
        sum1 += root.val;
        if (sum == sum1 && root.left == null && root.right == null)
            return true;
        else
            return check(root.left, sum, sum1) || check(root.right, sum, sum1);
    }
}
