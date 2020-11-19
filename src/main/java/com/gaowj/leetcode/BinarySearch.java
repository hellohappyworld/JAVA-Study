package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-11-18.
 * function: 二分查找算法
 * origin -> https://www.jianshu.com/p/2a5003422210
 */
public class BinarySearch {

    /**
     * 递归二分查找
     *
     * @param nums
     * @param low
     * @param high
     * @param findValue
     * @return
     */
    public static int searchRecursive(int[] nums, int low, int high, int findValue) {
        if (nums == null)
            return -1;
        if (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == findValue) {
                return mid;
            } else if (findValue < nums[mid]) {
                return searchRecursive(nums, low, mid - 1, findValue);
            } else {
                return searchRecursive(nums, mid + 1, high, findValue);
            }
        } else {
            return -1;
        }
    }

    /**
     * 循环二分查找
     *
     * @param nums
     * @param findValue
     * @return
     */
    public static int searchLoop(int[] nums, int findValue) {
        if (nums == null)
            return -1;
        int low = 0;
        int high = nums.length - 1;
        int mid = (low + high) / 2;
        while (low <= high) {
            if (nums[mid] == findValue) {
                return mid;
            } else {
                if (findValue < nums[mid]) {
                    high = mid - 1;
                    mid = (low + high) / 2;
                } else {
                    low = mid + 1;
                    mid = (low + high) / 2;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int nums[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        System.out.println(searchRecursive(nums, 0, nums.length - 1, 2));
        System.out.println(searchLoop(nums, 0));
    }
}
