package com.gaowj;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Jedis jedis = RedisPool.getJedisPool11();
        jedis.select(1);


        jedis.close();
    }
}
