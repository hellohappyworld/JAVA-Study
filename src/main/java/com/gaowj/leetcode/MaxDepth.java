package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-12-14.
 * function: 104. 二叉树的最大深度
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * origin -> https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 */
public class MaxDepth {
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        int leftLen = maxDepth(root.left);
        int rigthLen = maxDepth(root.right);

        return Math.max(leftLen, rigthLen) + 1;
    }
}
