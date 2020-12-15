package com.gaowj.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * created by gaowj.
 * created on 2020-12-14.
 * function: 102. 二叉树的层序遍历(广度优先搜索)
 * origin -> https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 */
public class LevelOrder {
    // 循环遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            ArrayList<Integer> nodeList = new ArrayList<>();
            int queueSize = queue.size();
            for (int i = 1; i <= queueSize; i++) {
                TreeNode node = queue.poll();
                nodeList.add(node.val);
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
            res.add(nodeList);
        }

        return res;
    }
}
