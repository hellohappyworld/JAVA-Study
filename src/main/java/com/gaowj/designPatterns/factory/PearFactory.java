package com.gaowj.designPatterns.factory;

/**
 * created by gaowj.
 * created on 2020-12-25.
 * function: 抽象工厂模式
 * origin ->
 */
public class PearFactory implements IFactory {
    @Override
    public Fruit create() {
        return new Pear();
    }
}
