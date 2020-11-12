package com.gaowj.common;

/**
 * created by gaowj.
 * created on 2020-06-24.
 * function:
 */
public class Constant {
    //新闻正反馈
    public static final String RECENTUSERARTICLE_KAFKA_GROUP_ID = "kafka_coldboot_202006232050";
    //新闻临近试探
    public static final String RECENTGROUPTENTATIVE_KAFKA_GROUP_ID = "kafka_coldboot_202006231604";
    //PublicKafkaCluster消费堆积接口
    public static final String KAFKA_SUM_LAG = "http://local.datacenter.ifengidc.com/local/monitor/kafkaLagMonitor?cluster=PublicKafkaCluster&groupID=";
    //腾讯云Kafka消费堆积接口
    public static final String TENCENT_KAFKA_SUM_LAG = "http://local.datacenter.ifengidc.com/local/monitor/kafkaLagMonitor?cluster=TencentKafka&groupID=";
    //企业微信报警接口
    public static final String WECHAT_MESSAGE = "http://local.datacenter.ifengidc.com/local/sendWechatMsg";
    public static final String WECHAT_ROBOT_MESSAGE = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=bc954180-2e9b-4dae-b89f-5b5c4997a08e";
}
