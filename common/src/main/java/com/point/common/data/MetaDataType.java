package com.point.common.data;

import lombok.Data;

/**
 * 元数据-数据类型
 */
@Data
public class MetaDataType {

    private String typeCode;
    private String tableName;
    private String groupName;
    private String chnShowName;
    private Integer order;
    private Integer isBaseType;
    private Integer isEnable;
    private String createTime;
    private Integer isDict;
    private String alterTime;
    private Integer isAllQuery;
    private Integer isDataAna;
    private Integer isPosition;
    private Integer isOut;
    private Integer displayOrder;
}
