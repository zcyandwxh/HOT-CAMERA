package com.point.common.data;

import lombok.Data;

/**
 * 运维服务器接口版本状态
 */
@Data
public class ServerVersion {
    private String ipAddr;
    private String devId;
    private String devName;
    private String version;
}
