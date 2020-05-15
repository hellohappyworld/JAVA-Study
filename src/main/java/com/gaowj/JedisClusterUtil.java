package com.gaowj;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * gaowj
 * created on 2020-03-07
 * redis集群
 */
public class JedisClusterUtil {
    private static final Logger logger = LoggerFactory.getLogger(JedisClusterUtil.class);
    private static JedisCluster jedisCluster;
    private static JedisCluster groupNewsCluster;
    private static ShardedJedisPool filterNewsShardedPool;

    private JedisClusterUtil() {
    }

    static {
        try {
            GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
            poolConfig.setMaxIdle(800);
            poolConfig.setMaxTotal(30000);
            poolConfig.setMaxWaitMillis(10000);
            poolConfig.setTestOnBorrow(true);

            Set<HostAndPort> groupNewsClusterNodes = new HashSet<>();
            groupNewsClusterNodes.add(new HostAndPort(RedisConst.HOST_121_139, RedisConst.PORT_7003));
            groupNewsClusterNodes.add(new HostAndPort(RedisConst.HOST_122_138, RedisConst.PORT_7003));
            groupNewsClusterNodes.add(new HostAndPort(RedisConst.HOST_123_138, RedisConst.PORT_7003));
            groupNewsClusterNodes.add(new HostAndPort(RedisConst.HOST_124_154, RedisConst.PORT_7003));
            groupNewsClusterNodes.add(new HostAndPort(RedisConst.HOST_125_154, RedisConst.PORT_7003));
            groupNewsClusterNodes.add(new HostAndPort(RedisConst.HOST_126_154, RedisConst.PORT_7003));

            JedisShardInfo shardInfo_121 = new JedisShardInfo(RedisConst.HOST_121_139, RedisConst.PORT_7004);
            shardInfo_121.setPassword(RedisConst.PASSWORD_7001_7002);
            JedisShardInfo shardInfo_122 = new JedisShardInfo(RedisConst.HOST_122_138, RedisConst.PORT_7004);
            shardInfo_122.setPassword(RedisConst.PASSWORD_7001_7002);
            JedisShardInfo shardInfo_123 = new JedisShardInfo(RedisConst.HOST_123_138, RedisConst.PORT_7004);
            shardInfo_123.setPassword(RedisConst.PASSWORD_7001_7002);
            JedisShardInfo shardInfo_124 = new JedisShardInfo(RedisConst.HOST_124_154, RedisConst.PORT_7004);
            shardInfo_124.setPassword(RedisConst.PASSWORD_7001_7002);
            JedisShardInfo shardInfo_125 = new JedisShardInfo(RedisConst.HOST_125_154, RedisConst.PORT_7004);
            shardInfo_125.setPassword(RedisConst.PASSWORD_7001_7002);
            JedisShardInfo shardInfo_126 = new JedisShardInfo(RedisConst.HOST_126_154, RedisConst.PORT_7004);
            shardInfo_126.setPassword(RedisConst.PASSWORD_7001_7002);
            List<JedisShardInfo> shardInfos = new ArrayList<JedisShardInfo>(){{
                add(shardInfo_121);
                add(shardInfo_122);
                add(shardInfo_123);
                add(shardInfo_124);
                add(shardInfo_125);
                add(shardInfo_126);
            }};

            groupNewsCluster = new JedisCluster(groupNewsClusterNodes, 50000, 30000, 10, RedisConst.CLUSTER_PASSWORD, poolConfig);
            filterNewsShardedPool = new ShardedJedisPool(poolConfig, shardInfos);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("init redisCluster error : ", e);
        }


        HashSet<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("10.61.224.3", 6379));
        jedisCluster = new JedisCluster(nodes, 50000, 30000, 10, "88r9cW7BiHkdU46p", new JedisPoolConfig());
    }

    public static JedisCluster getJedis() {
        return jedisCluster;
    }

    public static JedisCluster getGroupNewsCluster() {
        return groupNewsCluster;
    }

    public static ShardedJedis getFilteShardedJedis() {
        return filterNewsShardedPool.getResource();
    }

    public static void returnJedis(JedisCluster jedis) {
        try {
            jedis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JedisCluster jedis = JedisClusterUtil.getJedis();
        jedis.set("test1", "test1");
        JedisClusterUtil.returnJedis(jedis);
    }

}
