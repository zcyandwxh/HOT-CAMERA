package com.point.common.data;

import lombok.Data;

/**
 * 任务信息
 */
@Data
public class TaskInfo {

    private String taskId;
    private Integer subNum;
    private String name;
    private Integer type;
    private String param;
    private String startTime;
    private String status;
    private String statusCode;
    private Integer progress;
    private String endTime;
    private String dataId;
    private Integer dataType;
    private String result;
    private String error;
    private String ext1;
    private String ext2;
    private String ext3;
}
