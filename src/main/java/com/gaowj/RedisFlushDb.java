package com.gaowj;

import redis.clients.jedis.Jedis;
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
public class RedisFlushDb {
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
    private static JedisPool jedisPool1 = null;
    private static JedisPool jedisPool2 = null;
    private static JedisPool jedisPool3 = null;
    private static JedisPool jedisPool4 = null;
    private static JedisPool jedisPool5 = null;
    private static JedisPool jedisPool6 = null;
    private static JedisPool jedisPool7 = null;
    private static JedisPool jedisPool8 = null;
    private static JedisPool jedisPool9 = null;
    private static JedisPool jedisPool10 = null;
    private static JedisPool jedisPool11 = null;
    private static JedisPool jedisPool12 = null;

    public static final String HOST_121_139 = "10.90.121.139";
    public static final String HOST_122_138 = "10.90.122.138";
    public static final String HOST_123_138 = "10.90.123.138";
    public static final String HOST_124_154 = "10.90.124.154";
    public static final String HOST_125_154 = "10.90.125.154";
    public static final String HOST_126_154 = "10.90.126.154";
    private static int PORT7001 = 7001;
    private static int PORT7002 = 7002;
    public static String PASSWORD_7001_7002 = "ioBA6TJ3HZElEcoB";


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(MAX_ACTIVE);
        config.setMaxIdle(MAX_IDLE);
        config.setMaxWaitMillis(MAX_WAIT);
        config.setTestOnBorrow(TEST_ON_BORROW);
        jedisSinglePool = new JedisPool(config, "10.80.32.158", 80, TIMEOUT, "WxDCxfA8qi");
        jedisPool1 = new JedisPool(config, HOST_121_139, PORT7001, TIMEOUT, PASSWORD_7001_7002);
        jedisPool2 = new JedisPool(config, HOST_122_138, PORT7001, TIMEOUT, PASSWORD_7001_7002);
        jedisPool3 = new JedisPool(config, HOST_123_138, PORT7001, TIMEOUT, PASSWORD_7001_7002);
        jedisPool4 = new JedisPool(config, HOST_124_154, PORT7001, TIMEOUT, PASSWORD_7001_7002);
        jedisPool11 = new JedisPool(config, HOST_125_154, PORT7001, TIMEOUT, PASSWORD_7001_7002);
        jedisPool12 = new JedisPool(config, HOST_126_154, PORT7001, TIMEOUT, PASSWORD_7001_7002);

        jedisPool5 = new JedisPool(config, HOST_121_139, PORT7002, TIMEOUT, PASSWORD_7001_7002);
        jedisPool6 = new JedisPool(config, HOST_122_138, PORT7002, TIMEOUT, PASSWORD_7001_7002);
        jedisPool7 = new JedisPool(config, HOST_123_138, PORT7002, TIMEOUT, PASSWORD_7001_7002);
        jedisPool8 = new JedisPool(config, HOST_124_154, PORT7002, TIMEOUT, PASSWORD_7001_7002);
        jedisPool9 = new JedisPool(config, HOST_125_154, PORT7002, TIMEOUT, PASSWORD_7001_7002);
        jedisPool10 = new JedisPool(config, HOST_126_154, PORT7002, TIMEOUT, PASSWORD_7001_7002);

        ArrayList<Object[]> jedisList = new ArrayList<>();
        jedisList.add(new Object[]{"HOST_121_139_7001", jedisPool1});
        jedisList.add(new Object[]{"HOST_122_138_7001", jedisPool2});
        jedisList.add(new Object[]{"HOST_123_138_7001", jedisPool3});
        jedisList.add(new Object[]{"HOST_124_154_7001", jedisPool4});

//        jedisList.add(new Object[]{"HOST_125_154_7001", jedisPool11});
//        jedisList.add(new Object[]{"HOST_126_154_7001", jedisPool12});

        jedisList.add(new Object[]{"HOST_121_139_7002", jedisPool5});
        jedisList.add(new Object[]{"HOST_122_138_7002", jedisPool6});
        jedisList.add(new Object[]{"HOST_123_138_7002", jedisPool7});
        jedisList.add(new Object[]{"HOST_124_154_7002", jedisPool8});
        jedisList.add(new Object[]{"HOST_125_154_7002", jedisPool9});
        jedisList.add(new Object[]{"HOST_126_154_7002", jedisPool10});

        for (Object[] obj : jedisList) {
            String ip = (String) obj[0];
            JedisPool jedisPool = (JedisPool) obj[1];
            Jedis jedis = jedisPool.getResource();
            jedis.select(1);
            jedis.flushDB();
            jedis.close();
            System.out.println(ip);
        }

    }
}
