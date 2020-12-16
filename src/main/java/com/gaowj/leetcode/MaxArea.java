package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-12-15.
 * function: 11. 盛最多水的容器（数组）
 * origin -> https://leetcode-cn.com/problems/container-with-most-water/
 */
public class MaxArea {
    public int maxArea(int[] height) {
        int res = 0;
        int low = 0;
        int high = height.length - 1;
        while (low < high) {
            res = Math.max(res, (high - low) * Math.min(height[low], height[high]));
            if (height[low] < height[high])
                low++;
            else
                high--;
        }
        return res;
    }
}
