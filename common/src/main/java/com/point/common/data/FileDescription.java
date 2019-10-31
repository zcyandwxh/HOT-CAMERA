package com.point.common.data;

import lombok.Data;

/**
 * 文件描述信息
 */
@Data
public class FileDescription {

    private String fileId;
    private String filePath;
    private String fileName;
    private String fileUser;
    private String title;
    private String keyword;
    private String summary;
    private String description1;
    private String description2;
    private String description3;
    private Integer fileUploadType;

}
