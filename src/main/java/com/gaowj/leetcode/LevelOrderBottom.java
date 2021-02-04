package com.gaowj.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * created by gaowj.
 * created on 2021-02-04.
 * function: 107. 二叉树的层序遍历 II
 * origin -> https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
 */
public class LevelOrderBottom {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
                list.add(node.val);
            }
            res.add(0, list);
        }

        return res;
    }
}
