package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-12-09.
 * function: 53. 最大子序和(动态规划)
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * origin -> https://leetcode-cn.com/problems/maximum-subarray/
 */
public class MaxSubArray {
    public static int maxSubArray(int[] nums) {
        int pre = 0;
        int max = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            max = Math.max(max, pre);
        }
        return max;
    }
}
