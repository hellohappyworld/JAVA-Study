package com.gaowj.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * created by gaowj.
 * created on 2020-11-24.
 * function: 56. 合并区间 （二维数组）
 * 给出一个区间的集合，请合并所有重叠的区间。
 * origin -> https://leetcode-cn.com/problems/merge-intervals/
 */
public class MergeArray {
    public static int[][] merge(int[][] intervals) {
        //数组排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        ArrayList<int[]> merged = new ArrayList<>();
        merged.add(new int[]{intervals[0][0], intervals[0][1]});
        for (int i = 1; i < intervals.length; i++) {
            int[] arr = merged.get(merged.size() - 1);
            if (intervals[i][0] > arr[1])
                merged.add(new int[]{intervals[i][0], intervals[i][1]});
            else
                arr[1] = Math.max(arr[1], intervals[i][1]);
        }

        return merged.toArray(new int[merged.size()][2]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 9}, {2, 5}, {19, 20}, {10, 11}, {12, 20}, {0, 3}, {0, 1}, {0, 2}};
        int[][] merge = merge(intervals);
        for (int i = 0; i < merge.length; i++) {
            System.out.println(String.valueOf(merge[i][0]) + " " + String.valueOf(merge[i][1]));
        }
    }
}
