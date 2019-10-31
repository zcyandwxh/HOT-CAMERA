package com.point.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 结构化统一接口用配置类
 */
@PropertySource("classpath:structure.properties")
@Data
@Configuration
public class UniversalStructureConfig {

    /**
     * 旷世结构化调用授权token-key
     */
    @Value("${megvii.struct.token.key}")
    private String megviiStructTokenKey;

    /**
     * 旷世结构化调用授权token-value
     */
    @Value("${megvii.struct.token.value}")
    private String megviiStructTokenVal;

    /**
     * 依图结构化调用授权token-key
     */
    @Value("${yitu.struct.token.key}")
    private String yituStructTokenKey;

    /**
     * 依图结构化调用授权token-value
     */
    @Value("${yitu.struct.token.value}")
    private String yituStructTokenVal;

    /**
     * 商汤结构化调用授权token-key
     */
    @Value("${sensetime.struct.token.key}")
    private String sensetimeStructTokenKey;

    /**
     * 商汤结构化调用授权token-value
     */
    @Value("${sensetime.struct.token.value}")
    private String sensetimeStructTokenVal;

    /**
     * 布控任务回调MQ地址
     */
    @Value("${structure.callback.mq.uri}")
    private String callBackMQUri;

    /**
     * 布控任务回调MQ告警队列名
     */
    @Value("${structure.callback.mq.queue.alarm:}")
    private String callBackAlarmQueue;

    /**
     * 布控任务回调MQ抓拍队列名
     */
    @Value("${structure.callback.mq.queue.capture:}")
    private String callBackCaptureQueue;
}
