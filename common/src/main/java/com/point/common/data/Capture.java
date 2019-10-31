package com.point.common.data;

import lombok.Data;

/**
 * 抓拍
 */
@Data
public class Capture {
    private String startTime;
    private String endTime;
    private String limit;
    private String startIdx;
    private String taskIds;
    private String srcIds;
}
