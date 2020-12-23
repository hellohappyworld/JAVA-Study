package com.gaowj.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * created by gaowj.
 * created on 2020-12-22.
 * function: 3. 无重复字符的最长子串
 * origin -> https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/hua-dong-chuang-kou-by-powcai/
 */
public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0)
            return 0;
        HashMap<String, Integer> map = new HashMap<>();
        int max = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            String str = String.valueOf(s.charAt(i));
            if (map.containsKey(str)) {
                left = Math.max(left, map.get(str) + 1);
            }
            map.put(str, i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }
}
