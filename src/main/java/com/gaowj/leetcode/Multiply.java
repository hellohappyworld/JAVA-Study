package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2021-03-02.
 * function: 43. 字符串相乘(数学)
 * origin -> https://leetcode-cn.com/problems/multiply-strings/solution/zi-fu-chuan-xiang-cheng-by-leetcode-solution/
 */
public class Multiply {
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2))
            return "0";
        String res = "0";

        int n1 = num1.length();
        int n2 = num2.length();
        for (int i = n2 - 1; i >= 0; i--) {
            StringBuffer buffer = new StringBuffer();
            for (int j = n2 - 1; j > i; j--)
                buffer.append("0");
            int add = 0;
            int num2Char = num2.charAt(i) - '0';
            for (int j = n1 - 1; j >= 0; j--) {
                int num1Char = num1.charAt(j) - '0';
                int multi = num1Char * num2Char + add;
                int mold = multi % 10;
                add = multi / 10;
                buffer.append(mold);
            }
            if (add != 0)
                buffer.append(add);
            res = addString(res, buffer.reverse().toString());
        }

        return res;
    }

    private String addString(String num1, String num2) {
        int n1 = num1.length();
        int n2 = num2.length();
        int add = 0;
        StringBuffer res = new StringBuffer();
        for (int i = n1 - 1, j = n2 - 1; i >= 0 || j >= 0 || add != 0; i--, j--) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            res.append(result % 10);
            add = result / 10;
        }
        return res.reverse().toString();
    }
}
