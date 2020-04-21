package com.gaowj;

import com.google.common.base.Preconditions;
import redis.clients.jedis.Jedis;

/**
 * gaowj
 * created on 2020-03-24
 * Redis集群布隆过滤器工具类
 */
public class RedisClusterBloomFilter {

    /**
     * 根据给定的布隆过滤器添加值
     */
    public static void add(BloomFilterHelper bloomFilterHelper, String key, String value, Jedis jedis) {
        Preconditions.checkArgument(bloomFilterHelper != null, "bloomFilterHelper不能为空");
        int[] offset = bloomFilterHelper.murmurHashOffset(value);
        for (int i : offset)
            jedis.setbit(key, i, true);
    }

    /**
     * 批量添加
     */
    public static void madd(BloomFilterHelper bloomFilterHelper, String key, String[] value, Jedis jedis) {
        Preconditions.checkArgument(bloomFilterHelper != null, "bloomFilterHelper不能为空");
        for (String v : value) {
            int[] offset = bloomFilterHelper.murmurHashOffset(v);
            for (int i : offset)
                jedis.setbit(key, i, true);
        }
    }

    /**
     * 根据给定的布隆过滤器判断值是否存在
     */
    public static boolean exists(BloomFilterHelper bloomFilterHelper, String key, String value, Jedis jedis) {
        Preconditions.checkArgument(bloomFilterHelper != null, "bloomFilterHelper不能为空");
        int[] offset = bloomFilterHelper.murmurHashOffset(value);
        for (int i : offset)
            if (!jedis.getbit(key, i))
                return false;
        return true;
    }

    /**
     * 批量判断是否存在
     */
    public static boolean[] existsMulti(BloomFilterHelper bloomFilterHelper, String key, String[] value, Jedis jedis) {
        Preconditions.checkArgument(bloomFilterHelper != null, "bloomFilterHelper不能为空");
        boolean[] booleans = new boolean[value.length];
        int index = 0;
        for (String v : value) {
            boolean b = true;
            int[] offset = bloomFilterHelper.murmurHashOffset(v);
            for (int i : offset)
                if (!jedis.getbit(key, i)) {
                    b = false;
                    break;
                }
            booleans[index] = b;
            index++;
        }
        return booleans;
    }
}
