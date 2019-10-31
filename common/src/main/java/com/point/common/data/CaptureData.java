package com.point.common.data;

import lombok.Data;

/**
 * 查询抓拍比对记录页面数据
 */
@Data
public class CaptureData {
    // 追踪id
    private String trackIdx;
    // 抓拍图片
    private String faceImgUrl;
    // 抓拍时间
    private String time;
    // 抓拍质量
    private String quality;
}
