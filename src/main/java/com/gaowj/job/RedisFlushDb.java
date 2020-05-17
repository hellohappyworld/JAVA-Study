package com.gaowj.job;

import com.gaowj.common.RedisConst;
import com.gaowj.utils.RedisPool;
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


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String db = args[0];

        Jedis jedis121_7001 = RedisPool.getJedis121_7001(RedisConst.DB_1);
        Jedis jedis122_7001 = RedisPool.getJedis122_7001(RedisConst.DB_1);
        Jedis jedis123_7001 = RedisPool.getJedis123_7001(RedisConst.DB_1);
        Jedis jedis124_7001 = RedisPool.getJedis124_7001(RedisConst.DB_1);
        Jedis jedis121_7002 = RedisPool.getJedis121_7002(RedisConst.DB_1);
        Jedis jedis122_7002 = RedisPool.getJedis122_7002(RedisConst.DB_1);
        Jedis jedis123_7002 = RedisPool.getJedis123_7002(RedisConst.DB_1);
        Jedis jedis124_7002 = RedisPool.getJedis124_7002(RedisConst.DB_1);
        Jedis jedis125_7002 = RedisPool.getJedis125_7002(RedisConst.DB_1);
        Jedis jedis126_7002 = RedisPool.getJedis126_7002(RedisConst.DB_1);

        ArrayList<Jedis> list = new ArrayList<>();
        list.add(jedis121_7001);
        list.add(jedis122_7001);
        list.add(jedis123_7001);
        list.add(jedis124_7001);
        list.add(jedis121_7002);
        list.add(jedis122_7002);
        list.add(jedis123_7002);
        list.add(jedis124_7002);
        list.add(jedis125_7002);
        list.add(jedis126_7002);

        for (Jedis jedis : list) {
            jedis.select(Integer.parseInt(db));
            jedis.flushDB();
            jedis.close();
            System.out.println("ths db is " + db);
        }

    }
}
