package com.gaowj.run;

import com.gaowj.job.RedisBloomFilter;
import com.gaowj.utils.RedisUtil;
import redis.clients.jedis.Jedis;

/**
 * created by gaowj.
 * created on 2020-06-03.
 * function:
 */
public class UserBloomIsExists {
    public static void main(String[] args) {
        int db = Integer.parseInt(args[0]);
        String userId = args[1];
        String articleId = args[2];
        Jedis userJedis = RedisUtil.getUserJedis(userId, db);
        try {
            boolean exists = RedisBloomFilter.exists(userJedis, userId, articleId);
            System.out.println(exists);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            userJedis.close();
        }
    }
}
