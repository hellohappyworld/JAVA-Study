package com.gaowj.leetcode;

import org.junit.Test;

/**
 * created by gaowj.
 * created on 2020-11-13.
 * function: 215. 数组中的第K个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * origin ->
 */
public class FindKthLargest {
    public static int partIndex(int[] nums, int mark, int start, int end) {
        int j = start;
        for (int i = start; i <= end; i++) {
            if (nums[i] <= mark) {
                swap(nums, i, j);
                j++;
            }
        }
        return j - 1;
    }

    public static void swap(int nums[], int i, int j) {
        int swap = nums[i];
        nums[i] = nums[j];
        nums[j] = swap;
    }

    public static void findeK(int[] nums, int mark, int start, int end, int k) {
        int part = partIndex(nums, mark, start, end);
        if (part == k) {
            return;
        } else if (part < k) {
            findeK(nums, nums[end], part + 1, end, k);
        } else {
            findeK(nums, nums[part - 1], start, part - 1, k);
        }
    }

    public static int findKthLargest(int[] nums, int k) {
        int kMaxIndex = nums.length - k;
        findeK(nums, nums[nums.length - 1], 0, nums.length - 1, kMaxIndex);
        return nums[kMaxIndex];
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        int kthLargest = findKthLargest(nums, k);
        System.out.println(nums[kthLargest]);
    }
}
