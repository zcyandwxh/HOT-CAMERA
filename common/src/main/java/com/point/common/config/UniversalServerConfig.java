package com.point.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 统一接口用配置类
 */
@PropertySource("classpath:openapi.properties")
@Data
@Configuration
public class UniversalServerConfig {

    /**
     * 旷世调用方账号
     */
    @Value("${megvii.openapi.caller.id}")
    private String merviiCallerId;

    /**
     * 旷世调用授权token
     */
    @Value("${megvii.openapi.access.token}")
    private String merviiToken;

    /**
     * 依图调用方账号
     */
    @Value("${yitu.openapi.caller.id}")
    private String yituCallerId;

    /**
     * 依图调用授权token
     */
    @Value("${yitu.openapi.access.token}")
    private String yituToken;

    /**
     * 商汤调用方账号
     */
    @Value("${sensetime.openapi.caller.id}")
    private String sensetimeCallerId;

    /**
     * 商汤调用授权token
     */
    @Value("${sensetime.openapi.access.token}")
    private String sensetimeToken;

    /**
     * 布控任务回调MQ地址
     */
    @Value("${callback.mq.uri}")
    private String callBackMQUri;

    /**
     * 布控任务回调MQ报警队列名
     */
    @Value("${callback.mq.queue.alarm:}")
    private String callBackAlarmQueue;

    /**
     * 布控任务回调MQ路人库队列名
     */
    @Value("${callback.mq.queue.passer:}")
    private String callBackPasserQueue;
}
