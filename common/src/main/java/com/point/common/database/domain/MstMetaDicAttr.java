package com.point.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 数据字典属性
 */
@Data
public class MstMetaDicAttr {

    private Integer id;
    private String dattributeId;
    private String dattributeChnName;
    private String dattributeName;
    private String dattributeRange;
    private Integer dattributeViewOrder;
    private Integer isNullable;
    private Integer isUnique;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
