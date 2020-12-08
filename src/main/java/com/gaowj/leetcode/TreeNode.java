package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-12-07.
 * function: 树对象
 * origin ->
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
