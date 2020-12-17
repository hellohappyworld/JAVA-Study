package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-12-17.
 * function: 5. 最长回文子串(字符串)
 * origin -> https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zui-chang-hui-wen-zi-chuan-by-leetcode-solution/
 */
public class LongestPalindrome {
    public String longestPalindrome(String s) {
        String res = "";
        int length = s.length();
        boolean[][] dp = new boolean[length][length];

        for (int k = 0; k < length; k++) {
            for (int i = 0; i + k < length; i++) {
                int j = i + k;
                if (k == 0)
                    dp[i][j] = true;
                else if (k == 1)
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                else
                    dp[i][j] = (s.charAt(i) == s.charAt(j)) && dp[i + 1][j - 1];

                if (dp[i][j] && k + 1 > res.length())
                    res = s.substring(i, j + 1);
            }
        }

        return res;
    }
}
