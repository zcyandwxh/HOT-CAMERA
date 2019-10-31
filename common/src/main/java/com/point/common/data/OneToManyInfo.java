package com.point.common.data;

import lombok.Data;

/**
 * 人脸静态比对1：N前台参数
 */
@Data
public class OneToManyInfo {
    private String repoId;
    private String score;
    private String topNum;
    private int type;
}
