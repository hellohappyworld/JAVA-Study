package com.gaowj.job;

import com.gaowj.utils.JedisClusterUtil;
import com.gaowj.utils.RedisPool;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.ShardedJedis;

import java.util.*;
import java.util.concurrent.Callable;

/**
 * created by gaowj.
 * created on 2020-05-15.
 * function:
 */
public class ShardingDataMoveJob implements Callable {
    private Jedis singleJedis = null;
    private ShardedJedis shardedJedis = null;
    private int singleDb = 0;
    private int count = 0;


    public ShardingDataMoveJob(int singleDb) {
        this.singleDb = singleDb;
    }

    @Override
    public Object call() throws Exception {
        shardedJedis = JedisClusterUtil.getFilteShardedJedis();
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
                int ttl = singleJedis.ttl(key).intValue();
                if (-1 != ttl)
                    shardedJedis.expire(key, ttl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        shardedJedis.close();
        singleJedis.close();
        System.out.println(" the from db is " + singleDb + ", the from count is " + String.valueOf(keysLocal.size()) + " ,the to count is " + String.valueOf(count));
        return null;
    }

    private void setMove(String key) {
        Set<String> smembers = singleJedis.smembers(key);
        shardedJedis.sadd(key, smembers.toArray(new String[smembers.size()]));
        count++;
    }

    private void hashMove(String key) {
        Map<String, String> map = singleJedis.hgetAll(key);
        shardedJedis.hmset(key, map);
        count++;
    }

    private void StringMove(String key) {
        String v = singleJedis.get(key);
        shardedJedis.set(key, v);
        count++;
    }

    private void listMove(String key) {
        List<String> lrange = singleJedis.lrange(key, 0, -1);
        if (shardedJedis.exists(key))
            shardedJedis.del(key);
        shardedJedis.rpush(key, lrange.toArray(new String[lrange.size()]));
        count++;
    }
}
