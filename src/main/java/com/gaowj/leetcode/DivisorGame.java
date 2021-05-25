package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2021-03-31.
 * function: 1025. 除数博弈 （DP）
 * origin -> https://leetcode-cn.com/problems/divisor-game/solution/chu-shu-bo-yi-by-leetcode-solution/
 */
public class DivisorGame {
    public static boolean divisorGame(int n) {
        boolean[] dp = new boolean[n + 2];

        dp[1] = false;
        dp[2] = true;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (i % j == 0 && !dp[i - j]) {
                    dp[i] = true;
                    break;
                } else {
                    dp[i] = false;
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(divisorGame(2));
    }
}
