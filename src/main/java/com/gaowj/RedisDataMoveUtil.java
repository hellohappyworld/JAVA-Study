package com.gaowj;

import redis.clients.jedis.*;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * created on 2020-03-29
 * gaowj
 * redis数据转移
 */
public class RedisDataMoveUtil {
    //连接实例的最大连接数
    private static int MAX_ACTIVE = 1024;
    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 200;
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException
    private static int MAX_WAIT = 10000;
    //连接超时的时间　　
    private static int TIMEOUT = 10000;
    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;

    private static JedisPool jedisSinglePool = null;
    private static JedisPool jedisCLusterPool = null;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int dbNow = Integer.parseInt(args[0]);

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(MAX_ACTIVE);
        config.setMaxIdle(MAX_IDLE);
        config.setMaxWaitMillis(MAX_WAIT);
        config.setTestOnBorrow(TEST_ON_BORROW);
        jedisSinglePool = new JedisPool(config, "10.80.32.158", 80, TIMEOUT, "WxDCxfA8qi");
        jedisCLusterPool = new JedisPool(config, "10.66.224.4", 6379, TIMEOUT, "6tSSAsA0T1eDjMKV");
//        jedisCLusterPool = new JedisPool(config, "10.90.126.154", 80, TIMEOUT, "WxDCxfA8qi");

        ExecutorService es = Executors.newFixedThreadPool(60);
        ArrayList<Future> futures = new ArrayList<>();
        if (dbNow >= 0) {
            futures.add(es.submit(new JedisDataMoveJob(jedisSinglePool, jedisCLusterPool, dbNow)));
        } else {
            for (int db = 0; db <= 49; db++) {
                futures.add(es.submit(new JedisDataMoveJob(jedisSinglePool, jedisCLusterPool, db)));
            }
        }


        for (Future future : futures) {
            String s = String.valueOf(future.get());
            System.out.println(s);
        }

        jedisSinglePool.close();
        jedisCLusterPool.close();
    }
}
