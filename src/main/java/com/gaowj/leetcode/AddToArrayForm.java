package com.gaowj.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * created by gaowj.
 * created on 2021-10-11.
 * function: 989. 数组形式的整数加法
 * origin -> https://leetcode-cn.com/problems/add-to-array-form-of-integer/solution/jian-dan-yi-dong-javacpythonjs-pei-yang-a8ofe/
 */
public class AddToArrayForm {
    public List<Integer> addToArrayForm(int[] num, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        int carray = 0;
        int i = num.length - 1;

        while (i >= 0 || k != 0) {
            int x = i < 0 ? 0 : num[i];
            int y = k % 10;

            int sum = x + y + carray;
            res.add(sum % 10);
            carray = sum / 10;

            i--;
            k = k / 10;
        }

        if (carray != 0)
            res.add(carray);
        Collections.reverse(res);

        return res;
    }
}
