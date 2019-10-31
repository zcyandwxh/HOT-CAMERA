package com.point.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 数据属性转换配置
 */
@Data
public class MstAttrTransConf {

    private Integer id;
    private Integer planId;
    private Integer relationId;
    private String planName;
    private String fieldName;
    private String attrMapKey;
    private String description;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
