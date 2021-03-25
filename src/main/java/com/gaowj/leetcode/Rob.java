package com.gaowj.leetcode;

import java.util.Arrays;

/**
 * created by gaowj.
 * created on 2021-03-15.
 * function:198. 打家劫舍(DP)    213. 打家劫舍 II(DP)
 * origin -> https://leetcode-cn.com/problems/house-robber/
 */
public class Rob {
    // 213. 打家劫舍 II(DP)
    public int rob2(int[] nums) {
        if (nums == null)
            return 0;
        if (nums.length == 1)
            return nums[0];
        return Math.max(steal(Arrays.copyOfRange(nums, 0, nums.length - 1)),
                steal(Arrays.copyOfRange(nums, 1, nums.length)));
    }

    public static int steal(int[] nums) {
        int pre = 0;
        int cur = 0;
        for (int n : nums) {
            int tmp = Math.max(pre + n, cur);
            pre = cur;
            cur = tmp;
        }
        return cur;
    }


    //滚动数组
    public int rob1(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int length = nums.length;
        if (length == 1)
            return nums[0];
        int first = nums[0];
        int second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }

        return second;
    }

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int length = nums.length;
        if (length == 1)
            return nums[0];
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++)
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);

        return dp[length - 1];
    }
}
