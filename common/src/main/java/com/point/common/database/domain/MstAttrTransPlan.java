package com.point.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 数据属性转换方案
 */
@Data
public class MstAttrTransPlan {

    private Integer id;
    private Integer planId;
    private String planName;
    private Integer dataType;
    private Date startTime;
    private Date endTime;
    private String description;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
