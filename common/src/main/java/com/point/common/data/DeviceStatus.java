package com.point.common.data;

import lombok.Data;

/**
 * 设备运行状态
 */
@Data
public class DeviceStatus {

    private Integer type;
    private String devId;
    private String name;
    private Integer status;
    private String statusTime;
    private String ext1;
    private String ext2;
    private String ext3;
}
