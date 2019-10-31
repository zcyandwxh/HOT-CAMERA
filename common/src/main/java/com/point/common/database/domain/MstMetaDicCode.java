package com.point.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 数据字典代码
 */
@Data
public class MstMetaDicCode {

    private Integer id;
    private String dicGroup;
    private String keyCode;
    private String valueContent;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
