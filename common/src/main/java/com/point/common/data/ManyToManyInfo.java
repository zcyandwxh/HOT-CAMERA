package com.point.common.data;

import lombok.Data;

/**
 * 人脸静态比对N：N前台参数
 */
@Data
public class ManyToManyInfo {
    /**
     * 比对底库1
     */
    private String db1;

    /**
     * 比对底库2
     */
    private String db2;

    /**
     * 相似度阈值
     */
    private String score;

    /**
     * 比对结果返回最大件数
     */
    private String topNum;
}
