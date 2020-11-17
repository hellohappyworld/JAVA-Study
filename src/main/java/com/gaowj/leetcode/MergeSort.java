package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-11-17.
 * function: 归并排序
 * 平均时间复杂度： O(NLogN)
 * 最好情况时间复杂度： O(NLogN)
 * 最差情况时间复杂度： O(NLogN)
 * 所需要额外空间： 递归：O(N + LogN)， 非递归：O（N）
 * 稳定性： 稳定
 * 归并排序基于分治（快排也是），利用归并来实现排序，其基本思想是：
 * 如果一个数组有n个数据，则可以把这个数组看作n个有序的子序列，每个子序列的长度为1，然后两两归并，就能得到[n/2]个长度为2（或者1，落单的)的字序列，再不断地两两归并，直到得到一个长度为n的有序数组。
 * origin -> https://www.jianshu.com/p/39dd1d9b491d
 */
public class MergeSort {

    public static void mergeSort(int nums[], int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(nums, low, mid);
            mergeSort(nums, mid + 1, high);
            merge(nums, low, mid, high);
        }
    }

    public static void merge(int nums[], int low, int mid, int high) {
        int[] tmpArr = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        for (; i <= mid && j <= high; k++) {
            if (nums[i] < nums[j]) {
                tmpArr[k] = nums[i++];
            } else {
                tmpArr[k] = nums[j++];
            }
        }
        while (i <= mid) {
            tmpArr[k++] = nums[i++];
        }
        while (j <= high) {
            tmpArr[k++] = nums[j++];
        }
        for (int p = 0; p < tmpArr.length; p++)
            nums[low++] = tmpArr[p];
    }

    public static void main(String[] args) {
        int[] nums = {9, 3, 5, 1, 6, 3, 8, 6, 7, 7, 8, 2, 5, 9, 1, 0};
        mergeSort(nums, 0, nums.length - 1);
        for (int i : nums)
            System.out.print(String.valueOf(i) + " ");
    }
}
