package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2020-12-21.
 * function: 458. 可怜的小猪（数学）
 * origin -> https://leetcode-cn.com/problems/poor-pigs/solution/hua-jie-suan-fa-458-ke-lian-de-xiao-zhu-by-guanpen/
 */
public class PoorPigs {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int times = minutesToTest / minutesToDie;
        int base = times + 1;
        double tmp = Math.log(buckets) / Math.log(base);
        int res = (int) Math.ceil(tmp);
        return res;
    }
}
