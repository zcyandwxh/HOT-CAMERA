package com.point.common.data;

import lombok.Data;

/**
 * 目标车辆配置
 */
@Data
public class TargetVehicleConf {

    private String targetId;
    private String targetDb;
    private String plateNum;

    private String fileBase64;
    private String fileName;
    private String fileId;

    private String file1Base64;
    private String file1Name;
    private String file1Id;

    private String file2Base64;
    private String file2Name;
    private String file2Id;

    private String file3Base64;
    private String file3Name;
    private String file3Id;

    private String description;
    private String createTime;
    private Integer batchAction;
}
