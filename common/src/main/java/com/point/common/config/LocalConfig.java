package com.point.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 本机相关配置
 */
@Data
@Configuration
public class LocalConfig {

    /**
     * 本机端口
     */
    @Value("${server.ip:none}")
    private String ip;

    /**
     * 本机端口
     */
    @Value("${server.port}")
    private String port;
}
