package com.gaowj;

import com.gaowj.job.SingleRedisDataMoveJob;
import com.gaowj.utils.RedisPool;
import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * created by gaowj.
 * created on 2020-05-15.
 * function:
 */
public class SingleRedisReadFileDataMove {
    public static void main(String[] args) {
        String filePath = args[0];
        List<String> list = new ArrayList<String>();
        try {
            String encoding = "GBK";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                while ((lineTxt = bufferedReader.readLine()) != null) {
                    list.add(lineTxt);
                }
                bufferedReader.close();
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }

            ExecutorService es = Executors.newFixedThreadPool(60);
            for (String line : list) {
                String[] split = line.split(",");
                String fromJedisOption = split[0];
                String fromDb = split[1];
                String toJedisOption = split[2];
                String toDb = split[3];
                Jedis fromJedis = null;
                Jedis toJedis = null;
                switch (fromJedisOption) {
                    case "11":
                        fromJedis = RedisPool.getJedisPool11();
                        fromJedis.select(Integer.parseInt(fromDb));
                        break;
                    case "12":
                        fromJedis = RedisPool.getJedisPool12();
                        fromJedis.select(Integer.parseInt(fromDb));
                        break;
                    case "13":
                        fromJedis = RedisPool.getJedisPool13();
                        fromJedis.select(Integer.parseInt(fromDb));
                        break;
                }
                switch (toJedisOption) {
                    case "125":
                        toJedis = RedisPool.getJedis125_7001(Integer.parseInt(toDb));
                        break;
                    case "126":
                        toJedis = RedisPool.getJedis126_7001(Integer.parseInt(toDb));
                        break;
                }

                es.submit(new SingleRedisDataMoveJob(fromJedis, fromDb, toJedis, toDb));
            }

        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }
}
