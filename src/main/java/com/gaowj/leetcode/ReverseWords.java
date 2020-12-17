package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-12-17.
 * function: 151. 翻转字符串里的单词(字符串)
 * origin -> https://leetcode-cn.com/problems/reverse-words-in-a-string/
 */
public class ReverseWords {
    public static String reverseWords(String s) {
        String[] split = s.split(" ");
        StringBuffer buffer = new StringBuffer();
        for (int i = split.length - 1; i >= 0; i--)
            if (!"".equals(split[i])) {
                buffer.append(split[i]);
                buffer.append(" ");
            }
        return buffer.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println(reverseWords("the sky is blue"));
    }
}
