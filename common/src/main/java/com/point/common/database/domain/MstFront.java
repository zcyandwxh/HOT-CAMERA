package com.point.common.database.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 前端设备基础信息
 */
@Data
public class MstFront {

    private Integer id;
    private String devId;
    private String devName;
    private Integer devType;
    private String devModel;
    private String platformId;
    private String devNum;
    private Integer channel;
    private Integer streamType;
    private Integer devInputType;
    private String groupId;
    private Integer isEnabled;
    private Integer isDeleted;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String placeCode;
    private String place;
    private String ipAddr;
    private String ipV6Addr;
    private Integer port;
    private String loginUser;
    private String loginPass;
    private String rtspUrl;
    private String filePath;
    private String description;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
