package com.point.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 区域分组
 */
@Data
public class MstAreaGrp {

    private Integer id;
    private String rootId;
    private String grpId;
    private String grpName;
    private String parentId;
    private String description;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
