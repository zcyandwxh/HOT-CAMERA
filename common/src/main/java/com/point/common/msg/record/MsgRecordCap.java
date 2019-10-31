package com.point.common.msg.record;

import lombok.Data;

import java.util.Map;

/**
 * 抓拍结果
 */
@Data
public class MsgRecordCap extends MsgRecordDepotCommon {

    private String recogDevId;
    private String recogDevDesc;
    private String imgId;
    private String videoId;
    private String feature;
    private String searchSvr;
    private String searchDb;
    private String searchObjId;
    private String recogQuality;
    private String fullDatId;
    private String direction;
    private String dataType;
    private String externalImgId;
    private Map<String, Object> attrs;
}
