package com.point.common.data;

import lombok.Data;

/**
 * 文件存储通道
 */
@Data
public class FileStorageChanel {

    private String id;
    private String chanelId;
    private String chanelName;
    private String protocol;
    private String user;
    private String password;
    private String ipAddr;
    private Integer port;
    private String basePath;

}
