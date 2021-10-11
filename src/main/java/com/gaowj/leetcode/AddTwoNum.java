package com.gaowj.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * created by gaowj.
 * created on 2021-10-11.
 * function: 累加两个数组值
 leetcode 989 号算法题：数组形式的整数加法
 leetcode 66 号算法题：加 1
 leetcode 415 号算法题：字符串相加
 leetcode 67 号算法题：二进制求和
 leetcode 2 号算法题：两数相加
 * origin -> https://leetcode-cn.com/problems/add-to-array-form-of-integer/solution/jian-dan-yi-dong-javacpythonjs-pei-yang-a8ofe/
 */
public class AddTwoNum {
    public List<Integer> addTwoNum(int[] nums1, int[] nums2) {
        ArrayList<Integer> res = new ArrayList<>();
        int carray = 0;
        int i = nums1.length - 1;
        int j = nums2.length - 1;

        while (i >= 0 || j >= 0) {
            int x = i < 0 ? 0 : nums1[i];
            int y = j < 0 ? 0 : nums2[j];

            int sum = x + y + carray;
            res.add(sum % 10);
            carray = sum / 10;

            i--;
            j--;
        }

        if (carray != 0)
            res.add(carray);
        Collections.reverse(res);

        return res;
    }
}
