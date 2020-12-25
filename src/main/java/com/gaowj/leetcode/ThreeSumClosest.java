package com.gaowj.leetcode;

import java.util.Arrays;

/**
 * created by gaowj.
 * created on 2020-12-24.
 * function: 16. 最接近的三数之和(数组) 双指针
 * origin -> https://leetcode-cn.com/problems/3sum-closest/solution/hua-jie-suan-fa-16-zui-jie-jin-de-san-shu-zhi-he-b/
 */
public class ThreeSumClosest {
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(target - sum) < Math.abs(target - res))
                    res = sum;
                if (sum > target)
                    right--;
                else if (sum < target)
                    left++;
                else
                    return sum;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-3, -2, -5, 3, -4};
        System.out.println(threeSumClosest(nums, -1));
    }
}
