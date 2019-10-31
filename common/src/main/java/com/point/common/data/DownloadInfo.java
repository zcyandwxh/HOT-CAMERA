package com.point.common.data;

import lombok.Data;

/**
 * 下载信息
 */
@Data
public class DownloadInfo {

    private String channelId;
    private String startTime;
    private String endTime;
    private String fileName;
    private int downloadSeq;

}
