package com.point.common.data;

import lombok.Data;

/**
 * 系统设置
 */
@Data
public class SystemSet {

    private String confKey;

    private String confName;

    private String confValue;

    private String description;
}
