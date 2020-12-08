package com.gaowj.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * created by gaowj.
 * created on 2020-12-07.
 * function: 94. 二叉树的中序遍历
 * origin -> https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 */
public class InorderTraversal {
    // 递归遍历
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        List<Integer> leftList = inorderTraversal(root.left);
        res.addAll(leftList);
        res.add(root.val);
        List<Integer> rightList = inorderTraversal(root.right);
        res.addAll(rightList);

        return res;
    }

    // 迭代遍历
    public List<Integer> inorderTraversal2(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null)
            return res;

        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.poll();
            res.add(node.val);
            node = node.right;
        }

        return res;
    }
}
