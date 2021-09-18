package com.gaowj.java;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.*;

/**
 * created by gaowj.
 * created on 2021-07-28.
 * function:
 * origin ->
 */
public class JavaCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("主线程运行开始");
        Date date1 = new Date();
        int taskSize = 5;

        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        //创建多个有返回值的任务
        ArrayList<Future> futures = new ArrayList<>();
        for (int i = 0; i < taskSize; i++) {
            MyCallable callable = new MyCallable(i + " ");
            //执行任务并获取Future对象
            Future<Object> future = pool.submit(callable);
            futures.add(future);
        }
        //关闭线程池
        pool.shutdown();

        //获取所有并发任务的运行结果
        for (Future future : futures)
            //打印线程返回结果
            System.out.println(">>>" + future.get().toString());

        Date date2 = new Date();
        System.out.println("主线程运行结束，耗时" + (date2.getTime() - date1.getTime()) + "毫秒");
    }
}

class MyCallable implements Callable<Object> {
    private String taskNum;

    public MyCallable(String taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public Object call() throws Exception {
        System.out.println(">>>" + taskNum + "任务启动了");
        Date dateTmp1 = new Date();
        Thread.sleep(1000);
        Date dateTmp2 = new Date();
        long time = dateTmp2.getTime() - dateTmp1.getTime();
        System.out.println(">>>" + taskNum + "任务终止了");
        return taskNum + "任务运行结束，当前任务运行时间了" + time + "毫秒";
    }
}
