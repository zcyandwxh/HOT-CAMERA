package com.point.common.exception;

/**
 * 升级异常
 */
public class UpgradeException extends SystemException {

    /**
     * 构造函数
     *
     * @param message 消息
     */
    public UpgradeException(String message) {
        super(message);
    }

    /**
     * 构造函数
     *
     * @param message 消息
     * @param cause 系统异常
     */
    public UpgradeException(String message, Throwable cause) {
        super(message, cause);
    }
}
