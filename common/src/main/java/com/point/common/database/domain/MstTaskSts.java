package com.point.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 任务运行状态
 */
@Data
public class MstTaskSts {

    private Integer id;
    private String taskId;
    private Integer subNum;
    private String name;
    private Integer type;
    private String param;
    private String startTime;
    private String status;
    private Integer statusCode;
    private Integer progress;
    private String endTime;
    private String dataId;
    private Integer dataType;
    private String result;
    private String error;
    private String ext1;
    private String ext2;
    private String ext3;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
