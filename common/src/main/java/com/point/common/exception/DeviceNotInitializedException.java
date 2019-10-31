package com.point.common.exception;

/**
 * 设备异常
 */
public class DeviceNotInitializedException extends DeviceException {

    /**
     * 构造函数
     *
     * @param devId 设备ID
     * @param message 消息
     */
    public DeviceNotInitializedException(String devId, String message) {
        super(devId, message);
    }

    /**
     * 构造函数
     *
     * @param devId 设备ID
     * @param message 消息
     * @param cause 系统异常
     */
    public DeviceNotInitializedException(String devId, String message, Throwable cause) {
        super(devId, message, cause);
    }
}
