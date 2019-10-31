package com.point.common.data;

import lombok.Data;

import java.util.List;

/**
 * 图片对比配置
 */
@Data
public class CompareConf {

    private Integer planId;
    private String planName;
    private Integer isEnable;
    private List<SvrTarget> anlSvrTargets;
}
