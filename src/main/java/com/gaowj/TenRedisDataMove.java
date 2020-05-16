package com.gaowj;

import com.gaowj.job.TenRedisDataMoveJob;
import com.gaowj.utils.RedisPool;
import redis.clients.jedis.Jedis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * created by gaowj.
 * created on 2020-05-15.
 * function:
 */
public class TenRedisDataMove {
    public static void main(String[] args) {
        String jedisOption = args[0];
        String singleDb = args[1];
        String tenDb = args[2];
        ExecutorService es = Executors.newFixedThreadPool(60);
        Jedis singleJedis = null;
        switch (jedisOption) {
            case "11":
                singleJedis = RedisPool.getJedisPool11();
                break;
            case "12":
                singleJedis = RedisPool.getJedisPool12();
                break;
            case "13":
                singleJedis = RedisPool.getJedisPool13();
                break;
        }
//        int[] singleDb = {-1, -1, 3, 4, 6, 7, 16, 21, 23, 24, 26, 28, 15, 10, 17, 25};
//        for (int shardingdb = 2; shardingdb <= 15; shardingdb++) {
//            es.submit(new TenRedisDataMoveJob(singleJedis,singleDb[shardingdb], shardingdb));
//        }
        es.submit(new TenRedisDataMoveJob(singleJedis, Integer.parseInt(singleDb), Integer.parseInt(tenDb)));
    }
}
