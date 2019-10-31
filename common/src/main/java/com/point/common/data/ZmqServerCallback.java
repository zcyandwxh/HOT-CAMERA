package com.point.common.data;

import lombok.Data;

import java.util.List;

/**
 * Zmq服务调用结果类
 */
@Data
public class ZmqServerCallback {

    private List<byte[]> result;
}
