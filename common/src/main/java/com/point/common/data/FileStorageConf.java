package com.point.common.data;

import lombok.Data;

import java.util.List;

/**
 * 文件存储配置
 */
@Data
public class FileStorageConf {
    private String id;
    private Integer planId;
    private String planName;
    private Integer isEnable;
    private String startTime;
    private String endTime;
    private List<Confs> confs;
}
