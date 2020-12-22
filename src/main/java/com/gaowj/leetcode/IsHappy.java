package com.gaowj.leetcode;

import java.util.HashSet;

/**
 * created by gaowj.
 * created on 2020-12-21.
 * function: 202. 快乐数(数学) 哈希表
 * origin -> https://leetcode-cn.com/problems/happy-number/
 */
public class IsHappy {
    public boolean isHappy(int n) {
        HashSet<Integer> isContains = new HashSet<>();

        while (n != 1 && !isContains.contains(n)) {
            isContains.add(n);

            int tmp = 0;
            while (n > 0) {
                int d = n % 10;
                n = n / 10;
                tmp += d * d;
            }

            n = tmp;
        }

        return n == 1;
    }
}
