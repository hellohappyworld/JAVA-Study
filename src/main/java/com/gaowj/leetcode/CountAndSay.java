package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2021-01-04.
 * function: 38. 外观数列(字符串)
 * origin -> https://leetcode-cn.com/problems/count-and-say/
 */
public class CountAndSay {
    public static String countAndSay(int n) {
        if (n < 1) {
            return null;
        } else {
            String res = "1";
            for (int i = 2; i <= n; i++) {
                String tmpStr = "";
                String tmpC = "";
                int sum = 0;
                for (int j = 0; j < res.length(); j++) {
                    String c = String.valueOf(res.charAt(j));
                    if ("".equals(tmpC)) {
                        tmpC = c;
                        sum = 1;
                    } else {
                        if (c.equals(tmpC))
                            sum++;
                        else {
                            tmpStr = tmpStr + String.valueOf(sum) + tmpC;
                            tmpC = c;
                            sum = 1;
                        }
                    }
                }
                res = tmpStr + String.valueOf(sum) + tmpC;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        System.out.println(countAndSay(1));
    }
}
