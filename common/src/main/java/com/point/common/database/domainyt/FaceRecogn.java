package com.point.common.database.domainyt;

import lombok.Data;

import java.util.Date;

/**
 * 人脸识别结果
 */
@Data
public class FaceRecogn {

    private Integer id;
    private Integer DeviceId;
    private Integer ChannelId;
    private Integer CandiateNum;
    private Integer FullImageId;
    private Integer FaceImageId;
    private Date CapTime;
    private Date InsertTime;
    private String Note;
}
