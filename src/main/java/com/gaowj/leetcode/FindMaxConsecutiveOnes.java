package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-12-22.
 * function: 485. 最大连续1的个数
 * origin -> https://leetcode-cn.com/problems/max-consecutive-ones/
 */
public class FindMaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int res = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                max += 1;
                res = Math.max(max, res);
            } else {
                max = 0;
            }
        }
        return res;
    }
}
