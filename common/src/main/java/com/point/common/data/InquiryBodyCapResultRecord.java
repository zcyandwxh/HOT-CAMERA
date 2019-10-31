package com.point.common.data;

import com.point.common.database.domain.WhsBodyCap;
import lombok.Data;

import java.util.Map;

/**
 * 人体抓拍结果数据查询结果记录
 */
@Data
public class InquiryBodyCapResultRecord extends WhsBodyCap {

    private Map<String, Object> attrs;
    private String fullImgId;

}
