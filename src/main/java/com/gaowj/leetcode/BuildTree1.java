package com.gaowj.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * created by gaowj.
 * created on 2021-01-14.
 * function: 105. 从前序与中序遍历序列构造二叉树（树）
 * origin -> https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class BuildTree1 {
    public static int[] preOrder;
    public static int[] inOrder;
    public static int preIndex;
    public static Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        preOrder = preorder;
        inOrder = inorder;
        preIndex = 0;
        int i = 0;
        for (int inValue : inorder)
            inMap.put(inValue, i++);
        return getTree(0, preorder.length - 1);
    }

    public static TreeNode getTree(int left, int right) {
        if (left > right)
            return null;
        int preV = preOrder[preIndex];
        preIndex++;
        TreeNode rootNode = new TreeNode(preV);
        Integer index = inMap.get(preV);
        rootNode.left = getTree(left, index - 1);
        rootNode.right = getTree(index + 1, right);
        return rootNode;
    }

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        System.out.println(buildTree(preorder, inorder));

    }
}
