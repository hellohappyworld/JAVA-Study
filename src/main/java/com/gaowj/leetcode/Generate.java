package com.gaowj.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * created by gaowj.
 * created on 2020-12-22.
 * function: 杨辉三角
 * origin ->
 */
public class Generate {
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0)
            return res;

        for (int i = 0; i < numRows; i++) {
            ArrayList<Integer> tmp = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    tmp.add(1);
                } else {
                    tmp.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
                }
            }
            res.add(tmp);
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(generate(5));
    }
}
