package com.point.common.data;

import com.point.common.database.domain.WhsFaceCap;
import lombok.Data;

import java.util.Map;

/**
 * 人脸抓拍结果数据查询结果记录
 */
@Data
public class InquiryFaceCapResultRecord extends WhsFaceCap {

    private Map<String, Object> attrs;
    private String fullImgId;
}
