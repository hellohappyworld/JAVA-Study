package com.gaowj.designPatterns.singleton;

/**
 * created by gaowj.
 * created on 2020-12-25.
 * function: 懒汉式 单例模式
 * origin ->
 */
public class LazySingleton {
    private static LazySingleton instance = null;

    private LazySingleton() {

    }

    public static LazySingleton getInstance() {
        if (instance == null)
            instance = new LazySingleton();
        return instance;
    }

}
