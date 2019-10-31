package com.point.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 设备适配程序信息
 */
@Data
public class MstDevProg {

    private Integer id;
    private Integer type;
    private String devId;
    private String progId;
    private String progVer;
    private String progParam;
    private String description;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
