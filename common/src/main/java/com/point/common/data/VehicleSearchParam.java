package com.point.common.data;

import com.point.common.database.domain.WhsVehCap;
import lombok.Data;

import java.util.List;

/**
 * 车辆以图搜图条件
 */
@Data
public class VehicleSearchParam {

    private String imgBase64;
    private List<String> plateNumbers;
    private List<WhsVehCap> vehDataList;
    private String operationId;
    private String user;
    private long totalCount;
}
