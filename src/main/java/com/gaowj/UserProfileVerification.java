package com.gaowj;

import com.gaowj.common.RedisConst;
import com.gaowj.utils.RedisPool;
import com.gaowj.utils.RedisUtil;
import redis.clients.jedis.Jedis;

import java.util.Map;

public class UserProfileVerification {
    public static void main(String[] args) {
        String singleUser = args[0];
        String shardingUser = args[1];
        Jedis jedis13 = RedisPool.getJedisPool13();
        Jedis shardingJedis = RedisUtil.getUserJedis(shardingUser, 2);
        jedis13.select(3);
        String randomKey = jedis13.randomKey();

        Map<String, String> map1 = jedis13.hgetAll(randomKey);
        System.out.println("randomkey db3 is " + map1);
        jedis13.hmset(singleUser, map1);
        System.out.println("single db3 is " + jedis13.hgetAll(singleUser));
        shardingJedis.hmset(shardingUser, map1);
        System.out.println("sharding db2 is " + shardingJedis.hgetAll(shardingUser));

        jedis13.select(4);
        shardingJedis.select(3);
        Map<String, String> map2 = jedis13.hgetAll(randomKey);
        System.out.println("randomkey db4 is " + map2);
        jedis13.hmset(singleUser, map2);
        System.out.println("single db4 is " + jedis13.hgetAll(singleUser));
        shardingJedis.hmset(shardingUser, map2);
        System.out.println("sharding db3 is " + shardingJedis.hgetAll(shardingUser));
    }
}
