package com.gaowj.run;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gaowj.common.Constant;
import com.gaowj.utils.HttpUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * created by gaowj.
 * created on 2021-01-20.
 * function: kafka消费者组堆积监控
 */
public class KafkaConsumerLagSum {

    //获取kafka消费者组堆积值  lagJson, topic, partitions
    public static long getSumLag(String lagJson, String topics, int partitions) {
        long sumLag = 0L;
        try {
            String[] topicArr = topics.split("&");
            String data = JSON.parseObject(lagJson).getString("data");
            JSONObject dataJSONObj = JSON.parseObject(data);
            for (String topic : topicArr) {
                for (int i = 0; i < partitions; i++) {
                    String key = topic + "-" + i;
                    sumLag = sumLag + dataJSONObj.getLongValue(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sumLag;
    }

    //企业发送报警
    public static String sendMessage(long sumLag, long alarmLag, String message, String staff) {
        if (sumLag >= alarmLag) {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = simpleDateFormat.format(date);
            String returnStr;
            if ("".equals(staff)) {
                String alarmMessage = "{\"msgtype\": \"text\",\"text\": {\"content\": \"" + message + ",堆积量为:" + sumLag + ",设置最大报警量为:" + alarmLag + ",报警时间:" + format + "\"}}";
                returnStr = HttpUtil.doPost2(Constant.WECHAT_ROBOT_MESSAGE, alarmMessage);
            } else {
                String alarmMessage = "{\"msgGroup\": \"MANGROUP\",\"title\": \"kafka日志消费堆积\",\"content\": \"" + message + ",堆积量为:" + sumLag + ",设置最大报警量为:" + alarmLag + ",报警时间:" + format + "\",\"account\": \"" + staff + "\",\"url\": \"http://local.datacenter.ifengidc.com/local/sendWechatMsg\"}";
                returnStr = HttpUtil.doPost2(Constant.WECHAT_MESSAGE, alarmMessage);
            }
            return returnStr;
        }
        return "未大批量堆积";
    }

    public static void main(String[] args) {
        System.out.println(args.length);
        String projectName = args[0]; // 任务名称
        String groupId = args[1];  // 消费者组
        String topics = args[2];
        int partitions = Integer.parseInt(args[3]); // topic 分区数
        long alarmLag = Long.parseLong(args[4]); //期望最大堆积值
        String staff = args[5];

        String lagJson = HttpUtil.doGet(Constant.TENCENT_KAFKA_SUM_LAG + groupId);
        long sumLag = KafkaConsumerLagSum.getSumLag(lagJson, topics, partitions);
        String message = projectName + "kafka消息堆积,groupId:" + groupId + ",消费的topic:" + topics;
        String recentUserArticleMessage = KafkaConsumerLagSum.sendMessage(sumLag, alarmLag, message, staff);
        System.out.println(projectName + "堆积值(sumLag)-->" + sumLag + " 监控返回值:" + recentUserArticleMessage);
    }
}
