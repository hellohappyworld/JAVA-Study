package com.gaowj.leetcode;

import com.sun.deploy.util.StringUtils;

import java.util.Arrays;

/**
 * created by gaowj.
 * created on 2021-01-04.
 * function: 6. Z 字形变换(字符串)
 * origin ->  https://leetcode-cn.com/problems/zigzag-conversion/solution/zzi-xing-bian-huan-by-jyd/
 */
public class Convert {
    public static String convert(String s, int numRows) {
        String[] arr = new String[numRows];
        for (int i = 0; i < numRows; i++)
            arr[i] = "";
        int flag = 1;
        int index = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            index += flag;
            arr[index] = arr[index] + c;
            if (index == numRows - 1)
                flag = -1;
            if (index == 0)
                flag = 1;
            if (index == numRows - 1 && index == 0)
                flag = 0;
        }

        String res = "";
        for (int i = 0; i < numRows; i++)
            res += arr[i].trim();

        return res;
    }

    public static void main(String[] args) {
        System.out.println(convert("ab", 1));
    }
}
