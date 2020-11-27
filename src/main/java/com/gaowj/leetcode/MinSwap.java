package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-11-26.
 * function: 801. 使序列递增的最小交换次数 （动态规划）
 * 我们有两个长度相等且不为空的整型数组 A 和 B 。
 * 我们可以交换 A[i] 和 B[i] 的元素。注意这两个元素在各自的序列中应该处于相同的位置。
 * 在交换过一些元素之后，数组 A 和 B 都应该是严格递增的（数组严格递增的条件仅为A[0] < A[1] < A[2] < ... < A[A.length - 1]）。
 * 给定数组 A 和 B ，请返回使得两个数组均保持严格递增状态的最小交换次数。假设给定的输入总是有效的。
 * <p>
 * origin -> 大佬级解析：https://leetcode-cn.com/problems/minimum-swaps-to-make-sequences-increasing/solution/zhuang-tai-zhuan-yi-fang-cheng-zen-yao-tui-dao-ke-/
 */
public class MinSwap {
    public static int minSwap(int[] A, int[] B) {
        int[] dp = {0, 1}; // dp[0]代表当前A[i] B[i]不换的总交换次数 dp[1]代表当前A[i] B[i]互换的总交换次数
        for (int i = 1; i < A.length; i++) {
            int a1 = A[i - 1];
            int a2 = A[i];
            int b1 = B[i - 1];
            int b2 = B[i];
            if (a1 >= a2 || b1 >= b2) {
                int tmp = dp[0];
                dp[0] = dp[1];
                dp[1] = tmp + 1;
            } else if (a1 >= b2 || b1 >= a2) {
                dp[0] = dp[0];
                dp[1] = dp[1] + 1;
            } else {
                int min = Math.min(dp[0], dp[1]);
                dp[0] = min;
                dp[1] = min + 1;
            }
        }
        return Math.min(dp[0], dp[1]);
    }

    public static void main(String[] args) {
        int[] A = {1, 3, 5, 4};
        int[] B = {1, 2, 3, 7};
        System.out.println(minSwap(A, B));
    }
}
