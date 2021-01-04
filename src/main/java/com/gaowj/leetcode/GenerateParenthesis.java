package com.gaowj.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * created by gaowj.
 * created on 2021-01-04.
 * function: 22. 括号生成(字符串)(回溯算法深度优先遍历)
 * origin -> https://leetcode-cn.com/problems/generate-parentheses/solution/hui-su-suan-fa-by-liweiwei1419/
 */
public class GenerateParenthesis {
    public List<String> generateParenthesis(int n) {
        ArrayList<String> res = new ArrayList<>();

        if (n == 0)
            return res;
        String curStr = "";
        dfs(curStr, n, n, res);

        return res;
    }

    public static void dfs(String curStr, int left, int right, ArrayList res) {
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }

        if (left > right)
            return;

        if (left > 0)
            dfs(curStr + "(", left - 1, right, res);

        if (right > 0)
            dfs(curStr + ")", left, right - 1, res);
    }
}
