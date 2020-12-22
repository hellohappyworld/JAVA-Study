package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-12-21.
 * function: 233. 数字 1 的个数(数学)
 * origin -> https://leetcode-cn.com/problems/number-of-digit-one/
 */
public class CountDigitOne {
    public static int countDigitOne(int n) {
        int res = 0;

        while (n > 0) {
            String str = String.valueOf(n);
            for (int i = 0; i < str.length(); i++)
                if (str.charAt(i) == '1')
                    res++;
            n--;
        }

        return res;
    }

    public static void main(String[] args) {
        countDigitOne(824883294);
    }
}
