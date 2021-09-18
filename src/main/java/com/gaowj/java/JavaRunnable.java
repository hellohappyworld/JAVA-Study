package com.gaowj.java;

/**
 * created by gaowj.
 * created on 2021-07-28.
 * function:
 * origin ->
 */
public class JavaRunnable {
    public static void main(String[] args) {
        System.out.println("主线程ID：" + Thread.currentThread().getName());
        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
    }
}

class MyRunnable implements Runnable {
    public MyRunnable() {
    }

    @Override
    public void run() {
        System.out.println("子线程ID:" + Thread.currentThread().getName());
    }
}
