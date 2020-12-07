package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-12-03.
 * function: 13. 罗马数字转整数(数学)
 * origin -> https://leetcode-cn.com/problems/roman-to-integer/
 */
public class RomanToInt {
    public static int romanToInt(String s) {
        int length = s.length();
        int res = 0;
        for (int i = 0; i < length; i++) {
            if (i + 1 < length) {
                if ("I".equals(String.valueOf(s.charAt(i))) && "V".equals(String.valueOf(s.charAt(i + 1)))) {
                    res += 4;
                    i++;
                } else if ("I".equals(String.valueOf(s.charAt(i))) && "X".equals(String.valueOf(s.charAt(i + 1)))) {
                    res += 9;
                    i++;
                } else if ("X".equals(String.valueOf(s.charAt(i))) && "L".equals(String.valueOf(s.charAt(i + 1)))) {
                    res += 40;
                    i++;
                } else if ("X".equals(String.valueOf(s.charAt(i))) && "C".equals(String.valueOf(s.charAt(i + 1)))) {
                    res += 90;
                    i++;
                } else if ("C".equals(String.valueOf(s.charAt(i))) && "D".equals(String.valueOf(s.charAt(i + 1)))) {
                    res += 400;
                    i++;
                } else if ("C".equals(String.valueOf(s.charAt(i))) && "M".equals(String.valueOf(s.charAt(i + 1)))) {
                    res += 900;
                    i++;
                } else {
                    res += getInt(s.charAt(i));
                }
            } else {
                res += getInt(s.charAt(i));
            }
        }

        return res;
    }

    public static int getInt(char s) {
        switch (s) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
    }
}
