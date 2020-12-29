package com.gaowj.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * created by gaowj.
 * created on 2020-12-28.
 * function: 106. 从中序与后序遍历序列构造二叉树（树）
 * origin -> https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 */
public class BuildTree {
    int postIndex;
    Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();
    int[] inorder;
    int[] postorder;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postIndex = postorder.length - 1;
        this.inorder = inorder;
        this.postorder = postorder;
        int i = 0;
        for (int index : inorder)
            inMap.put(index, i++);
        return getTree(0, postorder.length - 1);
    }

    public TreeNode getTree(int left, int high) {
        if (left > high)
            return null;
        int rootVal = postorder[this.postIndex];
        postIndex--;
        TreeNode root = new TreeNode(rootVal);
        int index = inMap.get(rootVal);
        root.right = getTree(index + 1, high);
        root.left = getTree(left, index - 1);
        return root;
    }
}
