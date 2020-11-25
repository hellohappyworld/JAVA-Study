package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-11-24.
 * function: 35. 搜索插入位置（二分查找）
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 * origin -> https://leetcode-cn.com/problems/search-insert-position/
 */
public class SearchInsert {

    public static int searchInsert(int[] nums, int target, int low, int high) {
        if (low == high) {
            if (nums[low] == target)
                return low;
            else if (nums[low] < target)
                return low + 1;
            else
                return low;
        }
        if (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                return searchInsert(nums, target, mid + 1, high);
            else
                return searchInsert(nums, target, low, mid - 1);
        } else {
            return low;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 3};
        System.out.println(searchInsert(nums, 0, 0, nums.length - 1));
    }
}
