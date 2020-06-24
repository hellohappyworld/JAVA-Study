package com.gaowj;

import com.alibaba.fastjson.JSON;
import com.gaowj.common.Constant;
import com.gaowj.utils.HttpUtil;

/**
 * created by gaowj.
 * created on 2020-06-24.
 * function: kafka消费者组堆积监控
 */
public class KafkaConsumerHeap {

    //获取kafka消费者组堆积值
    public static int getSumLag(String jsonStr) {
        int sumLag = 0;

        try {
            String data = JSON.parseObject(jsonStr).getString("data");
            sumLag = JSON.parseObject(data).getInteger("SumLag");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sumLag;
    }

    //企业发送报警
    public static String sendMessage(int sumLag, int alarmLag, String message) {
        String alarmMessage = "{\"msgGroup\": \"MANGROUP\",\"title\": \"" + message + "\",\"content\": \"" + message + "\",\"account\": \"gaowj\",\"url\": \"http://local.datacenter.ifengidc.com/local/sendWechatMsg\"}";
        if (sumLag >= alarmLag) {
            String returnStr = HttpUtil.doPost2(Constant.WECHAT_MESSAGE, alarmMessage);
            return returnStr;
        }
        return "未大批量堆积";
    }

    public static void main(String[] args) {
        String recentUserArticleKafkaGroupId = args[0];
        int recentUserArticleAlarmLag = Integer.parseInt(args[1]);
        String recentGroupTentativeKafkaGroupId = args[2];
        int recentGroupTentativeAlarmLag = Integer.parseInt(args[3]);

        //新闻正反馈
        String recentUserArticle = HttpUtil.doGet(Constant.KAFKA_SUM_LAG + recentUserArticleKafkaGroupId);
        int recentUserArticleSumLag = KafkaConsumerHeap.getSumLag(recentUserArticle);
        String recentUserArticleMessage = KafkaConsumerHeap.sendMessage(recentUserArticleSumLag, recentUserArticleAlarmLag, "新闻正反馈kafka消息堆积,topic:app_newsapp,groupId:" + recentUserArticleKafkaGroupId);
        System.out.println("新闻正反馈(recentUserArticleSumLag)-->" + recentUserArticleSumLag + " 监控返回值:" + recentUserArticleMessage);
        //新闻临近试探
        String recentGroupTentative = HttpUtil.doGet(Constant.KAFKA_SUM_LAG + recentGroupTentativeKafkaGroupId);
        int recentGroupTentativeSumLag = KafkaConsumerHeap.getSumLag(recentGroupTentative);
        String recentGroupTentativeMessage = KafkaConsumerHeap.sendMessage(recentGroupTentativeSumLag, recentGroupTentativeAlarmLag, "新闻临近试探kafka消息堆积,topic:newsapp_coldboot,groupId:" + recentGroupTentativeKafkaGroupId);
        System.out.println("新闻临近试探(recentGroupTentativeSumLag)-->" + recentGroupTentativeSumLag + " 监控返回值:" + recentGroupTentativeMessage);
    }
}
