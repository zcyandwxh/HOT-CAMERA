package com.point.common.data;

import com.point.common.database.domain.MstTaskFlowConf;
import com.point.common.msg.record.MsgRecordDepot;
import lombok.Data;

import java.util.List;

/**
 * 任务信息
 */
@Data
public class TaskProcessParam implements MsgRecordDepot {

    private String taskId;
    private Integer subNum;
    private String commonParam;
    private String firstParam;
    private List<MstTaskFlowConf> procFlowConfs;
}
