package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2021-02-18.
 * function: 363. 矩形区域不超过 K 的最大数值和(DP)
 * origin -> https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k/solution/javacong-bao-li-kai-shi-you-hua-pei-tu-pei-zhu-shi/
 */
public class MaxSumSubmatrix {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int max = Integer.MIN_VALUE;
        int row = matrix.length;
        int col = matrix[0].length;

        for (int i1 = 1; i1 <= row; i1++) {
            for (int j1 = 1; j1 <= col; j1++) {
                int[][] dp = new int[row + 1][col + 1];
                dp[i1][j1] = matrix[i1 - 1][j1 - 1];
                for (int i2 = i1; i2 <= row; i2++) {
                    for (int j2 = j1; j2 <= col; j2++) {
                        dp[i2][j2] = dp[i2 - 1][j2] + dp[i2][j2 - 1] - dp[i2 - 1][j2 - 1] + matrix[i2 - 1][j2 - 1];
                        if (dp[i2][j2] <= k && dp[i2][j2] > max)
                            max = dp[i2][j2];
                    }
                }
            }
        }

        return max;
    }
}
