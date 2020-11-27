package com.gaowj.leetCode;

import java.util.ArrayList;

/**
 * created by gaowj.
 * created on 2020-11-23.
 * function:
 * origin ->
 */
public class LeetCode {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        System.out.println(arrayList.get(3));
    }

    public static int climbStairs(int n) {
        if (n == 0 || n == 1)
            return 1;
        int i = climbStairs(n - 1);
        int j = climbStairs(n - 2);
        return i + j;
    }

    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++)
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            max = Math.max(max, dp[i]);
        }
        return max;
    }


    public static int binarySearch(int[] nums, int low, int high, int target) {
        if (nums == null)
            return -1;
        int mid = (low + high) / 2;
        if (nums[mid] == target)
            return mid;
        else if (nums[mid] < target)
            return binarySearch(nums, mid + 1, high, target);
        else
            return binarySearch(nums, low, mid - 1, target);
    }

    public static int[] twoSum(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum == target)
                break;
            else if (sum < target)
                i++;
            else
                j--;
        }
        i++;
        j++;
        int[] tmp = {i, j};
        return tmp;
    }

    public static void mergeSort(int[] nums, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(nums, low, mid);
            mergeSort(nums, mid + 1, high);
            merge(nums, low, mid, high);
        }
    }

    public static void merge(int[] nums, int low, int mid, int high) {
        int[] tmpArr = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= high) {
            if (nums[i] <= nums[j]) {
                tmpArr[k] = nums[i];
                i++;
                k++;
            } else {
                tmpArr[k] = nums[j];
                j++;
                k++;
            }
        }
        while (i <= mid) {
            tmpArr[k] = nums[i];
            i++;
            k++;
        }
        while (j <= high) {
            tmpArr[k] = nums[j];
            j++;
            k++;
        }
        for (int p = 0; p < tmpArr.length; p++)
            nums[low++] = tmpArr[p];
    }


}
