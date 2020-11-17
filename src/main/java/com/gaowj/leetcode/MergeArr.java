package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-11-17.
 * function:
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * 说明：
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * origin ->
 */
public class MergeArr {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int tail = nums1.length - 1; //nums1数组最后下标
        m--; //nums1元素最后位置下标
        n--; //nums2元素最后位置下标
        while (n >= 0) {
            if (m >= 0 && nums1[m] >= nums2[n]) {
                nums1[tail--] = nums1[m--];
            } else {
                nums1[tail--] = nums2[n--];
            }
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {0};
        int m = 0;
        int[] nums2 = {1};
        int n = 1;
        merge(nums1, m, nums2, n);

        for (int i : nums1) {
            System.out.print(String.valueOf(i) + " ");
        }
    }
}
