package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-11-18.
 * function: 双指针
 * 反转字符串中的元音字母
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 * origin -> https://leetcode-cn.com/leetbook/read/all-about-array/x93lce/
 */
public class ReverseVowels {

    public static String reverseVowels(String str) {
        int low = 0;
        int high = str.length() - 1;
        StringBuilder strBuilder = new StringBuilder(str);
        while (low < high) {
            if (isVowel(strBuilder.charAt(low)) && isVowel(strBuilder.charAt(high))) {
                char lowChar = strBuilder.charAt(low);
                char highChar = strBuilder.charAt(high);
                strBuilder.setCharAt(low, highChar);
                strBuilder.setCharAt(high, lowChar);
                low++;
                high--;
            } else {
                if (!isVowel(strBuilder.charAt(low)))
                    low++;
                if (!isVowel(strBuilder.charAt(high)))
                    high--;
            }
        }
        return strBuilder.toString();
    }

    public static boolean isVowel(char c) {
        switch (c) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
            case 'A':
            case 'E':
            case 'I':
            case 'O':
            case 'U':
                return true;
            default:
                return false;
        }
    }

    public static void main(String[] args) {
        String str = "leetcode";
        System.out.println(reverseVowels(str));
    }
}
