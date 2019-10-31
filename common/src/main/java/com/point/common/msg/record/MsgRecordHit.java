package com.point.common.msg.record;

import lombok.Data;

/**
 * 命中数据
 */
@Data
public class MsgRecordHit extends MsgRecordComp {

    private MsgRecordCompDetail compDetail;
}
