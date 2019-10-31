package com.point.common.data;

import lombok.Data;

/**
 * 视频抽取结果
 */
@Data
public class VideoDownloadResult extends TaskInfo {

    private String frtDevId;
    private String videoId;
}
