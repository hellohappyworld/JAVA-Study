package com.gaowj.job;

import com.gaowj.utils.JedisClusterUtil;
import com.gaowj.utils.RedisPool;
import redis.clients.jedis.*;

import java.util.*;
import java.util.concurrent.Callable;

/**
 * created by gaowj.
 * created on 2020-05-15.
 * function:
 */
public class ClusterDataMoveJob implements Callable {
    private Jedis singleJedis = null;
    private JedisCluster jedisCluster = null;
    private int singleDb = 0;
    private int count = 0;


    public ClusterDataMoveJob(int singleDb) {
        this.singleDb = singleDb;
    }

    @Override
    public Object call() throws Exception {
        jedisCluster = JedisClusterUtil.getGroupNewsCluster();
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
                    jedisCluster.expire(key, ttl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        singleJedis.close();
        System.out.println(" the from db is " + singleDb + ", the from count is " + String.valueOf(keysLocal.size()) + " ,the to count is " + String.valueOf(count));
        return null;
    }

    private void setMove(String key) {
        Set<String> smembers = singleJedis.smembers(key);
        jedisCluster.sadd(key, smembers.toArray(new String[smembers.size()]));
        count++;
    }

    private void hashMove(String key) {
        Map<String, String> map = singleJedis.hgetAll(key);
        jedisCluster.hmset(key, map);
        count++;
    }

    private void StringMove(String key) {
        String v = singleJedis.get(key);
        jedisCluster.set(key, v);
        count++;
    }

    private void listMove(String key) {
        List<String> lrange = singleJedis.lrange(key, 0, -1);
        if (jedisCluster.exists(key))
            jedisCluster.del(key);
        jedisCluster.rpush(key, lrange.toArray(new String[lrange.size()]));
        count++;
    }
}
