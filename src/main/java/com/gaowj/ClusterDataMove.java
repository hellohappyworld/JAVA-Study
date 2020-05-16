package com.gaowj;

import com.gaowj.job.ClusterDataMoveJob;
import com.gaowj.job.ShardingDataMoveJob;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClusterDataMove {
    public static void main(String[] args) {
        String singleDb = args[0];
        ExecutorService es = Executors.newFixedThreadPool(2);
        es.submit(new ClusterDataMoveJob(Integer.parseInt(singleDb)));
    }
}
