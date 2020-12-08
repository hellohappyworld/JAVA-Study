package com.gaowj.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * created by gaowj.
 * created on 2020-12-07.
 * function:
 * origin ->
 */
public class PostorderTraversal {
    // 递归遍历
    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null)
            return res;

        List<Integer> leftList = postorderTraversal(root.left);
        res.addAll(leftList);
        List<Integer> rightList = postorderTraversal(root.right);
        res.addAll(rightList);
        res.add(root.val);

        return res;
    }

    // 迭代遍历
    public List<Integer> postorderTraversal2(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null)
            return res;

        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode preV = null;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == preV) {
                res.add(root.val);
                preV = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }
}
