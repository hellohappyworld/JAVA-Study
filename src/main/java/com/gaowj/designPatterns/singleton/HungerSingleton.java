package com.gaowj.designPatterns.singleton;

/**
 * created by gaowj.
 * created on 2020-12-25.
 * function: 饿汉式 单例模式
 * origin ->
 */
public class HungerSingleton {
    private static HungerSingleton instance = new HungerSingleton();

    private HungerSingleton() {

    }

    public static HungerSingleton getInstance() {
        return instance;
    }

}
