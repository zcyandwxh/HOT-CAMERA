package com.point.common.msg.record;

import lombok.Data;

/**
 * 抓拍比对相关数据共通
 */
@Data
public class MsgRecordDepotCommon implements MsgRecordDepot {

    private String id;
    private String captureTime;
    private String trackIdx;
    private String jobId;
    private String srcType;
    private String frtDevId;
    private String frtDevDesc;
    private String capDevId;
    private String capDevDesc;

    private String longitude;
    private String latitude;
    private String placeCode;
    private String place;

    private String createUser;
    private String createTime;
    private String updateUser;
    private String updateTime;

}
