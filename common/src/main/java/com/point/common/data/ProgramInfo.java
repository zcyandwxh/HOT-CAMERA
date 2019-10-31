package com.point.common.data;

import lombok.Data;

/**
 * 程序信息
 */
@Data
public class ProgramInfo {

    private String progId;
    private Integer progType;
    private String progName;
    private String progVer;
    private String description;

    private String progPath;

    private Integer is32bit;
}
