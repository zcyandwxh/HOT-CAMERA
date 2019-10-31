package com.point.common.data;

import lombok.Data;

/**
 * 采集任务配置
 */
@Data
public class JobConf {

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

}
