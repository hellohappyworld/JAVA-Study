package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-12-22.
 * function: 153. 寻找旋转排序数组中的最小值
 * origin -> https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
 */
public class FindMin {
    public int findMin(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        int minIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                minIndex = i;
                break;
            }
        }
        return nums[minIndex];
    }
}
