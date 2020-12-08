package com.gaowj.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * created by gaowj.
 * created on 2020-12-07.
 * function: 144. 二叉树的前序遍历
 * origin -> https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 */
public class PreorderTraversal {
    // 递归遍历
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null)
            return list;
        list.add(root.val);
        List<Integer> leftList = preorderTraversal(root.left);
        list.addAll(leftList);
        List<Integer> rightList = preorderTraversal(root.right);
        list.addAll(rightList);
        return list;
    }

    // 迭代遍历
    public List<Integer> preorderTraversal2(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null)
            return list;

        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                list.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }

        return list;
    }
}
