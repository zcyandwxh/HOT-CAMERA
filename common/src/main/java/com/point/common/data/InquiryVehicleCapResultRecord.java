package com.point.common.data;

import com.point.common.database.domain.WhsVehCap;
import lombok.Data;

import java.util.Map;

/**
 * 车辆抓拍结果数据查询结果记录
 */
@Data
public class InquiryVehicleCapResultRecord extends WhsVehCap {

    private Map<String, Object> attrs;
    private String fullImgId;

}
