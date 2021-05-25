package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2021-04-27.
 * function: 136. 只出现一次的数字
 * origin ->
 */
public class SingleNumber {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i : nums)
            res ^= i;
        return res;
    }
}
