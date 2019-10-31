package com.point.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 系统参数配置
 */
@Data
public class MstSysParamConf {

    private Integer id;
    private String confKey;
    private String confName;
    private String confValue;
    private String description;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
