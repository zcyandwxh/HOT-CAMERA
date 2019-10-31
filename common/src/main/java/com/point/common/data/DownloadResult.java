package com.point.common.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * API 返回结果父类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DownloadResult extends WebServiceResult {

    private int returnCode;
    private String errorMessage;
    private int downloadSeq;
    private int pos;
    private String devListStr;
    private String realStreamUrl;
}
