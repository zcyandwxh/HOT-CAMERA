package com.point.common.data;

import lombok.Data;

/**
 * 原图数据信息
 */
@Data
public class ClientOriginResult {
    private FileStorageInfo img;
    private FileStorageInfo video;
}
