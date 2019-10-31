package com.point.common.data;

import lombok.Data;

/**
 * 区域分组
 */
@Data
public class DeviceAreagrp {

    private String grpId;
    private String grpName;
    private String parentId;
    private String rootId;
    private String description;

}
