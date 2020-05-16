package com.gaowj;

import com.gaowj.job.ShardingDataMoveJob;
import com.gaowj.job.TenRedisDataMoveJob;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShardingDataMove {
    public static void main(String[] args) {
        String singleDb = args[0];
        ExecutorService es = Executors.newFixedThreadPool(2);
        es.submit(new ShardingDataMoveJob(Integer.parseInt(singleDb)));
    }
}
