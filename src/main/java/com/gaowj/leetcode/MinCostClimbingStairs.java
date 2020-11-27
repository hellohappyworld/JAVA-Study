package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-11-26.
 * function: 746. 使用最小花费爬楼梯(动态规划)
 * 数组的每个索引作为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
 * <p>
 * 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
 * <p>
 * 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
 * origin -> https://leetcode-cn.com/problems/min-cost-climbing-stairs/
 */
public class MinCostClimbingStairs {
    public static int minCostClimbingStairs(int[] cost, int n) {
        if (n == 0 || n == 1)
            return cost[n];
        return Math.min(minCostClimbingStairs(cost, n - 1), minCostClimbingStairs(cost, n - 2)) + cost[n];
    }


    public static void main(String[] args) {
        int[] cost = {10, 15, 20};
        System.out.println(minCostClimbingStairs(cost, cost.length - 1));
    }
}
