package com.point.common.storage.smb;

import com.point.common.data.StorageConfig;
import com.point.common.exception.DevelopmentException;
import com.point.common.storage.FileStorage;
import com.point.common.util.DateUtil;
import com.point.common.util.FileUtil;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * 文件存储系统实现- Samba
 */
@Component
@Slf4j
@Scope("prototype")
public class SmbStorage implements FileStorage {

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
        String fullPath = getStorageFullPath(innerPath);
        OutputStream outputStream = null;
        try {
            log.debug("saving ---- {}", fullPath);
            SmbFile remoteFile = new SmbFile(fullPath);
            makeDirs(new SmbFile(remoteFile.getParent()));
            remoteFile.connect();
            outputStream = new BufferedOutputStream(new SmbFileOutputStream(remoteFile));
            FileUtil.toFile(inputStream, outputStream);
        } catch (IOException e) {
            throw new DevelopmentException(e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error("file close error.", fullPath, e);
                }
            }
        }
        return innerPath;
    }

    /**
     * 创建文件夹
     *
     * @param dir 文件夹
     */
    private void makeDirs(SmbFile dir) {

        try {
            dir.connect();
            if (!dir.exists()) {
                dir.mkdirs();
            }
        } catch (IOException e) {
            throw new DevelopmentException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputStream get(String innerPath) {

        String fullPath = getStorageFullPath(innerPath);
        log.debug("getting -------{}----{}", innerPath, fullPath);
        try {
            SmbFile remoteFile = new SmbFile(fullPath);
            remoteFile.connect();
            return new BufferedInputStream(new SmbFileInputStream(remoteFile));
        } catch (IOException e) {
            throw new DevelopmentException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(String innerPath) {
        String fullPath = getStorageFullPath(innerPath);
        try {
            SmbFile remoteFile = new SmbFile(fullPath);
            remoteFile.connect();
            remoteFile.delete();
            return true;
        } catch (IOException e) {
            throw new DevelopmentException(e);
        }
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

        return String.format("%s://%s:%s@%s/%s/%s", config.getProtocol(), config.getUser(),
                config.getPassword(), config.getIpAddr(), config.getBasePath(), innerPath);

    }
}
