package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-12-16.
 * function: 152. 乘积最大子数组（动态规划）
 * origin -> https://leetcode-cn.com/problems/maximum-product-subarray/
 */
public class MaxProduct {
    public static int maxProduct(int[] nums) {
        int maxF = nums[0], minF = nums[0], res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int max = maxF;
            int min = minF;
            maxF = Math.max(max * nums[i], Math.max(min * nums[i], nums[i]));
            minF = Math.min(max * nums[i], Math.min(min * nums[i], nums[i]));
            res = Math.max(res, Math.max(maxF, minF));
        }
        return res;
    }
}
