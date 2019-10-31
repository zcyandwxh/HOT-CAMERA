package com.point.common.exception;

import lombok.Data;

/**
 * Created by public on 2018/9/17.
 */
@Data
public class SameNoException extends SystemException {
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
    public SameNoException(String message) {
        super(message);
    }

    /**
     * 构造函数
     *
     * @param messageCode 错误代码
     * @param messagesParams 错误信息
     */
    public SameNoException(String messageCode, String... messagesParams) {
        super(messageCode);
        this.messageCode = messageCode;
        this.messagesParams = messagesParams;
    }

}
