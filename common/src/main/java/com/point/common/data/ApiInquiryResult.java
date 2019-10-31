package com.point.common.data;

import lombok.Data;

/**
 * API 返回结果父类
 */
@Data
public class ApiInquiryResult extends ApiResult {

    private Long dataCount;
    private Long startIndex;
    private Long endIndex;
}
