package com.point.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 结果筛选配置
 */
@Data
public class MstRsltFilterConf {

    private Integer id;
    private Integer planId;
    private String filterTarget;
    private String filterCond;
    private String filterValue;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
