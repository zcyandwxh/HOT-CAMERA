package com.point.common.data;

import lombok.Data;

/**
 * 流媒体服务器
 */
@Data
public class DeviceRmsSvr {

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
    private String serviceNode;
    private String progId;

}
