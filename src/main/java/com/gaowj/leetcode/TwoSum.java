package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-11-17.
 * function: 双指针  二分查找
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 * origin ->
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/all-about-array/x9i1x6/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class TwoSum {

    /**
     * 二分查找：获取两数之和等于目标值的两个下标
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] findTwoSum(int[] nums, int target) {
        if (nums == null)
            return null;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > target)
                return null;
            int findValue = target - nums[i];
            int findValueIndex = searchRecursive(nums, i + 1, nums.length - 1, findValue);
            if (findValueIndex != -1)
                return new int[]{i + 1, findValueIndex + 1};
        }
        return null;
    }

    /**
     * 双指针
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoIndex(int[] nums, int target) {
        if (nums == null)
            return null;
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int sum = nums[low] + nums[high];
            if (sum == target) {
                return new int[]{low + 1, high + 1};
            } else {
                if (sum < target)
                    low++;
                else
                    high--;
            }
        }
        return null;
    }

    /**
     * 递归式二分查找
     *
     * @param nums
     * @param low
     * @param high
     * @param findValue
     * @return
     */
    public static int searchRecursive(int nums[], int low, int high, int findValue) {
        if (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == findValue) {
                return mid;
            } else {
                if (findValue < nums[mid]) {
                    return searchRecursive(nums, low, mid - 1, findValue);
                } else {
                    return searchRecursive(nums, mid + 1, high, findValue);
                }
            }
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        int nums[] = {-1, 0};
        int target = -1;
//        int[] twoSumIndex = findTwoSum(nums, target);
        int[] twoSumIndex = twoIndex(nums, target);
        if (twoSumIndex != null) {
            for (int i : twoSumIndex) {
                System.out.print(String.valueOf(i) + " ");
            }
        }
    }
}
