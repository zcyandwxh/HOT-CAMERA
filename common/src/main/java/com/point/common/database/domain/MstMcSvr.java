package com.point.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 转码服务器基础信息
 */
@Data
public class MstMcSvr {

    private Integer id;
    private String devId;
    private String devName;
    private String devModel;
    private Integer isEnabled;
    private Integer isDeleted;
    private String ipAddr;
    private String ipV6Addr;
    private Integer port;
    private String loginUser;
    private String loginPass;
    private String description;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
