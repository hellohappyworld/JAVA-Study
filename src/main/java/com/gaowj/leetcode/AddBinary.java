package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-12-11.
 * function: 67. 二进制求和(数学)
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * origin -> https://leetcode-cn.com/problems/add-binary/solution/hua-jie-suan-fa-67-er-jin-zhi-qiu-he-by-guanpengch/
 */
public class AddBinary {
    public static String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();
        int carry = 0; //进位
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int a1 = i >= 0 ? a.charAt(i) - '0' : 0;
            int b1 = j >= 0 ? b.charAt(j) - '0' : 0;
            int sum = carry + a1 + b1;
            res.append(sum % 2);
            carry = sum / 2;
        }
        res.append(carry == 1 ? carry : "");
        return res.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(addBinary("11", "1"));
    }
}
