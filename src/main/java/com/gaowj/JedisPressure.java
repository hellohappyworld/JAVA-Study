package com.gaowj;

import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class JedisPressure {
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

    public static void main(String[] args) {
        String ip = args[0];
        int port = Integer.parseInt(args[1]);
        String password = args[2];
        String key = args[3];
        int count = Integer.parseInt(args[4]);

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(MAX_ACTIVE);
        config.setMaxIdle(MAX_IDLE);
        config.setMaxWaitMillis(MAX_WAIT);
        config.setTestOnBorrow(TEST_ON_BORROW);

        JedisPool jedis = new JedisPool(config, ip, port, 10000, password);
        Jedis redis = jedis.getResource();
        redis.select(0);

        int setCount = count;
        int getCount = count;
        int delCount = count;

        long setStart = System.currentTimeMillis();
        while (setCount > 0) {
            String v = key + String.valueOf(setCount);
            redis.setex(v, 3600, v);
            setCount--;
        }
        long setEnd = System.currentTimeMillis();
        while (getCount > 0) {
            String v = key + String.valueOf(getCount);
            String s = redis.get(v);
            getCount--;
        }
        long getEnd = System.currentTimeMillis();
        while (delCount > 0) {
            String v = key + String.valueOf(delCount);
            redis.del(v);
            delCount--;
        }
        long delEnd = System.currentTimeMillis();

        System.out.println("The number of executions of each operation is " + String.valueOf(count));
        System.out.println("the set all time is " + String.valueOf(setEnd - setStart));
        System.out.println("the get all time is " + String.valueOf(getEnd - setEnd));
        System.out.println("the del all time is " + String.valueOf(delEnd - getEnd));

        redis.close();
    }
}
