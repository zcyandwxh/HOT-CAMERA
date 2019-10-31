package com.point.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 文件存储方案
 */
@Data
public class MstStoragePlan {

    private Integer id;
    private Integer planId;
    private String planName;
    private Date startTime;
    private Date endTime;
    private Integer isEnabled;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
