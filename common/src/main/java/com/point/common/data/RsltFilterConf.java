package com.point.common.data;

import lombok.Data;

/**
 * 结果筛查配置
 */
@Data
public class RsltFilterConf {

    private String filterTarget;
    private String filterCond;
    private String filterValue;

}
