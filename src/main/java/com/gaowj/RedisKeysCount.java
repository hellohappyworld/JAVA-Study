package com.gaowj;

import redis.clients.jedis.*;

import java.util.*;

public class RedisKeysCount {
    public static void main(String[] args) {
//        int db = 1;
//        Jedis jedis1 = RedisPool.getJedis121_7002(db);
//        Jedis jedis2 = RedisPool.getJedis122_7002(db);
//        Jedis jedis3 = RedisPool.getJedis123_7002(db);
//        Jedis jedis4 = RedisPool.getJedis124_7002(db);
//        Jedis jedis5 = RedisPool.getJedis125_7002(db);
//        Jedis jedis6 = RedisPool.getJedis126_7002(db);
//        Jedis jedis7 = RedisPool.getJedis121_7001(db);
//        Jedis jedis8 = RedisPool.getJedis122_7001(db);
//        Jedis jedis9 = RedisPool.getJedis123_7001(db);
//        Jedis jedis10 = RedisPool.getJedis124_7001(db);
        String key = args[0];
        ShardedJedis filteShardedJedis = JedisClusterUtil.getFilteShardedJedis();
        Boolean exists = filteShardedJedis.exists(key);
        System.out.println(exists);
    }
}
