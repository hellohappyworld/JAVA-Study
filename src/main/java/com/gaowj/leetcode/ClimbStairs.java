package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-11-19.
 * function: 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 * origin -> https://leetcode-cn.com/problems/climbing-stairs/solution/pa-lou-ti-by-leetcode-solution/
 */
public class ClimbStairs {
    //循环式
    public static int climbStairs(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    //递归式
    public static int climbStairsRecursion(int n) {
        if (n == 0 || n == 1)
            return 1;
        int i = climbStairsRecursion(n - 1);
        int j = climbStairsRecursion(n - 2);
        return i + j;
    }

    public static void main(String[] args) {
        int n = 45;
//        System.out.println(climbStairs(n));
        System.out.println(climbStairsRecursion(n));
    }
}