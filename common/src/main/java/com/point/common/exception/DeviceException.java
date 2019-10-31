package com.point.common.exception;

/**
 * 设备异常
 */
public class DeviceException extends SystemException {

    /**
     * 构造函数
     *
     * @param devId 设备ID
     * @param message 消息
     */
    public DeviceException(String devId, String message) {
        this(devId, message,  null);
    }

    /**
     * 构造函数
     *
     * @param devId 设备ID
     * @param message 消息
     * @param cause 系统异常
     */
    public DeviceException(String devId, String message, Throwable cause) {
        super(String.format("%s [devId=%s]", message, devId), cause);
    }

}
