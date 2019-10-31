package com.point.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 车辆比对底库对象
 */
@Data
public class MstVehTargetDbObj {

    private Integer id;
    private String targetDb;
    private String devId;
    private String dbId;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
