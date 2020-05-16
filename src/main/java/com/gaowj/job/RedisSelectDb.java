package com.gaowj.job;

import redis.clients.jedis.Jedis;

public class RedisSelectDb {
    public static Jedis select(Jedis jedis, int db) {
        jedis.select(db);
        return jedis;
    }
}
