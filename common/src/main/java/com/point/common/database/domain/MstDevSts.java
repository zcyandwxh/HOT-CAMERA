package com.point.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 设备运行状态
 */
@Data
public class MstDevSts {

    private Integer id;
    private Integer type;
    private String devId;
    private String name;
    private Integer status;
    private String statusTime;
    private String ext1;
    private String ext2;
    private String ext3;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
