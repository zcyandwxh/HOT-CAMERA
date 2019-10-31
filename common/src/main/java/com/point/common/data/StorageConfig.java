package com.point.common.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文件存储相关配置
 */
@Data
@EqualsAndHashCode
public class StorageConfig {

    /**
     * 通道ID
     */
    private String chanelId;

    /**
     * 协议
     */
    private String protocol;

    /**
     * 用户名
     */
    private String user;

    /**
     * 密码
     */
    private String password;

    /**
     * ip
     */
    private String ipAddr;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 文件存储根路径
     */
    private String basePath;

    /**
     * 文件类型
     */
    private Integer fileType;
}
