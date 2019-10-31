package com.point.common.database.domainyt;

import lombok.Data;

/**
 * 人脸比对结果
 */
@Data
public class FaceRecognPic {

    private Integer id;
    private byte[] CapPic;
}
