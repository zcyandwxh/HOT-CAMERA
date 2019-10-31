package com.point.common.exception;

import lombok.Data;

/**
 * 参数错误异常
 */
@Data
public class SameNameException extends SystemException {

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
    public SameNameException(String message) {
        super(message);
    }

    /**
     * 构造函数
     *
     * @param messageCode 错误代码
     * @param messagesParams 错误信息
     */
    public SameNameException(String messageCode, String... messagesParams) {
        super(messageCode);
        this.messageCode = messageCode;
        this.messagesParams = messagesParams;
    }

}
