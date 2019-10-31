package com.point.common.data;

import lombok.Data;

/**
 * 设备适配程序信息
 */
@Data
public class DeviceProgramInfo {

    private String devId;
    private String progId;
    private String progVer;
    private String program;
    private String programParam;
    private Integer is32bit;
}
