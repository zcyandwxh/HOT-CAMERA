package com.point.common.data;

import com.point.common.database.domain.WhsFaceSearch;
import lombok.Data;

import java.util.Map;

/**
 * 人脸比对结果数据查询结果记录
 */
@Data
public class InquiryFaceCompResultRecord extends WhsFaceSearch {

    private Map<String, Object> attrs;
    private String fullImgId;
}
