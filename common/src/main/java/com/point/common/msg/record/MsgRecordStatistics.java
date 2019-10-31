package com.point.common.msg.record;

import lombok.Data;

/**
 * 统计数据消息记录
 */
@Data
public class MsgRecordStatistics implements MsgRecordDepot {

    private String time;
    private Integer count;
}
