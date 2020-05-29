package com.gaowj;

import com.gaowj.job.SingleRedisDataMoveJob;
import com.gaowj.utils.RedisPool;
import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * created by gaowj.
 * created on 2020-05-15.
 * function:
 */
public class SingleRedisDataMove {
    public static void main(String[] args) {
        String fromHost = args[0];
        int formPost = Integer.parseInt(args[1]);
        String fromMessage = args[2];
        String fromDb = args[3];

        Jedis fromJedis = new Jedis(fromHost, formPost);
        fromJedis.auth(fromMessage);
        fromJedis.select(Integer.parseInt(fromDb));

        String toHost = args[4];
        int toPost = Integer.parseInt(args[5]);
        String toMessage = args[6];
        String toDb = args[7];

        Jedis toJedis = new Jedis(toHost, toPost);
        toJedis.auth(toMessage);
        toJedis.select(Integer.parseInt(toDb));

        ExecutorService es = Executors.newFixedThreadPool(2);
        es.submit(new SingleRedisDataMoveJob(fromJedis, fromDb, toJedis, toDb));

    }
}
