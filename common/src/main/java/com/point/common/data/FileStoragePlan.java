package com.point.common.data;

import lombok.Data;

/**
 * 文件存储方案
 */
@Data
public class FileStoragePlan {

    private String id;
    private Integer planId;
    private String fileType;
    private String startTime;
    private String endTime;

}
