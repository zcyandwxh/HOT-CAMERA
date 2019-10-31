package com.point.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 人体目标
 */
@Data
public class MstBodyTarget {

    private Integer id;
    private String targetDb;
    private String targetId;
    private Integer branchNum;
    private String imgId;
    private String alias;
    private String description;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
