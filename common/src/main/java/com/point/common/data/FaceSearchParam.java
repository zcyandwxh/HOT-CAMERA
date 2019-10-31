package com.point.common.data;

import lombok.Data;

/**
 * 人脸以图搜图条件
 */
@Data
public class FaceSearchParam {

    private String fileId;
    private String externalImgId;
    private String operationId;
    private String user;
    private long totalCount;

    private String timeFr;
    private String timeTo;
    private String frtDevId;
    private String lngFr;
    private String latFr;
    private String lngTo;
    private String latTo;
    private String pcode;
    private String place;
    private String qualityFr;
    private String qualityTo;

}
