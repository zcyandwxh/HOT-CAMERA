package com.point.common.data;

import lombok.Data;

/**
 * 文件上传返回结果
 */
@Data
public class StorageFileUploadResult extends ApiResult {

    private String fileId;
}
