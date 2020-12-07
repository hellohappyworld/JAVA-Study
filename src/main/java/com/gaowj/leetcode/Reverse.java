package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-12-03.
 * function: 7. 整数反转(数学)
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * origin -> https://leetcode-cn.com/problems/reverse-integer/solution/zheng-shu-fan-zhuan-by-leetcode/
 */
public class Reverse {
    public static int Reverse(int x) {
        int res = 0;
        while (x != 0) {
            int mod = x % 10;
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && mod > 7))
                return 0;
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && mod < -8))
                return 0;
            res = res * 10 + mod;
            x = x / 10;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Reverse(1534236469));
    }
}
