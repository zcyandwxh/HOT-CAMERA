package com.point.common.data;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 输入源位置信息
 */
@Data
public class SourceLocation {

    private BigDecimal lng;


    private BigDecimal lat;


    private String pcode;


    private String place;
}
