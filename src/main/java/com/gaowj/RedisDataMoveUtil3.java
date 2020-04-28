package com.gaowj;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * created on 2020-03-29
 * gaowj
 * redis数据转移
 */
public class RedisDataMoveUtil3 {
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

    private static JedisPool jedisSingle1 = null;
    private static JedisPool jedisSingle2 = null;


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String host1 = args[0];
        int post1 = Integer.parseInt(args[1]);
        String password1 = args[2];
        String host2 = args[3];
        int post2 = Integer.parseInt(args[4]);
        String password2 = args[5];
        int db = Integer.parseInt(args[6]);

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(MAX_ACTIVE);
        config.setMaxIdle(MAX_IDLE);
        config.setMaxWaitMillis(MAX_WAIT);
        config.setTestOnBorrow(TEST_ON_BORROW);
        jedisSingle1 = new JedisPool(config, host1, post1, TIMEOUT, password1);
        jedisSingle2 = new JedisPool(config, host2, post2, TIMEOUT, password2);
        ExecutorService es = Executors.newFixedThreadPool(60);
        es.submit(new JedisDataMoveJob3(jedisSingle1, jedisSingle2, db));
    }
}
