package com.point.common.data;

import lombok.Data;

/**
 * 设备连接信息
 */
@Data
public class DeviceConnection {

    private String devId;
    private String devName;
    private String url;
    private Integer devType;
    private String ipAddr;
    private String ipV6Addr;
    private Integer port;
    private String prefix;
    private String loginUser;
    private String loginPass;
    private String platformId;
    private String devNum;
    private Integer channel;
    private Integer streamType;
    private String rtspUrl;
    private String filePath;

}
