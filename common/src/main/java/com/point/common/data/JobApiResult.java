package com.point.common.data;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * API 返回结果父类
 */
@Data
@NoArgsConstructor
public class JobApiResult extends ApiResult {

    public JobApiResult(String result) {
        this.result = result;
    }

    private int returnCode;
    private String errorMessage;

    private String result;
}
