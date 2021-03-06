package com.gaowj.designPatterns.factory;

import org.junit.Test;

/**
 * created by gaowj.
 * created on 2020-12-25.
 * function: 抽象工厂模式
 * origin ->
 */
public class User {
    @Test
    public void eat() {
        IFactory appleFactory = new AppleFactory();
        IFactory pearFactory = new PearFactory();

        appleFactory.create().eat();
        pearFactory.create().eat();
    }
}
