package com.gaowj.leetcode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * created by gaowj.
 * created on 2021-05-11.
 * function:
 * origin ->
 */
class MyHashSet {
    private int BASE = 769;
    private LinkedList[] linkedLists;

    /**
     * Initialize your data structure here.
     */
    public MyHashSet() {
        linkedLists = new LinkedList[BASE];
        for (int i = 0; i < BASE; i++) {
            linkedLists[i] = new LinkedList<Integer>();
        }
    }

    public void add(int key) {
        int hash = hash(key);
        LinkedList linkedList = linkedLists[hash];
        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            Integer data = iterator.next();
            if (data == key)
                return;
        }
        linkedList.offerLast(key);
    }

    private int hash(int key) {
        return key % BASE;
    }

    public void remove(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = linkedLists[h].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                linkedLists[h].remove(element);
                return;
            }
        }
    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key) {
        int hash = hash(key);
        LinkedList linkedList = linkedLists[hash];
        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            Integer data = iterator.next();
            if (data == key)
                return true;
        }
        return false;
    }
}
