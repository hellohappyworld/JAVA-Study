package com.gaowj;

import com.gaowj.job.TenRedisDataMoveJob;
import com.gaowj.utils.RedisPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * created by gaowj.
 * created on 2020-05-15.
 * function:
 */
public class TenRedisDataMove {
    static Logger logger = LoggerFactory.getLogger(TenRedisDataMove.class);

    public static void main(String[] args) {
        String jedisOption = args[0];
        String singleDb = args[1];
        String tendb = args[2];
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

        ExecutorService es = Executors.newFixedThreadPool(3);

        System.out.println("------主线程-----" + singleDb + "---" + tendb);
        es.submit(new TenRedisDataMoveJob(singleJedis, Integer.parseInt(singleDb), Integer.parseInt(tendb)));
    }
}
