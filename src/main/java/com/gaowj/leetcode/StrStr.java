package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-12-17.
 * function: 28. 实现 strStr()
 * origin -> https://leetcode-cn.com/problems/implement-strstr/solution/shi-xian-strstr-by-leetcode/
 */
public class StrStr {
    public static int strStr(String haystack, String needle) {
        int res = 0;
        if ("".equals(needle))
            return res;

        int j = 0;
        for (int i = 0; i < haystack.length() && j < needle.length(); ) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                if (j == 0)
                    res = i;
                i++;
                j++;
            } else {
                if (j == 0)
                    i++;
                else {
                    i = res + 1;
                    j = 0;
                }
            }
        }
        if (j < needle.length())
            return -1;

        return res;
    }

    public static void main(String[] args) {
        System.out.println(strStr("mississippi", "issip"));
    }
}
