package com.gaowj.leetcode;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * created by gaowj.
 * created on 2021-04-26.
 * function: 20. 有效的括号
 * origin ->
 */
public class IsValid {
    public boolean isValid(String s) {
        if (s.length() % 2 == 1)
            return false;

        HashMap<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};

        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (pairs.containsKey(c)) {
                if (stack.isEmpty() || stack.peek() != pairs.get(c))
                    return false;
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }
}
