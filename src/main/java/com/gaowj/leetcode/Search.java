package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2021-09-23.
 * function:
 * origin ->
 */
public class Search {
    public int search(int[] nums, int target) {
        int length = nums.length;
        if (length == 0)
            return -1;
        if (length == 1)
            return nums[0] == target ? 0 : -1;

        int left = 0;
        int right = length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target)
                return mid;
            //判断左边还是右边有序
            if (nums[0] <= nums[mid]) { //左边有序
                if (nums[0] <= target && target < nums[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            } else {//右边有序
                if (nums[mid] < target && target <= nums[length - 1])
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }

        return -1;
    }
}
