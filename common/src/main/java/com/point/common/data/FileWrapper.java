package com.point.common.data;

import lombok.Data;

import java.io.InputStream;

/**
 * 文件包装类
 */
@Data
public class FileWrapper {

    private String fileName;
    private String fileBase64;
    private String description;
    private byte[] fileContent;
    private InputStream fileInputStream;

}
