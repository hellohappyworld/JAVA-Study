package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-12-17.
 * function: 14. 最长公共前缀(字符串)
 * origin -> https://leetcode-cn.com/problems/longest-common-prefix/solution/hua-jie-suan-fa-14-zui-chang-gong-gong-qian-zhui-b/
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0)
            return "";
        String res = strs[0];

        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            for (; j < res.length() && j < strs[i].length(); j++) {
                if (res.charAt(j) != strs[i].charAt(j))
                    break;
            }
            res = res.substring(0, j);
            if ("".equals(res))
                break;
        }

        return res;
    }
}
