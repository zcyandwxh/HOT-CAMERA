package com.point.common.data;

import lombok.Data;

import java.util.List;

/**
 * 视频抽取任务
 */
@Data
public class MediaExtractTask {

    private String taskId;
    private int status;
    private List<FileStorageInfo> files;
    private TimeInfo time;
    private SourceLocation location;
    private ClientDeviceInfo device;
}
