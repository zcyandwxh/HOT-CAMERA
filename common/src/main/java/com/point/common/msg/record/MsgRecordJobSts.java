package com.point.common.msg.record;

import com.point.common.data.JobConf;
import lombok.Data;

import java.util.List;

/**
 * 布控任务状态数据消息记录
 */
@Data
public class MsgRecordJobSts implements MsgRecordDepot {

    private List<JobConf> jobConfList;
}
