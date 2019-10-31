package com.point.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 车辆目标
 */
@Data
public class MstVehTarget {

    private Integer id;
    private String targetDb;
    private String targetId;
    private Integer branchNum;
    private String imgId;
    private String plateNum;
    private String description;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
