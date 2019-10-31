package com.point.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 结果筛选方案
 */
@Data
public class MstRsltFilterPlan {

    private Integer id;
    private Integer planId;
    private String planName;
    private Integer dataType;
    private Integer action;
    private Integer isEnabled;
    private Integer isDeleted;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
