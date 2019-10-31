package com.point.common.data;

import lombok.Data;

/**
 * 对象位置信息
 */
@Data
public class ObjectPositionInfo {

    private int posLeft;
    private int posTop;
    private int posRight;
    private int posBottom;
    private String callerId;
    private String jobId;
    private String srcId;
}
