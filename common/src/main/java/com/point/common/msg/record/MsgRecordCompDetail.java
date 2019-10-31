package com.point.common.msg.record;

import lombok.Data;

/**
 * 比对结果详细
 */
@Data
public class MsgRecordCompDetail {

    private String id;
    private String index;
    private String targetDb;
    private String targetId;
    private String targetImgId;
    private String targetName;
    private String targetIdentity;
    private String score;

}
