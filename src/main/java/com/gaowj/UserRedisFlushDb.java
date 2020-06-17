package com.gaowj;

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
 * function: 用户使用的布隆库清库处理
 */
public class UserRedisFlushDb {
    public static void main(String[] args) {
        int db = Integer.parseInt(args[0]);
        Jedis jedis121_7002 = RedisPool.getJedis121_7002(db);  //10.90.18.14 7002
        Jedis jedis122_7002 = RedisPool.getJedis122_7002(db);  //10.90.18.15 7002
        Jedis jedis123_7002 = RedisPool.getJedis123_7002(db);  //10.90.18.16 7002
        Jedis jedis124_7002 = RedisPool.getJedis124_7002(db);  //10.90.18.17 7002
        Jedis jedis125_7002 = RedisPool.getJedis125_7002(db);  //10.90.18.18 7002
        Jedis jedis126_7002 = RedisPool.getJedis126_7002(db);  //10.90.18.19 7002
        Jedis jedis121_7001 = RedisPool.getJedis121_7001(db);  //10.90.18.14 7001
        Jedis jedis122_7001 = RedisPool.getJedis122_7001(db);  //10.90.18.15 7001
        Jedis jedis123_7001 = RedisPool.getJedis123_7001(db);  //10.90.18.16 7001
        Jedis jedis124_7001 = RedisPool.getJedis124_7001(db);  //10.90.18.17 7001
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
            jedis.flushDB();
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
