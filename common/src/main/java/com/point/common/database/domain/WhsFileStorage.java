package com.point.common.database.domain;

import lombok.Data;

/**
 * 文件存储
 */
@Data
public class WhsFileStorage {

    private String id;
    private String fileType;
    private String uploadType;
    private String fileTime;
    private String fileUser;
    private String filePath;
    private String fileName;
    private String fileSize;
    private String title;
    private String keyword;
    private String summary;
    private String description1;
    private String description2;
    private String description3;
    private String createUser;
    private String createTime;
    private String updateUser;
    private String updateTime;
}
