package com.point.common.database.domainyt;

import lombok.Data;

/**
 * 人脸识别设备
 */
@Data
public class FaceRecognDevice {

    private Integer id;
    private String Name;
    private String Loc;
    private String Ip;
    private Integer Port;
    private String UserName;
    private String Password;
    private String Note;
}
