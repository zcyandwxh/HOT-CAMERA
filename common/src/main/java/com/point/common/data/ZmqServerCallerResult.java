package com.point.common.data;

import lombok.Data;

/**
 * Zmq服务调用结果类
 */
@Data
public class ZmqServerCallerResult {

    private Object result;
    private int returnCode;
    private String errorMessage;
}
