package com.point.common.data;

import lombok.Data;

/**
 * 人脸数据查询结果前台显示推送用
 */
@Data
public class ClientCaptureData {

    private TimeInfo time;
    private SourceLocation location;

    private String recogImgStr;
    private String originImgStr;

    private RecognizeResult recogRslt;
    private ClientOriginResult origin;
}
