package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-12-29.
 * function: 面试题 17.24. 最大子矩阵(DP)
 * origin -> https://leetcode-cn.com/problems/max-submatrix-lcci/solution/zhe-yao-cong-zui-da-zi-xu-he-shuo-qi-you-jian-dao-/
 */
public class GetMaxMatrix {
    public int[] getMaxMatrix(int[][] matrix) {
        int[] res = new int[4];
        int row = matrix.length;
        int columns = matrix[0].length;
        int[] columnSumArr = new int[columns];
        int bestr1 = 0;
        int bestc1 = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < row; i++) {
            for (int k = 0; k < columns; k++)
                columnSumArr[k] = 0;
            for (int j = i; j < row; j++) {
                int sum = 0;
                for (int c = 0; c < columns; c++) {
                    columnSumArr[c] += matrix[j][c];
                    if (sum > 0)
                        sum += columnSumArr[c];
                    else {
                        sum = columnSumArr[c];
                        bestr1 = i;
                        bestc1 = c;
                    }
                    if (sum > maxSum) {
                        maxSum = sum;
                        res[0] = bestr1;
                        res[1] = bestc1;
                        res[2] = j;
                        res[3] = c;
                    }
                }
            }
        }
        return res;
    }
}
