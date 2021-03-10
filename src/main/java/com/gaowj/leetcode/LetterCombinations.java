package com.gaowj.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by gaowj.
 * created on 2021-02-19.
 * function: 17. 电话号码的字母组合(字符串)
 * origin -> https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/solution/dian-hua-hao-ma-de-zi-mu-zu-he-by-leetcode-solutio/
 */
public class LetterCombinations {
    public static List<String> letterCombinations(String digits) {
        ArrayList<String> res = new ArrayList<>();
        if (digits.length() == 0)
            return res;

        HashMap<String, String> phoneMap = new HashMap<String, String>() {{
            put("2", "abc");
            put("3", "def");
            put("4", "ghi");
            put("5", "jkl");
            put("6", "mno");
            put("7", "pqrs");
            put("8", "tuv");
            put("9", "wxyz");
        }};

        backtrack(res, phoneMap, digits, 0, new StringBuffer());

        return res;
    }

    private static void backtrack(ArrayList<String> res, HashMap<String, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (index == digits.length())
            res.add(combination.toString());
        else {
            char phoneChar = digits.charAt(index);
            String phoneString = phoneMap.get(String.valueOf(phoneChar));
            for (int i = 0; i < phoneString.length(); i++) {
                char c = phoneString.charAt(i);
                combination.append(c);
                backtrack(res, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }
}
