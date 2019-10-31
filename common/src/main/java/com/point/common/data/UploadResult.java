package com.point.common.data;

import lombok.Data;

/**
 * 文件上传返回结果
 */
@Data
public class UploadResult extends StorageResult {

    private String fileId;
}
