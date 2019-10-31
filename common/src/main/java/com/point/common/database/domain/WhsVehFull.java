package com.point.common.database.domain;

import lombok.Data;

/**
 * 车辆抓拍原图
 */
@Data
public class WhsVehFull {

    private String id;
    private String captureTime;
    private String trackIdx;
    private String jobId;
    private String frtDevId;
    private String frtDevDesc;
    private String capDevId;
    private String capDevDesc;
    private String longitude;
    private String latitude;
    private String placeCode;
    private String place;
    private String imgId;
    private String createUser;
    private String createTime;
    private String updateUser;
    private String updateTime;
}
