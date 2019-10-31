package com.point.common.data;

import lombok.Data;

/**
 * 人体目标
 */
@Data
public class TargetBodyConf {

    private String targetId;
    private String targetDb;
    private String alias;

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
