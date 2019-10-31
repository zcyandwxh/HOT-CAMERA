package com.point.common.data;

import lombok.Data;

/**
 * 任务流程配置
 */
@Data
public class TaskFlowConf {

    private Integer seq;
    private String name;
    private String progId;
    private Integer isEnabled;
    private Integer retryCnt;
    private Integer isContinue;

}
