package com.point.common.storage.weed;

import com.point.common.data.StorageConfig;
import com.point.common.exception.DevelopmentException;
import com.point.common.storage.FileStorage;
import lombok.extern.slf4j.Slf4j;
import org.lokra.seaweedfs.core.FileSource;
import org.lokra.seaweedfs.core.FileTemplate;
import org.lokra.seaweedfs.core.file.FileHandleStatus;
import org.lokra.seaweedfs.core.http.StreamResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * 文件存储系统实现- Seaweed
 */
@Component
@Slf4j
public class WeedStorage implements FileStorage {

    /**
     * 文件存储相关配置
     */
    private StorageConfig config;

    /**
     * 文件保存source
     */
    private FileSource fileSource;

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void config(StorageConfig config) {

        if (this.config == null || !this.config.equals(config)) {
            if (fileSource != null) {
                try {
                    fileSource.destroy();
                } catch (Exception e) {
                }
            }
            // 初始化
            this.config = config;
            init();
        }
    }

    /**
     * 初始化
     */
    private void init() {

        fileSource = new FileSource();
        fileSource.setHost(config.getIpAddr());
        fileSource.setPort(config.getPort());
        fileSource.setMaxConnection(100);
        fileSource.setEnableLookupVolumeCache(false);
        fileSource.setConnectionTimeout(10);
        fileSource.setIdleConnectionExpiry(30);
        try {
            fileSource.startup();
        } catch (IOException e) {
            throw new DevelopmentException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String store(InputStream inputStream, String subPath, String fileName) {

        FileTemplate template = null;
        try {
            template = new FileTemplate(fileSource.getConnection());
            FileHandleStatus status = template.saveFileByStream(fileName, inputStream);
            return status.getFileId();
        } catch (IOException e) {
            throw new DevelopmentException(e);
        } finally {
            if (template != null) {
                try {
                    template.destroy();
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputStream get(String fileId) {

        log.debug("getting -------{}----{}", fileId);
        FileTemplate template = null;
        try {
            template = new FileTemplate(fileSource.getConnection());
            StreamResponse response = template.getFileStream(fileId);
            return response.getInputStream();
        } catch (IOException e) {
            throw new DevelopmentException(e);
        } finally {
            if (template != null) {
                try {
                    template.destroy();
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(String fileId) {

        FileTemplate template = null;
        try {
            template = new FileTemplate(fileSource.getConnection());
            template.deleteFile(fileId);
            return true;
        } catch (IOException e) {
            throw new DevelopmentException(e);
        } finally {
            if (template != null) {
                try {
                    template.destroy();
                } catch (Exception e) {
                }
            }
        }
    }

//    /**
//     * 获取文件保存路径
//     *
//     * @param fileName 文件名
//     * @return 文件路径
//     */
//    private String getStorageInnerPath(String subPath, String fileName) {
//        String date = DateUtil.getCurrentDateDefaultFormat();
//        return StringUtils.defaultIfEmpty(subPath, "").concat("/").concat(date)
//                .concat("/").concat(fileName);
//    }

//    /**
//     * 获取文件保存全路径
//     *
//     * @param innerPath 系统内部路径
//     * @return 全路径
//     */
//    private String getStorageFullPath(String innerPath) {
//
//        return String.format("%s://%s:%s@%s/%s/%s", config.getProtocol(), config.getUser(), config.getPassword(), config.getIp(), config.getBasePath(), innerPath);
//
//    }
}
