package com.point.common.data;

import lombok.Data;

/**
 * 视频抽取条件
 */
@Data
public class VideoDownloadCondition {

    private String timeFr;
    private String timeTo;
    private long delay;

    private String frtDevId;
}
