package com.gaowj.leetcode;

import java.io.Serializable;
import java.util.Comparator;

/**
 * created by gaowj.
 * created on 2021-04-22.
 * function:
 * origin ->
 */
public class Class1 implements Comparable, Comparator, Serializable {
    public Class1() {
        System.out.println("A");
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }
}
