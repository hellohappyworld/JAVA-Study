package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2021-03-30.
 * function: 303. 区域和检索 - 数组不可变（前缀和）
 * origin -> https://leetcode-cn.com/problems/range-sum-query-immutable/solution/qu-yu-he-jian-suo-shu-zu-bu-ke-bian-by-l-px41/
 */
public class NumArray {
    int[] sums;

    public NumArray(int[] nums) {
        int length = nums.length;
        sums = new int[length + 1];
        for (int i = 0; i < length; i++)
            sums[i + 1] = sums[i] + nums[i];
    }

    public int sumRange(int left, int right) {
        return sums[right + 1] - sums[left];
    }
}
