package com.point.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 任务流程配置
 */
@Data
public class MstTaskFlowConf {

    private Integer id;
    private Integer planId;
    private Integer seq;
    private String name;
    private String progId;
    private Integer isEnabled;
    private Integer retryCnt;
    private Integer isContinue;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
