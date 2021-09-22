package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2021-09-22.
 * function: 304. 二维区域和检索 - 矩阵不可变
 * origin -> https://www.bilibili.com/video/BV1Gx411E7bU?from=search&seid=10577136339519259961&spm_id_from=333.337.0.0
 */
public class NumMatrix {
    private int[][] resum;

    public NumMatrix(int[][] matrix) {
        if (matrix.length != 0 && matrix[0].length != 0) {
            int row = matrix.length;
            int col = matrix[0].length;
            resum = new int[row + 1][col + 1];
            for (int i = 1; i <= row; i++) {
                for (int j = 1; j <= col; j++) {
                    resum[i][j] = matrix[i - 1][j - 1] + resum[i - 1][j] + resum[i][j - 1] - resum[i - 1][j - 1];
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return resum[row2 + 1][col2 + 1] - resum[row2 + 1][col1]
                - resum[row1][col2 + 1] + resum[row1][col1];
    }
}
