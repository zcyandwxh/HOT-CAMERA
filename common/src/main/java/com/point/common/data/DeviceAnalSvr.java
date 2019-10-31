package com.point.common.data;

import lombok.Data;

/**
 * 视频分析服务器
 */
@Data
public class DeviceAnalSvr {

    private String devId;
    private String devModel;
    private String devName;
    private Integer isEnabled;
    private Integer isFaceCapAble;
    private Integer isVehCapAble;
    private Integer isFaceRecogAble;
    private Integer isVehRecogAble;
    private Integer isFaceCompAble;
    private Integer isVehCompAble;
    private Integer isBodyCapAble;
    private Integer isBodyRecogAble;
    private Integer isBodyRecogCompAble;
    private Integer isVehRecogCompAble;
    private String description;
    private String ipAddr;
    private String ipV6Addr;
    private Integer port;
    private String loginUser;
    private String loginPass;
    private Integer chanCnt;
    private String progId;
}
