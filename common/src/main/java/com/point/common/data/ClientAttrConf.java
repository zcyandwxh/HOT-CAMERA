package com.point.common.data;

import lombok.Data;

import java.util.Map;

/**
 * 属性定义信息
 */
@Data
public class ClientAttrConf {

    private String dicTableName;
    private String dicCode;
    private Map<String, String> codeConf;
}
