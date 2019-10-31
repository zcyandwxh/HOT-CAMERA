package com.point.common.data;

import lombok.Data;

/**
 * 元数据-数据字典属性
 */
@Data
public class MetaDataDicAttr  {

    private String dattributeId;
    private String dattributeChnName;
    private String dattributeName;
    private String dattributeRange;
    private Integer dattributeViewOrder;
    private Integer isNullable;
    private Integer isUnique;
}


