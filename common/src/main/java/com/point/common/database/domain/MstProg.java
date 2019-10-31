package com.point.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 程序基础信息
 */
@Data
public class MstProg {

    private Integer id;
    private String progId;
    private String progName;
    private Integer progType;
    private String progVer;
    private String description;
    private String progPath;
    private Integer isBit32;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
