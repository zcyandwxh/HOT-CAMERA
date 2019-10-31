package com.point.common.exception;

import lombok.Getter;

/**
 * 采集任务异常
 */
public class JobException extends SystemException {

    /**
     * 错误代码
     */
    @Getter
    private String errorCode;

    /**
     * 构造函数
     *
     * @param jobId   设备ID
     */
    public JobException(String jobId, String errorCode) {
        this(jobId, errorCode, null);
    }

    /**
     * 构造函数
     *
     * @param jobId   设备ID
     * @param errorCode 错误代码
     * @param cause   系统异常
     */
    public JobException(String jobId, String errorCode, Throwable cause) {
        super(String.format("%s [jobId=%s]", errorCode, jobId), cause);
        this.errorCode = errorCode;
    }

}
