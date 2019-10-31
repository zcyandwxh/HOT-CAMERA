package com.point.common.data;

import com.point.common.database.domain.MstStorageChanel;
import lombok.Getter;
import lombok.Setter;

/**
 * 文件存储配置
 */
public class StorageConf extends MstStorageChanel {
    /**
     * 文件类型
     */
    @Getter
    @Setter
    private Integer fileType;
}
