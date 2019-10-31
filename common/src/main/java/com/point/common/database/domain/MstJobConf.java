package com.point.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 采集任务配置
 */
@Data
public class MstJobConf {

    private Integer id;
    private String jobId;
    private String jobName;
    private Integer jobType;
    private String description;
    private Integer frequency;
    private String runDay;
    private String startTime;
    private String endTime;
    private String frtDevId;
    private String capDevId;
    private Integer recogPlanId;
    private Integer compPlanId;
    private Integer isEnabled;
    private Integer isDeleted;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
