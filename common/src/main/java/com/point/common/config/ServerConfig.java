package com.point.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 任务运行主机相关设定
 */
@Component
@PropertySource("classpath:host.properties")
@Data
@Configuration
public class ServerConfig {

    /**
     * 64位Dll设备连接主机ID地址
     */
    @Value("${host.dll.64bit.ip}")
    private String deviceHost64BitIpAddr;

    /**
     * 64位Dll设备连接主机端口
     */
    @Value("${host.dll.64bit.port}")
    private String deviceHost64BitPort;

    /**
     * 32位Dll设备连接主机ID地址
     */
    @Value("${host.dll.32bit.ip}")
    private String deviceHost32BitIpAddr;

    /**
     * 32位Dll设备连接主机端口
     */
    @Value("${host.dll.32bit.port}")
    private String deviceHost32BitPort;

    /**
     * 32位Dll设备连接主机启动命令
     */
    @Value("${host.dll.32bit.cmd:}")
    private String deviceHost32BitCmd;

    /**
     * 64位Dll设备连接主机启动命令
     */
    @Value("${host.dll.64bit.cmd:}")
    private String deviceHost64BitCmd;

    /**
     * 任务运行主机IP
     */
    @Value("${host.job.ip}")
    private String jobServerAddr;

    /**
     * 任务运行主机端口
     */
    @Value("${host.job.port}")
    private String jobServerPort;

}
