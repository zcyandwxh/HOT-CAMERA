package com.point.common.data;

import lombok.Data;

/**
 * 视频转码参数
 */
@Data
public class VideoTransCodeParam {

    private boolean isLocalFile;
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

    private Integer uploadType;
}
