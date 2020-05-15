package com.gaowj;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * created by gaowj.
 * created on 2020-05-15.
 * function:
 */
public class RedisDataMoveUtil4 {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(60);
        int[] singleDb = {-1, -1, 3, 4, 6, 7, 16, 21, 23, 24, 26, 28, 15, 10, 17, 25};
        for (int shardingdb = 2; shardingdb <= 15; shardingdb++) {
            es.submit(new JedisDataMoveJob4(singleDb[shardingdb], shardingdb));
        }
    }
}
