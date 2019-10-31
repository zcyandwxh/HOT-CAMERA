package com.point.common.data;

import lombok.Data;

/**
 * 静态比对（N：N）比对结果信息
 */
@Data
public class FaceCrossResult {
    /**
     * 比对底库1
     */
    private String db1;

    /**
     * 比对目标ID1
     */
    private String targetId1;

    /**
     * 比对底库2
     */
    private String db2;

    /**
     * 比对目标ID2
     */
    private String targetId2;

    /**
     * 比对结果分数
     */
    private double score;
}
