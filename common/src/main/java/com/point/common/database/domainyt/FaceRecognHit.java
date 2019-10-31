package com.point.common.database.domainyt;

import lombok.Data;

import java.util.Date;

/**
 * 人脸比对结果
 */
@Data
public class FaceRecognHit {

    private Integer id;
    private Integer DeviceId;
    private Integer RecordId;
    private Integer ChannelId;
    private Integer TargetId;
    private Integer FullImageId;
    private Integer FaceImageId;
    private Integer TargetImageId1;
    private Integer TargetImageId2   ;
    private Integer TargetImageId3;
    private Integer TargetImageId4;
    private Integer Similar;
    private Date CapTime;
    private Date InsertTime;
    private String Note;
}
