package com.gaowj.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * created by gaowj.
 * created on 2021-01-18.
 * function:
 * origin ->
 */
public class LetterCombinations {
    public List<String> letterCombinations(String digits) {
        ArrayList<String> res = new ArrayList<>();
        if (digits.length() == 0)
            return res;

        HashMap<String, String> combinations = new HashMap<String, String>() {{
            put("2", "abc");
            put("3", "def");
            put("4", "ghi");
            put("5", "jkl");
            put("6", "mno");
            put("7", "pqrs");
            put("8", "tuv");
            put("9", "wxyz");
        }};

        return res;
    }
}
