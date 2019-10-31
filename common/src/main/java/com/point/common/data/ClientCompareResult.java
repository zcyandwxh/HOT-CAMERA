package com.point.common.data;

import lombok.Data;

/**
 * 比较结果信息
 */
@Data
public class ClientCompareResult {
    private String db;
    private String targetId;
    private String score;
    private FileStorageInfo img;
}
