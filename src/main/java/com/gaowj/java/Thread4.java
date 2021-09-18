package com.gaowj.java;

/**
 * created by gaowj.
 * created on 2021-08-02.
 * function:
 * origin ->
 */
public class Thread4 extends Thread {
    public Thread4(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(getName() + " " + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //启动子线程
        new Thread4("new thread").start();
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                Thread4 th = new Thread4("joined thread");
                th.start();
//                th.join();
            }
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}
