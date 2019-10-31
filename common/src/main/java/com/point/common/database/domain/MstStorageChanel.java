package com.point.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 文件存储通道
 */
@Data
public class MstStorageChanel {

    private Integer id;
    private String chanelId;
    private String chanelName;
    private String protocol;
    private String user;
    private String password;
    private String ipAddr;
    private Integer port;
    private String basePath;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
