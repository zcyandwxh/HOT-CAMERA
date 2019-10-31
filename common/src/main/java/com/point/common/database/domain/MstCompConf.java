package com.point.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 图片比对配置
 */
@Data
public class MstCompConf {

    private Integer id;
    private Integer planId;
    private String confName;
    private Integer dataType;
    private String devId;
    private String targetDb;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
