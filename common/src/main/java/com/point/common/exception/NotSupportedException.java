package com.point.common.exception;

/**
 * 未被实现异常
 */
public class NotSupportedException extends ViewDepotException {

    /**
     * 构造函数
     *
     * @param message 消息
     */
    public NotSupportedException(String message) {
        super(message);
    }
}
