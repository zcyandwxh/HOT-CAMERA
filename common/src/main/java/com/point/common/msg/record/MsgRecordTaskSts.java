package com.point.common.msg.record;

import com.point.common.database.domain.MstTaskSts;
import lombok.Data;

/**
 * 任务状态数据消息记录
 */
@Data
public class MsgRecordTaskSts extends MstTaskSts implements MsgRecordDepot {

}
