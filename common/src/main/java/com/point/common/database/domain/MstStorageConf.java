package com.point.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 文件存储配置
 */
@Data
public class MstStorageConf {

    private Integer id;
    private Integer planId;
    private String planName;
    private Integer fileType;
    private String chanelId;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
