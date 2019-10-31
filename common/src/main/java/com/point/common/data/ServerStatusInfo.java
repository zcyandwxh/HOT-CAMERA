package com.point.common.data;

import lombok.Data;

/**
 * 运维服务器接口状态
 */
@Data
public class ServerStatusInfo {
    private String devId;
    private String ipAddr;
    private String devName;
    private String time;
    private String cpuPercent;
    private String cpuCount;
    private String cpuFrep;
    private String memoryPercent;
    private String memoryTotal;
    private String memoryFree;
    private String memoryUsed;
    private String diskPercent;
    private String diskTotal;
    private String diskFree;
    private String diskUsed;
    private String gpuNumber;
    private String sliceIndex;
    private String sliceName;
    private String slicePercent;
    private String sliceTotal;
    private String sliceUsed;
    private String sliceTemp;
}
