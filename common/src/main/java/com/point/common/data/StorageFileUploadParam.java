package com.point.common.data;

import lombok.Data;

/**
 * 文件上传参数
 */
@Data
public class StorageFileUploadParam extends ApiParameter {

    private String fileBase64;
    private String fileName;
    private Integer fileType;
    private String fileUser;
    private String title;
    private String keyword;
    private String summary;
    private String description1;
    private String description2;
    private String description3;

    private Integer uploadType;
}
