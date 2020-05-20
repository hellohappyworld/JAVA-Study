package com.gaowj.job;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.*;
import java.util.concurrent.Callable;

/**
 * created by gaowj.
 * created on 2020-05-15.
 * function:
 */
public class SingleRedisDataMoveJob implements Callable {
    private Jedis fromJedis = null;
    private Jedis toJedis = null;
    private String fromDb = null;
    private String toDb = null;
    private int count = 0;


    public SingleRedisDataMoveJob(Jedis fromJedis, String fromDb, Jedis toJedis, String toDb) {
        this.fromJedis = fromJedis;
        this.toJedis = toJedis;
        this.fromDb = fromDb;
        this.toDb = toDb;
    }

    @Override
    public Object call() throws Exception {
        HashSet<String> keysLocal = new HashSet<>();
        String cursorLocal = String.valueOf(0);
        ScanParams spLocal = new ScanParams();
        spLocal.match("*");
        spLocal.count(100);
        do {
            ScanResult<String> srLocal = fromJedis.scan(cursorLocal, spLocal);
            List<String> partKeysLocal = srLocal.getResult();
            keysLocal.addAll(partKeysLocal);
            cursorLocal = srLocal.getCursor();
        } while (!cursorLocal.equals(String.valueOf(0)));

        Iterator<String> keyIter = keysLocal.iterator();
        while (keyIter.hasNext()) {
            String key = keyIter.next();
            String type = fromJedis.type(key);
            try {
                switch (type) {
                    case "list":
                        listMove(key);
                        break;
                    case "string":
                        StringMove(key);
                        break;
                    case "hash":
                        hashMove(key);
                        break;
                    case "set":
                        setMove(key);
                        break;
                }
                int ttl = fromJedis.ttl(key).intValue();
                if (-1 != ttl)
                    toJedis.expire(key, ttl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        fromJedis.close();
        toJedis.close();
        System.out.println(" the from db is " + fromDb + ", the from count is " + String.valueOf(keysLocal.size()) + " ,the to db is " + toDb + " ,the to count is " + String.valueOf(count));
        return null;
    }

    private void setMove(String key) {
        Set<String> smembers = fromJedis.smembers(key);
        toJedis.sadd(key, smembers.toArray(new String[smembers.size()]));
        count++;
    }

    private void hashMove(String key) {
        Map<String, String> map = fromJedis.hgetAll(key);
        toJedis.hmset(key, map);
        count++;
    }

    private void StringMove(String key) {
        String v = fromJedis.get(key);
        toJedis.set(key, v);
        count++;
    }

    private void listMove(String key) {
        List<String> lrange = fromJedis.lrange(key, 0, -1);
        if (toJedis.exists(key))
            toJedis.del(key);
        toJedis.rpush(key, lrange.toArray(new String[lrange.size()]));
        count++;
    }
}
