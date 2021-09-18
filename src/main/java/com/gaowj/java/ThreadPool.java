package com.gaowj.java;

import java.util.concurrent.*;

/**
 * created by gaowj.
 * created on 2021-08-04.
 * function:
 */
public class ThreadPool {
    private static ExecutorService pool;

    public static void main(String[] args) {
        pool = new ThreadPoolExecutor(2, 4,
                1000,
                TimeUnit.MICROSECONDS,
                new ArrayBlockingQueue<Runnable>(5),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        System.out.println("线程 " + r.hashCode() + " 创建");
                        //线程命名
                        Thread th = new Thread(r, "线程 " + r.hashCode());
                        return th;
                    }
                },
                new ThreadPoolExecutor.CallerRunsPolicy()) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行 " + ((ThreadTask) r).getTaskName());
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行完毕 " + ((ThreadTask) r).getTaskName());
            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
        };

        for (int i = 0; i < 10; i++) {
            pool.execute(new ThreadTask("Task" + i));
        }

        pool.shutdown();
    }
}

class ThreadTask implements Runnable {
    private String taskName;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public ThreadTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        //输出执行线程的名称
        System.out.println("任务名称：" + this.getTaskName() + " 线程名称" + Thread.currentThread().getName());
    }
}
