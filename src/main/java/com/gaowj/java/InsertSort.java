package com.gaowj.java;

/**
 * created by gaowj.
 * created on 2021-08-27.
 * function: 插入排序
 * origin -> https://blog.csdn.net/afei__/article/details/82949003
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] nums = {5, 2, 4, 5, 1, 3};
        sort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    private static void sort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0; j--) {
                if (nums[j] < nums[j - 1])
                    swap(nums, j, j - 1);
                else
                    break;
            }
        }
    }

    private static void swap(int[] nums, int j, int i) {
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }
}
