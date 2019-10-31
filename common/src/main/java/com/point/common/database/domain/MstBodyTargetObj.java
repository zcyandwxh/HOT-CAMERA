package com.point.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 人体目标对象
 */
@Data
public class MstBodyTargetObj {

    private Integer id;
    private String targetDb;
    private String targetId;
    private Integer branchNum;
    private String devId;
    private String objId;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
