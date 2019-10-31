package com.point.common.storage;



import com.point.common.data.StorageConfig;

import java.io.InputStream;

/**
 * 文件存储系统接口
 */
public interface FileStorage {

    void config(StorageConfig conf);

    String store(InputStream imgDataStream, String subPath, String fileName);

    InputStream get(String path);

    boolean delete(String path);
}
