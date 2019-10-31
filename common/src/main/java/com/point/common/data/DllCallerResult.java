package com.point.common.data;

import lombok.Data;

/**
 * DLL调用结果类
 */
@Data
public class DllCallerResult {

    private Object result;
    private int returnCode;
    private String errorMessage;
}
