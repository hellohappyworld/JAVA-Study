package com.gaowj;

import com.alibaba.fastjson.JSON;
import com.gaowj.common.Constant;
import com.gaowj.utils.HttpUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    public static String sendMessage(int sumLag, int alarmLag, String message, String staff) {
        if (sumLag >= alarmLag) {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = simpleDateFormat.format(date);
            String alarmMessage = "{\"msgtype\": \"text\",\"text\": {\"content\": \""  + message + ",堆积量为:" + sumLag + ",设置最大报警量为:" + alarmLag + ",报警时间:" + format + "\"}}";
//            System.out.println(alarmMessage);
            String returnStr = HttpUtil.doPost2(Constant.WECHAT_ROBOT_MESSAGE, alarmMessage);
            return returnStr;
        }
        return "未大批量堆积";
    }

    public static void main(String[] args) {
        //新闻正反馈
        String projectName = args[0];
        String recentUserArticleKafkaGroupId = args[1];  // 消费者组
        int recentUserArticleAlarmLag = Integer.parseInt(args[2]); //期望最大堆积值
        String staff = args[3];
        /*//新闻临近试探
        String recentGroupTentativeKafkaGroupId = args[2];
        int recentGroupTentativeAlarmLag = Integer.parseInt(args[3]);
        //视频正反馈
        String videoRecentUserArticleKafkaGroupId = args[4];
        int videoRecentUserArticleAlarmLag = Integer.parseInt(args[5]);
        //视频临近试探
        String videoRecentGroupTentativeKafkaGroupId = args[6];
        int videoRecentGroupTentativeAlarmLag = Integer.parseInt(args[7]);*/

        //新闻正反馈
        String recentUserArticle = HttpUtil.doGet(Constant.TENCENT_KAFKA_SUM_LAG + recentUserArticleKafkaGroupId);
        int recentUserArticleSumLag = KafkaConsumerHeap.getSumLag(recentUserArticle);
        String message = projectName + "kafka消息堆积,groupId:";
        String recentUserArticleMessage = KafkaConsumerHeap.sendMessage(recentUserArticleSumLag, recentUserArticleAlarmLag, message + recentUserArticleKafkaGroupId, staff);
        System.out.println(projectName + "堆积值(sumLag)-->" + recentUserArticleSumLag + " 监控返回值:" + recentUserArticleMessage);
        /*//新闻临近试探
        String recentGroupTentative = HttpUtil.doGet(Constant.KAFKA_SUM_LAG + recentGroupTentativeKafkaGroupId);
        int recentGroupTentativeSumLag = KafkaConsumerHeap.getSumLag(recentGroupTentative);
        String recentGroupTentativeMessage = KafkaConsumerHeap.sendMessage(recentGroupTentativeSumLag, recentGroupTentativeAlarmLag, "新闻临近试探kafka消息堆积,topic:newsapp_coldboot,groupId:" + recentGroupTentativeKafkaGroupId);
        System.out.println("新闻临近试探(recentGroupTentativeSumLag)-->" + recentGroupTentativeSumLag + " 监控返回值:" + recentGroupTentativeMessage);
        //视频正反馈
        String videoRecentUserArticle = HttpUtil.doGet(Constant.KAFKA_SUM_LAG + videoRecentUserArticleKafkaGroupId);
        int videoRecentUserArticleSumLag = KafkaConsumerHeap.getSumLag(videoRecentUserArticle);
        String videoRecentUserArticleMessage = KafkaConsumerHeap.sendMessage(videoRecentUserArticleSumLag, videoRecentUserArticleAlarmLag, "视频正反馈kafka消息堆积,topic:app_vapp_ex,groupId:" + videoRecentUserArticleKafkaGroupId);
        System.out.println("视频正反馈(videoRecentUserArticleSumLag)-->" + videoRecentUserArticleSumLag + " 监控返回值:" + videoRecentUserArticleMessage);
        //视频临近试探
        String videoRecentGroupTentative = HttpUtil.doGet(Constant.KAFKA_SUM_LAG + videoRecentGroupTentativeKafkaGroupId);
        int videoRecentGroupTentativeSumLag = KafkaConsumerHeap.getSumLag(videoRecentGroupTentative);
        String videoRecentGroupTentativeMessage = KafkaConsumerHeap.sendMessage(videoRecentGroupTentativeSumLag, videoRecentGroupTentativeAlarmLag, "视频临近试探kafka消息堆积,topic:vapp_coldboot,groupId:" + videoRecentGroupTentativeKafkaGroupId);
        System.out.println("视频临近试探(videoRecentGroupTentativeSumLag)-->" + videoRecentGroupTentativeSumLag + " 监控返回值:" + videoRecentGroupTentativeMessage);*/
    }
}
