package com.point.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 数据字典
 */
@Data
public class MstMetaDic {

    private Integer id;
    private String dictionaryCode;
    private String dicTableName;
    private String dictionaryName;
    private String dictionaryGid;
    private Integer dictionaryKey;
    private Integer dictionaryValue;
    private Integer dictionaryState;
    private String description;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
