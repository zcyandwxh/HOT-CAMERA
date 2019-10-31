package com.point.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 图片结构化配置
 */
@Data
public class MstRecogConf {

    private Integer id;
    private Integer planId;
    private String confName;
    private Integer dataType;
    private String devId;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
