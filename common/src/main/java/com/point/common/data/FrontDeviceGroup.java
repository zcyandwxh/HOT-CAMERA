package com.point.common.data;

import lombok.Data;

import java.util.List;

/**
 * 前端设备信息
 */
@Data
public class FrontDeviceGroup {

    private String platformId;
    private String groupId;
    private String groupName;
    private List<FrontDeviceGroup> subGroups;
    private List<FrontDevice> deviceList;
}
