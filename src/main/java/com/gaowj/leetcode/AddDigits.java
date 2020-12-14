package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-12-11.
 * function: 258. 各位相加（数学）
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
 * <p>
 * 示例:
 * <p>
 * 输入: 38
 * 输出: 2
 * 解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。
 * origin -> https://leetcode-cn.com/problems/add-digits/
 */
public class AddDigits {
    public int addDigits(int num) {
        while (num / 10 != 0) {
            int mod = num % 10;
            num = num / 10 + mod;
        }
        return num;
    }
}
