package com.gaowj.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * created by gaowj.
 * created on 2020-12-01.
 * function: 354. 俄罗斯套娃信封问题(动态规划)
 * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * origin ->
 */
public class MaxEnvelopes {
    public static int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0)
            return 0;

        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] arr1, int[] arr2) {
                if (arr1[0] == arr2[0]) {
                    return arr2[1] - arr1[1];
                } else {
                    return arr1[0] - arr2[0];
                }
            }
        });

        int max = 0;
        int[] dp = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {

        }

        return 1;
    }

    public static void main(String[] args) {
        int[][] envelopes = {{1, 5}, {1, 4}, {1, 2}, {2, 3}};
        maxEnvelopes(envelopes);
    }
}
