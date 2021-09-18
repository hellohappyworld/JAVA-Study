package com.gaowj.java;

/**
 * created by gaowj.
 * created on 2021-09-02.
 * function: 合并排序
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] nums = {2, 1, 7, 9, 5, 8};
        sort(nums, 0, nums.length - 1);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    private static void sort(int[] nums, int left, int ritht) {
        if (left >= ritht)
            return;
        int mid = left + (ritht - left) / 2;
        sort(nums, left, mid);
        sort(nums, mid + 1, ritht);
        merge(nums, left, mid, ritht);
    }

    //合并两个数组
    private static void merge(int[] nums, int left, int mid, int ritht) {
        int[] tmpNums = nums.clone();
        int p = left;//用于指定左边数组的第一个元素位置
        int q = mid + 1;//用于指定右边数组的第一个元素位置
        for (int i = left; i <= ritht; i++) {
            if (p > mid)
                nums[i] = tmpNums[q++];
            else if (q > ritht)
                nums[i] = tmpNums[p++];
            else if (tmpNums[p] < tmpNums[q])
                nums[i] = tmpNums[p++];
            else
                nums[i] = tmpNums[q++];
        }
    }
}
