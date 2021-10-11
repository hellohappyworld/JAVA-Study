package com.gaowj.leetcode;


import java.util.Collections;

/**
 * created by gaowj.
 * created on 2021-10-11.
 * function: 415. 字符串相加
 * origin -> https://leetcode-cn.com/problems/add-strings/solution/jian-dan-yi-dong-javacpythonjs-pei-yang-4yj7t/
 */
public class AddStrings {
    public String addStrings(String num1, String num2) {
        StringBuilder res = new StringBuilder();
        int carray = 0;
        int i = num1.length() - 1;
        int j = num2.length() - 1;

        while (i >= 0 || j >= 0) {
            int x = i < 0 ? 0 : num1.charAt(i) - '0';
            int y = j < 0 ? 0 : num2.charAt(j) - '0';

            int sum = x + y + carray;
            res.append(sum % 10);
            carray = sum / 10;

            i--;
            j--;
        }

        if (carray != 0)
            res.append(carray);

        return res.reverse().toString();
    }
}
