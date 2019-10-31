package com.point.common.storage.local;

import com.point.common.data.StorageConfig;
import com.point.common.storage.FileStorage;
import com.point.common.util.DateUtil;
import com.point.common.util.FileUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;

/**
 * 文件存储系统实现- 本地存储
 */
@Component
@Scope("prototype")
public class LocalStorage implements FileStorage {

    /**
     * 文件存储相关配置
     */
    private StorageConfig config;

    /**
     * {@inheritDoc}
     */
    @Override
    public void config(StorageConfig config) {
        this.config = config;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String store(InputStream inputStream, String subPath, String fileName) {

        String innerPath = getStorageInnerPath(subPath, fileName);
        FileUtil.toFile(inputStream, getStorageFullPath(innerPath));
        return innerPath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputStream get(String innerPath) {
        return FileUtil.fromFile(getStorageFullPath(innerPath));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(String innerPath) {
        return FileUtil.deleteFile(getStorageFullPath(innerPath));
    }

    /**
     * 获取文件保存路径
     *
     * @param fileName 文件名
     * @return 文件路径
     */
    private String getStorageInnerPath(String subPath, String fileName) {
        String date = DateUtil.getCurrentDateDefaultFormat();
        return StringUtils.defaultIfEmpty(subPath, "").concat("/").concat(date)
                .concat("/").concat(fileName);
    }

    /**
     * 获取文件保存全路径
     *
     * @param innerPath 系统内部路径
     * @return 全路径
     */
    private String getStorageFullPath(String innerPath) {

        return FileUtil.patchDirPath(config.getBasePath())
                .concat(StringUtils.isEmpty(innerPath) ? "": innerPath.replace("/", File.separator));
    }
}
