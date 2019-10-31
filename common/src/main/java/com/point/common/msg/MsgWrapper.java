package com.point.common.msg;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消息数据包装类
 */
@Data
@NoArgsConstructor
public class MsgWrapper {

    /**
     * 消息常量-无
     */
    public static final MsgWrapper NONE = new MsgWrapper(-1, null, null);

    /**
     * 时间
     */
    private String time;

    /**
     * 数据类型
     */
    private int dataType;

    /**
     * 消息内容
     */
    private Object message;

    /**
     * 图片Base64
     */
    private String imageBase64;

    /**
     * 消息内容的class
     */
    private String dataClass;

    /**
     * 消息组Key
     */
    private String msgSetKey;

    /**
     * 识别方案ID
     */
    private String recogPlanId;

    /**
     * 比对方案ID
     */
    private String compPlanId;

    /**
     * 是否由结构化服务器抓拍
     */
    private boolean isCapByRecog;

    /**
     * 构造函数
     *
     * @param dataType 数据类型
     * @param message 消息内容
     * @param imageBase64 图片Base64
     * @param msgSetKey 消息组Key
     */
    public MsgWrapper(int dataType, Object message, String imageBase64, String msgSetKey) {
        this.dataType = dataType;
        this.message = message;
        if (message != null){
            this.dataClass = message.getClass().getCanonicalName();
        }
        this.imageBase64 = imageBase64;
        this.msgSetKey = msgSetKey;
    }

    /**
     * 构造函数
     *
     * @param dataType 数据类型
     * @param message 消息内容
     * @param imageBase64 图片Base64
     */
    public MsgWrapper(int dataType, Object message, String imageBase64) {
        this(dataType, message, imageBase64, null);
    }

}
