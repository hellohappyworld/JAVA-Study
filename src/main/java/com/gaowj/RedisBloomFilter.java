package com.gaowj;

import io.rebloom.client.Command;
import redis.clients.jedis.Client;
import redis.clients.jedis.Connection;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * gaowj
 * created on 2020-01-24
 * 布隆过滤器
 */
public class RedisBloomFilter {

    /**
     * 批量添加
     *
     * @param jedis
     * @param key
     * @param value
     * @return
     */
    public static boolean[] addMulti(Jedis jedis, String key, String... value) {
        ArrayList<String> arr = new ArrayList<>();
        arr.add(key);
        arr.addAll(Arrays.asList(value));

        Connection client = jedis.getClient();
        client.sendCommand(Command.MADD, (String[]) arr.toArray((String[]) value));
        List<Long> replyLongList = client.getIntegerMultiBulkReply();

        boolean[] ret = new boolean[value.length];
        for (int i = 0; i < replyLongList.size(); i++) {
            ret[i] = replyLongList.get(i) != 0;
        }
        return ret;
    }

    /**
     * 判断是否存在
     *
     * @param jedis
     * @param key
     * @param value
     * @return
     */
    public static boolean exists(Jedis jedis, String key, String value) {
        Client client = jedis.getClient();
        client.sendCommand(Command.EXISTS, key, value);
        return client.getIntegerReply() == 1;
    }

    /**
     * 批量判断是否存在
     *
     * @param jedis
     * @param key
     * @param value
     * @return
     */
    public static List<Long> existsMulti(Jedis jedis, String key, String... value) {
        ArrayList<String> arr = new ArrayList<String>();
        arr.add(key);
        arr.addAll(Arrays.asList(value));

        Connection client = jedis.getClient();
        client.sendCommand(Command.MEXISTS, (String[]) arr.toArray((String[]) value));
        List<Long> replyLongList = client.getIntegerMultiBulkReply();

        return replyLongList;
    }

    /**
     * 创建名为key的过滤器
     *
     * @param jedis
     * @param key
     * @param initCapacity 预计放入的元素数量
     * @param errorRate    错误率
     * @return 成功返回'ok' 失败抛出异常
     */
    public static String createFilter(Jedis jedis, String key, long initCapacity, double errorRate) {
        Client client = jedis.getClient();
        client.sendCommand(Command.RESERVE, key, String.valueOf(errorRate), String.valueOf(initCapacity));
        return client.getStatusCodeReply();
    }


    public static void main(String[] args) {
        String userKey = args[0];
        Jedis jedis = RedisUtil.getUserJedis(userKey, 1);
        Client client = jedis.getClient();
        boolean[] booleans = addMulti(jedis, userKey, new String[]{"ucms_1", "ucms_2", "ucms_3"});
        for (boolean b : booleans)
            System.out.println(b);
    }
}
