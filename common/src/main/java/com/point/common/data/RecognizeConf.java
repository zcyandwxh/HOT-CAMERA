package com.point.common.data;

import lombok.Data;

import java.util.List;

/**
 * 图片识别配置
 */
@Data
public class RecognizeConf {

    private Integer planId;
    private String planName;
    private Integer isEnable;
    private List<AnlSvrId> anlSvrIds;
}
