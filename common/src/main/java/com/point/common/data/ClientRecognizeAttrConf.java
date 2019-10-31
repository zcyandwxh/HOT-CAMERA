package com.point.common.data;

import lombok.Data;

import java.util.Map;

/**
 * 识别结果属性定义信息
 */
@Data
public class ClientRecognizeAttrConf {

    private Map<String, ClientAttrConf> attrConf;
}
