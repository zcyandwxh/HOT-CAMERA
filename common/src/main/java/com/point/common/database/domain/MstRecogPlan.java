package com.point.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 图片结构化方案
 */
@Data
public class MstRecogPlan {

    private Integer id;
    private Integer planId;
    private String planName;
    private Integer isEnabled;
    private Integer isDeleted;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
