package com.point.common.data;

import lombok.Data;

/**
 * MediaCoder服务调用结果类
 */
@Data
public class MediaCoderResult {

    private int returnCode;
    private String errorMessage;
}
