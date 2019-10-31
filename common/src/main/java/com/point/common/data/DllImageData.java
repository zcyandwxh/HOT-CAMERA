package com.point.common.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 图片数据结构
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DllImageData {

//    private byte[] image;
    private String imageBase64;
    private String imageAttrs;
//    private String dataClass;



//    public <T> T getImageAttrs() {
//        return (T)imageAttrs;
//    }

//    /**
//     * 转化为JSON字符串
//     * @return JSON字符串
//     */
//    public String toJsonString() {
//        return JSON.toJSONString(this);
//    }

//    /**
//     * 从JSON字符串转化
//     *
//     * @param json JSON字符串
//     */
//    public static ImageData fromJsonString(String json, Class<?> attrClazz) {
//
//        ImageData imageData = JSON.parseObject(json, ImageData.class);
//        if (imageData.getImageAttrs() != null) {
//            // 将JSON Object转换为JSON字符串
//            String imageAttrStr = JSON.toJSONString(imageData.getImageAttrs());
//            // 将JSON字符串转化为相应的JavaObject
//            imageData.setImageAttrs(JSON.parseObject(imageAttrStr, attrClazz));
//        }
//        return imageData;
//    }
}
