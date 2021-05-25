package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2021-05-07.
 * function: 69. x 的平方根
 * origin ->
 */
public class MySqrt {
    public static int mySqrt(int x) {
        int left = 0, rigth = x, res = -1;
        while (left <= rigth) {
            int mid = (left + rigth) / 2;
            if (mid * mid <= x) {
                res = mid;
                left = mid + 1;
            } else {
                rigth = mid - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(2147395599));
    }
}
