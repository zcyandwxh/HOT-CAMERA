package com.point.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 人体比对底库对象
 */
@Data
public class MstBodyTargetDbObj {

    private Integer id;
    private String targetDb;
    private String devId;
    private String dbId;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
