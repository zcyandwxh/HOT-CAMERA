package com.point.common.data;

import lombok.Data;

/**
 * 静态比对（1：N）比对结果信息
 */
@Data
public class FaceGroupResult {
    /**
     * 比对底库名
     */
    private String db;

    /**
     * 比对目标ID
     */
    private String targetId;

    /**
     * 比对结果分数
     */
    private double score;
}
