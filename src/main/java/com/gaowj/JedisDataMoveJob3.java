package com.gaowj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * created by gaowj.
 * created on 2020-04-20.
 * function:
 */
public class JedisDataMoveJob3 implements Callable {

    private final Logger logger = LoggerFactory.getLogger(JedisDataMoveJob3.class);

    private JedisPool jedisSingle = null;
    private JedisPool jedisCluster = null;
    private Jedis singleRedis = null;
    private Jedis clusterRedis = null;
    private int db1 = -1;
    private int db2 = -1;
    private int count = 0;

    public JedisDataMoveJob3(JedisPool jedisSingle, JedisPool jedisCluster, int db1, int db2) {
        this.jedisSingle = jedisSingle;
        this.jedisCluster = jedisCluster;
        this.db1 = db1;
        this.db2 = db2;
    }

    @Override
    public Object call() throws Exception {
        Set<String> keys = null;
        try {
            singleRedis = jedisSingle.getResource();
            clusterRedis = jedisCluster.getResource();
            singleRedis.select(db1);
            clusterRedis.select(db2);
            clusterRedis.flushDB();

            keys = singleRedis.keys("*");
            Iterator<String> keyIter = keys.iterator();

            while (keyIter.hasNext()) {
                try {
                    String key = keyIter.next();
                    String type = singleRedis.type(key);
                    switch (type) {
                        case "none":
                            listMove(key);
                            break;
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            singleRedis.close();
            clusterRedis.close();
        }
        System.out.println("the db is " + String.valueOf(db1) + ", the single count is " + String.valueOf(keys.size()) + ", the cluster count is " + String.valueOf(count));
        return null;
    }

    private void setMove(String key) {
        Set<String> smembers = singleRedis.smembers(key);
        clusterRedis.sadd(key, smembers.toArray(new String[smembers.size()]));
        count++;
    }

    private void hashMove(String key) {
        Map<String, String> map = singleRedis.hgetAll(key);
        clusterRedis.hmset(key, map);
        count++;
    }

    private void StringMove(String key) {
        String v = singleRedis.get(key);
        clusterRedis.set(key, v);
        count++;
    }

    private void listMove(String key) {
        List<String> lrange = singleRedis.lrange(key, 0, -1);
        clusterRedis.lpush(key, lrange.toArray(new String[lrange.size()]));
        count++;
    }
}
