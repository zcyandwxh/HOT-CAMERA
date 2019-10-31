package com.point.common.data;

import lombok.Data;

/**
 * 运维服务器能力接口
 */
@Data
public class ServerAbility {
    private String ipAddr;
    private String devId;
    private String devName;
    private String ability;
    private int abilityCode;
    private long faceMaxCount;
    private long vehicleMaxCountl;
    private long videoMaxCount;
    private long videoUsedCount;
}
