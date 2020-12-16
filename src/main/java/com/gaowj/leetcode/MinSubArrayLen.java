package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-12-15.
 * function: 209. 长度最小的子数组(数组)
 * origin -> https://leetcode-cn.com/problems/minimum-size-subarray-sum/
 */
public class MinSubArrayLen {
    public int minSubArrayLen(int s, int[] nums) {
        int res = 0;
        int sum = 0;
        int low = 0;
        int high = 0;
        int length = nums.length;
        while (high < length) {
            sum = sum + nums[high];
            while (sum >= s) {
                res = res == 0 ? high - low + 1 : Math.min(res, high - low + 1);
                sum = sum - nums[low];
                low++;
            }
            high++;
        }
        return res;
    }
}
