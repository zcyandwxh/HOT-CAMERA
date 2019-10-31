package com.point.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 任务流程方案
 */
@Data
public class MstTaskFlowPlan {

    private Integer id;
    private Integer planId;
    private String name;
    private Integer flowType;
    private Integer isEnabled;
    private Integer isDeleted;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
