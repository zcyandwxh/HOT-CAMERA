package com.point.common.data;

import lombok.Data;

import java.util.List;

/**
 * 属性数据转换方案
 */
@Data
public class AttrTransPlan {

    private Integer planId;
    private String planName;
    private Integer dataType;
    private String startTime;
    private String endTime;
    private String description;
    private List<AttrTrans> attrTrans;
}
