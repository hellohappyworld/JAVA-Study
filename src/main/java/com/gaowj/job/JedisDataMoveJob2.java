package com.gaowj.job;

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
public class JedisDataMoveJob2 implements Callable {
    private JedisPool jedisSingle = null;
    private JedisPool jedisCluster = null;
    private Jedis singleRedis = null;
    private Jedis clusterRedis = null;
    private String ip = null;
    private int count = 0;


    public JedisDataMoveJob2(JedisPool jedisSingle, String ip, JedisPool jedisCluster) {
        this.ip = ip;
        this.jedisSingle = jedisSingle;
        this.jedisCluster = jedisCluster;
    }

    @Override
    public Object call() throws Exception {
        singleRedis = jedisSingle.getResource();
        clusterRedis = jedisCluster.getResource();
        int[] singleDb = {-1, -1, 3, 4, 6, 7, 16, 21, 23, 24, 26, 28, 15, 10, 17, 25};
        for (int db = 2; db <= 15; db++) {
            count = 0;
            Set<String> keys = null;
            try {
                singleRedis.select(singleDb[db]);
                clusterRedis.select(db);
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
                System.out.println("the ip is " + ip + " the db is " + String.valueOf(db) + ", the single count is " + String.valueOf(keys.size()) + ", the cluster count is " + String.valueOf(count));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        singleRedis.close();
        clusterRedis.close();
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
