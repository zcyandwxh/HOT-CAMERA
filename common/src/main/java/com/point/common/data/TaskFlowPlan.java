package com.point.common.data;

import lombok.Data;

import java.util.List;

/**
 * 任务流程方案
 */
@Data
public class TaskFlowPlan {

    private Integer planId;
    private String name;
    private Integer type;
    private Integer isEnabled;
    private List<TaskFlowConf> taskFlowConfs;
}
