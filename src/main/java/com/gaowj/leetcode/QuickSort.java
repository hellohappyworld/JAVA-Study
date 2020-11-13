package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-11-13.
 * function: 快速排序算法
 * origin -> https://www.jianshu.com/p/710d6f767917
 */
public class QuickSort {
    /**
     * 获取
     *
     * @param nums
     * @param mark  基准元素
     * @param start 数组开始下标位置
     * @param end   数组结束下标位置
     * @return 基准元素最终下标位置
     */
    public static int getPartIndex(int[] nums, int mark, int start, int end) {
        int j = start;
        for (int i = start; i <= end; i++) {
            if (nums[i] <= mark) {
                swap(nums, i, j);
                j++;
            }
        }

        return j - 1;
    }

    /**
     * 交换相应位置数据
     *
     * @param nums
     * @param i
     * @param j
     */
    public static void swap(int[] nums, int i, int j) {
        int swap = nums[i];
        nums[i] = nums[j];
        nums[j] = swap;
    }

    /**
     * 递归对不同partition排序
     *
     * @param nums
     * @param mark
     * @param start
     * @param end
     */
    public static void partRecursion(int[] nums, int mark, int start, int end) {
        //递归结束条件
        if (start == end)
            return;
        int partIndex = getPartIndex(nums, mark, start, end);
        if (partIndex == start) {
            partRecursion(nums, nums[end], partIndex + 1, end);
        } else if (partIndex == end) {
            partRecursion(nums, nums[partIndex - 1], start, partIndex - 1);
        } else {
            partRecursion(nums, nums[partIndex - 1], start, partIndex - 1);
            partRecursion(nums, nums[end], partIndex + 1, end);
        }
    }

    public static void main(String[] args) {
        int[] nums = {8, 2, 1, 7, 3, 5, 9, 6};
        int end = nums.length - 1;
        partRecursion(nums, nums[end], 0, end);
        String outStr = "";
        for (int i : nums) {
            outStr = outStr + "," + String.valueOf(i);
        }
        System.out.println(outStr);
    }
}
