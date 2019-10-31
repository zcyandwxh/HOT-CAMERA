package com.point.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 数据属性
 */
@Data
public class MstMetaDatAttr {

    private Integer id;
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
    private Integer displayOrder;
    private String orderType;
    private Integer drivenType;
    private Integer isQueryAlfa;
    private Integer queryOrder;
    private Integer isHitOut;
    private Date insertTime;
    private Date alterTime;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
