package com.gaowj.run;

import com.gaowj.common.RedisConst;
import com.gaowj.utils.RedisPool;
import com.gaowj.utils.RedisUtil;
import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.zip.CRC32;

/**
 * created by gaowj.
 * created on 2020-05-09.
 * function:手动设置用户画像以便于后期逻辑验证
 */
public class SetUserPortrait {
    public static void main(String[] args) {
        String videoUid = args[0] + "video";
        String newsUid = args[0] + "news";

        //选取实例节点
        CRC32 crc32 = new CRC32();
        crc32.update(videoUid.getBytes());
        int nUser = Math.abs((int) crc32.getValue() % 10);

        Jedis jedis13 = RedisPool.getJedisPool13();
        Jedis userJedis = RedisUtil.getUserJedis(videoUid, RedisConst.DB_2);

        //用户计算后历史画像
        String otherKey = userJedis.randomKey();
        Map<String, String> historyPortrait = userJedis.hgetAll(otherKey);
        userJedis.hmset(videoUid, historyPortrait);
        jedis13.select(3);
        jedis13.hmset(newsUid, historyPortrait);
        //用户初始历史画像
        userJedis.select(RedisConst.DB_3);
        Map<String, String> startPortrait = userJedis.hgetAll(otherKey);
        userJedis.hmset(videoUid, startPortrait);
        jedis13.select(4);
        jedis13.hmset(newsUid, startPortrait);


        System.out.println("newsUid is " + newsUid);
        System.out.println("videoUid is " + videoUid);
        System.out.println("other user is " + otherKey + " ,the historyPortrait is " + historyPortrait);
        System.out.println("other user is " + otherKey + " ,the startPortrait is " + startPortrait);

        userJedis.close();
    }
}
