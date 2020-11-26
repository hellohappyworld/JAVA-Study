package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-11-25.
 * function: 面试题 01.07. 旋转矩阵
 * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
 * origin -> https://leetcode-cn.com/problems/rotate-matrix-lcci/
 */
public class Rotate {
    public static void rotate(int[][] matrix) {
        int row = matrix.length;
        int column = matrix[0].length;
        //水平轴翻转
        for (int i = 0; i < row / 2; i++) {
            for (int j = 0; j < column; j++) {
                int swap = matrix[i][j];
                matrix[i][j] = matrix[row - 1 - i][j];
                matrix[row - 1 - i][j] = swap;
            }
        }
        //对角线旋转
        for (int i = 0; i < row; i++) {
            for (int j = i; j < column; j++) {
                int swap = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = swap;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotate(matrix);
        for (int i = 0; i < matrix.length; i++)
            System.out.println(matrix[i][0] + " " + matrix[i][1] + " " + matrix[i][2]);
    }
}
