package com.gaowj.run;

import com.gaowj.utils.RedisPool;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * created by gaowj.
 * created on 2020-06-02.
 * function:
 */
public class BloomCount {
    public static void main(String[] args) {
        int db = Integer.parseInt(args[0]);
        Jedis jedis121_7002 = RedisPool.getJedis121_7002(db);
        Jedis jedis122_7002 = RedisPool.getJedis122_7002(db);
        Jedis jedis123_7002 = RedisPool.getJedis123_7002(db);
        Jedis jedis124_7002 = RedisPool.getJedis124_7002(db);
        Jedis jedis125_7002 = RedisPool.getJedis125_7002(db);
        Jedis jedis126_7002 = RedisPool.getJedis126_7002(db);
        Jedis jedis121_7001 = RedisPool.getJedis121_7001(db);
        Jedis jedis122_7001 = RedisPool.getJedis122_7001(db);
        Jedis jedis123_7001 = RedisPool.getJedis123_7001(db);
        Jedis jedis124_7001 = RedisPool.getJedis124_7001(db);
        HashMap<String, Jedis> map = new HashMap<>();
        map.put("jedis121_7002", jedis121_7002);
        map.put("jedis122_7002", jedis122_7002);
        map.put("jedis123_7002", jedis123_7002);
        map.put("jedis124_7002", jedis124_7002);
        map.put("jedis125_7002", jedis125_7002);
        map.put("jedis126_7002", jedis126_7002);
        map.put("jedis121_7001", jedis121_7001);
        map.put("jedis122_7001", jedis122_7001);
        map.put("jedis123_7001", jedis123_7001);
        map.put("jedis124_7001", jedis124_7001);

        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String k = iterator.next();
            Jedis jedis = map.get(k);
            HashSet<String> keysLocal = new HashSet<>();
            String cursorLocal = String.valueOf(0);
            ScanParams spLocal = new ScanParams();
            spLocal.match("*");
            spLocal.count(100);
            do {
                ScanResult<String> srLocal = jedis.scan(cursorLocal, spLocal);
                List<String> partKeysLocal = srLocal.getResult();
                keysLocal.addAll(partKeysLocal);
                cursorLocal = srLocal.getCursor();
            } while (!cursorLocal.equals(String.valueOf(0)));
            System.out.println(k + "------->" + keysLocal.size());
            jedis.close();
        }
    }
}
