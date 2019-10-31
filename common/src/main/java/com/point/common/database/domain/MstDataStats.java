package com.point.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 数据统计
 */
@Data
public class MstDataStats {

    private Integer id;
    private Integer dataType;
    private Integer dataDate;
    private Integer dataHour;
    private Integer dataCount;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
