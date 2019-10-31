package com.point.common.exception;

import lombok.Data;

/**
 * 处理对象数据不存在错误异常
 */
@Data
public class TargetDataNotExistException extends SystemException {

    /**
     * 错误代码
     */
    private String messageCode;

    /**
     * 错误信息
     */
    private String[] messagesParams;

    /**
     * 构造函数
     *
     * @param message 消息
     */
    public TargetDataNotExistException(String message) {
        super(message);
    }

    /**
     * 构造函数
     *
     * @param messageCode 错误代码
     * @param messagesParams 错误信息
     */
    public TargetDataNotExistException(String messageCode, String... messagesParams) {
        super(messageCode);
        this.messageCode = messageCode;
        this.messagesParams = messagesParams;
    }

}
