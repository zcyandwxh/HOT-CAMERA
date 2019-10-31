package com.point.common.data;

import lombok.Data;

/**
 * 视频采集任务
 */
@Data
public class AnalysisJob {

    private String jobId;
    private String jobName;
    private Integer jobType;
    private String description;
    private Integer frequency;
    private String runDay;
    private String startTime;
    private String endTime;
    private String frtDevId;
    private String capSvrId;
    private Integer recogPlanId;
    private Integer compPlanId;
    private Integer isEnable;
    private String createTime;

}
