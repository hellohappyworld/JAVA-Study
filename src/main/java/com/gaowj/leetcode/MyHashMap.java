package com.gaowj.leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import static java.util.Objects.hash;

/**
 * created by gaowj.
 * created on 2021-05-11.
 * function: 706. 设计哈希映射
 * origin ->
 */
class MyHashMap {
    private static final int BASE = 769;
    private LinkedList[] linkedLists;

    private class Pair {
        private int key;
        private int value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    /**
     * Initialize your data structure here.
     */
    public MyHashMap() {
        linkedLists = new LinkedList[BASE];
        for (int i = 0; i < BASE; i++) {
            linkedLists[i] = new LinkedList<Pair>();
        }
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        int hash = hash(key);
        LinkedList linkedList = linkedLists[hash];
        Iterator<Pair> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            Pair pair = iterator.next();
            if (key == pair.getKey()) {
                pair.setValue(value);
                return;
            }
        }
        linkedList.offerLast(new Pair(key, value));
    }

    private int hash(int key) {
        return key % BASE;
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        int hash = hash(key);
        LinkedList linkedList = linkedLists[hash];
        Iterator<Pair> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            Pair pair = iterator.next();
            if (key == pair.getKey()) {
                return pair.getValue();
            }
        }
        return -1;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        int hash = hash(key);
        LinkedList linkedList = linkedLists[hash];
        Iterator<Pair> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            Pair pair = iterator.next();
            if (key == pair.getKey()) {
                linkedList.remove(pair);
                return;
            }
        }
    }

}
