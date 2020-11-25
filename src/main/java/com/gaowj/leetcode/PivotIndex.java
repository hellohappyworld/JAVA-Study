package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-11-24.
 * function: 724. 寻找数组的中心索引 (方法:前缀和)
 * 给定一个整数类型的数组 nums，请编写一个能够返回数组 “中心索引” 的方法。
 * <p>
 * 我们是这样定义数组 中心索引 的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
 * <p>
 * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-pivot-index
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * origin ->
 */
public class PivotIndex {

    public static int getPivotIndex(int[] nums) {
        if (nums.length < 3)
            return -1;
        int sum = 0;
        for (int i : nums)
            sum = sum + i;
        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (leftSum == (sum - nums[i] - leftSum))
                return i;
            else
                leftSum += nums[i];
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {-1, -1, -1, 0, 1, 1};
        int pivotIndex = getPivotIndex(nums);
        System.out.println(pivotIndex);
    }
}
