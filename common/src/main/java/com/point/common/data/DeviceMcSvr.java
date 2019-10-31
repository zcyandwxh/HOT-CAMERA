package com.point.common.data;

import lombok.Data;

/**
 * 转码服务器
 */
@Data
public class DeviceMcSvr {

    private String devId;
    private String devModel;
    private String devName;
    private Integer isEnabled;
    private String ipAddr;
    private String ipV6Addr;
    private Integer port;
    private String loginUser;
    private String loginPass;
    private String description;
    private String progId;

}
