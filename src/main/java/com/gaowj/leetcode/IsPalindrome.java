package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-11-18.
 * function:
 * 验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * origin -> https://leetcode-cn.com/leetbook/read/all-about-array/x9tqjc/
 */
public class IsPalindrome {

    public static boolean isPalindrome(String str) {
        int low = 0;
        int high = str.length() - 1;
        while (low <= high) {
            char lowChar = str.charAt(low);
            char highChar = str.charAt(high);
            if (!(Character.isLetter(lowChar) || Character.isDigit(lowChar))) {
                low++;
                continue;
            }
            if (!(Character.isLetter(highChar) || Character.isDigit(highChar))) {
                high--;
                continue;
            }
            String lowStr;
            String highStr;
            if (!Character.isUpperCase(lowChar))
                lowStr = String.valueOf(lowChar).toUpperCase();
            else
                lowStr = String.valueOf(lowChar);

            if (!Character.isUpperCase(highChar))
                highStr = String.valueOf(highChar).toUpperCase();
            else
                highStr = String.valueOf(highChar);

            if (!lowStr.equals(highStr))
                return false;

            low++;
            high--;
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "121";
        System.out.println(isPalindrome(str));
    }
}
