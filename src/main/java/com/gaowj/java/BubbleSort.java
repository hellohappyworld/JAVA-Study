package com.gaowj.java;

import java.util.Arrays;
import java.util.List;

/**
 * created by gaowj.
 * created on 2021-08-26.
 * function:
 * origin ->
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] nums = {8, 2, 1, 7, 3, 5, 9, 6};
        sort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    private static void sort(int[] nums) {
        boolean flag = true;
        for (int i = 0; i < nums.length && flag; i++) {
            flag = false;
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    flag = true;
                }
            }
        }
    }

    private static void swap(int[] nums, int j, int i) {
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }
}
