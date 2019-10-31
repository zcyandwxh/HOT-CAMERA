package com.point.common.exception;

/**
 * 系统异常
 */
public class ViewDepotException extends RuntimeException {

    /**
     * 构造函数
     *
     * @param message 消息
     */
    public ViewDepotException(String message) {
        super(message);
    }

    /**
     * 构造函数
     *
     * @param cause 系统异常
     */
    public ViewDepotException(Throwable cause) {
        super(cause);
    }

    /**
     * 构造函数
     *
     * @param message 消息
     * @param cause 系统异常
     */
    public ViewDepotException(String message, Throwable cause) {
        super(message, cause);
    }
}

