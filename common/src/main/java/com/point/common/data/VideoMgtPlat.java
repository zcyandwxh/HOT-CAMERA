package com.point.common.data;

import lombok.Data;

/**
 * 视频管理平台
 */
@Data
public class VideoMgtPlat {

    private String devId;
    private String devModel;
    private String devName;
    private Integer isEnabled;
    private String description;
    private String ipAddr;
    private String ipV6Addr;
    private Integer port;
    private String rtsp;
    private String loginUser;
    private String loginPass;
    private Integer devType;
    private String progId;

}
