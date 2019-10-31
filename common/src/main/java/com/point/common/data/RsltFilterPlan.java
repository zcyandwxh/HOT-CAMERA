package com.point.common.data;

import lombok.Data;

import java.util.List;

/**
 * 结果筛查方案
 */
@Data
public class RsltFilterPlan {

    private Integer planId;
    private Integer dataType;

    private Integer action;
    private Integer isEnabled;
    private List<RsltFilterConf> rsltFilterConfs;
}
