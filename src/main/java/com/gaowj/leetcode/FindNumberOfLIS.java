package com.gaowj.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * created by gaowj.
 * created on 2020-12-01.
 * function: 673. 最长递增子序列的个数 （动态规划）
 * 给定一个未排序的整数数组，找到最长递增子序列的个数。
 * origin -> https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/solution/dong-tai-gui-hua-dong-tu-fu-zhu-li-jie-ru-you-bang/
 */
public class FindNumberOfLIS {
    public static int findNumberOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int[] count = new int[nums.length];
        for (int i = 0; i < count.length; i++)
            count[i] = 1;
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i])
                        //第一次找到
                        count[i] = count[j];
                    else if (dp[j] + 1 == dp[i])
                        //再次找到
                        count[i] += count[j];
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        //最后的返回值应该是最大长度的所有count的总和
        int maxDp = Arrays.stream(dp).max().getAsInt();
        int maxCount = 0;
        for (int i = 0; i < dp.length; i++)
            if (dp[i] == maxDp)
                maxCount += count[i];
        return maxCount;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 4, 7};
        int numberOfLIS = findNumberOfLIS(nums);
        System.out.println(numberOfLIS);
    }
}
