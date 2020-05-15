package com.gaowj;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.*;
import java.util.concurrent.Callable;

/**
 * created by gaowj.
 * created on 2020-05-15.
 * function:
 */
public class JedisDataMoveJob4 implements Callable {
    private Jedis singleJedis = null;
    private int singleDb = 0;
    private int shardingDb = 0;
    private int count = 0;

    public JedisDataMoveJob4(int singleDb, int shardingDb) {
        this.singleDb = singleDb;
        this.shardingDb = shardingDb;
    }

    @Override
    public Object call() throws Exception {
        singleJedis = RedisPool.getJedisPool13();
        singleJedis.select(singleDb);

        HashSet<String> keysLocal = new HashSet<>();
        String cursorLocal = String.valueOf(0);
        ScanParams spLocal = new ScanParams();
        spLocal.match("*");
        spLocal.count(100);
        do {
            ScanResult<String> srLocal = singleJedis.scan(cursorLocal, spLocal);
            List<String> partKeysLocal = srLocal.getResult();
            keysLocal.addAll(partKeysLocal);
            cursorLocal = srLocal.getCursor();
        } while (!cursorLocal.equals(String.valueOf(0)));

        Iterator<String> keyIter = keysLocal.iterator();

        while (keyIter.hasNext()) {
            String key = keyIter.next();
            String type = singleJedis.type(key);
            Jedis userJedis = RedisUtil.getUserJedis(key, shardingDb);
            try {
                switch (type) {
                    case "list":
                        listMove(userJedis, key);
                        break;
                    case "string":
                        StringMove(userJedis, key);
                        break;
                    case "hash":
                        hashMove(userJedis, key);
                        break;
                    case "set":
                        setMove(userJedis, key);
                        break;
                }
                int ttl = singleJedis.ttl(key).intValue();
                userJedis.expire(key, ttl);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                userJedis.close();
            }
        }

        singleJedis.close();
        System.out.println(" the single db is " + String.valueOf(singleDb) + ", the single count is " + String.valueOf(keysLocal.size()) + " ,the sharding db is " + String.valueOf(shardingDb) + " ,the sharding count is " + String.valueOf(count));
        return null;
    }

    private void setMove(Jedis userJedis, String key) {
        Set<String> smembers = singleJedis.smembers(key);
        userJedis.sadd(key, smembers.toArray(new String[smembers.size()]));
        count++;
    }

    private void hashMove(Jedis userJedis, String key) {
        Map<String, String> map = singleJedis.hgetAll(key);
        userJedis.hmset(key, map);
        count++;
    }

    private void StringMove(Jedis userJedis, String key) {
        String v = singleJedis.get(key);
        userJedis.setnx(key, v);
        count++;
    }

    private void listMove(Jedis userJedis, String key) {
        List<String> lrange = singleJedis.lrange(key, 0, -1);
        userJedis.lpush(key, lrange.toArray(new String[lrange.size()]));
        count++;
    }
}
