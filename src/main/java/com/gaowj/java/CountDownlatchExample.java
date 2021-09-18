package com.gaowj.java;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * created by gaowj.
 * created on 2021-08-09.
 * function:
 * origin ->
 */
public class CountDownlatchExample {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        Service1 service = new Service1(latch);
        Runnable task = () -> service.exec();

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(task);
            thread.start();
        }

        System.out.println("main thread await.");
        latch.await();
        System.out.println("main thread finishes await.");
    }
}

class Service1 {
    private CountDownLatch latch;

    public Service1(CountDownLatch latch) {
        this.latch = latch;
    }

    public void exec() {
        try {
            System.out.println(Thread.currentThread().getName() + " execute task.");
            sleep(2);
            System.out.println(Thread.currentThread().getName() + " finished task.");
        } finally {
            latch.countDown();
        }
    }

    private void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}