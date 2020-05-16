package com.study;

import redis.clients.jedis.Jedis;

import java.util.Map;

public class RedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("10.80.32.158", 80);
        jedis.auth("WxDCxfA8qi");
        jedis.select(3);
        Map<String, String> map1 = jedis.hgetAll(jedis.randomKey());
        Map<String, String> map2 = jedis.hgetAll(jedis.randomKey());
        jedis.select(10);
        jedis.hmset("hashtest0516", map1);
        jedis.hmset("hashtest0516", map2);
        System.out.println(map1);
        System.out.println(map2);
    }
}
