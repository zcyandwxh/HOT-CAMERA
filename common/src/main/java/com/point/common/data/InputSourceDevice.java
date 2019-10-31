package com.point.common.data;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 输入源设备信息
 */
@Data
public class InputSourceDevice {
    private String devId;
    private Integer devType;
    private String platformId;
    private String devModel;
    private String devName;
    private String devNum;
    private Integer channel;
    private Integer streamType;
    private Integer devInputType;
    private String groupId;
    private String groupName;
    private String description;
    private Integer isEnabled;
    private BigDecimal lng;
    private BigDecimal lat;
    private String pcode;
    private String place;
    private String ipAddr;
    private String ipV6Addr;
    private Integer port;
    private String loginUser;
    private String loginPass;
    private String rtspUrl;
    private String filePath;
    private String progName;
    private String progId;
    private Integer batchAction;
    private String type;
}
