package com.point.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 数据类型
 */
@Data
public class MstMetaDatType {

    private Integer id;
    private String typeCode;
    private String tableName;
    private String groupName;
    private String chnShowName;
    private Integer displayOrder;
    private Integer isBaseType;
    private Integer isEnable;
    private Integer isDict;
    private Integer isAllQuery;
    private Integer isDataAna;
    private Integer isPosition;
    private Integer isOut;
    private Date insertTime;
    private Date alterTime;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
