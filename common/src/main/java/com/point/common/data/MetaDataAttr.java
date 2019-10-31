package com.point.common.data;

import lombok.Data;

/**
 * 元数据-数据属性
 */
@Data
public class MetaDataAttr {

    private String attrId;
    private String attributeType;
    private String columnName;
    private String chnShowName;
    private Integer isVisible;
    private Integer queryType;
    private Integer isQueryNullable;
    private Integer isDict;
    private String dictionaryCode;
    private String length;
    private Integer isNullAble;
    private Integer order;
    private String orderType;
    private Integer drivenType;
    private Integer isQueryAlfa;
    private Integer queryOrder;
    private Integer isHitOut;
    private String insertTime;
    private String alterTime;
}
