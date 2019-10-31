package com.point.common.storage.rms;

import com.alibaba.fastjson.JSON;
import com.point.common.data.StorageConfig;
import com.point.common.exception.DevelopmentException;
import com.point.common.exception.NotSupportedException;
import com.point.common.http.MultipartFileResource;
import com.point.common.http.RestClient;
import com.point.common.storage.FileStorage;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.InputStream;
import java.util.List;

/**
 * 文件存储系统实现- 流媒体服务器
 */
@Component
@Slf4j
public class RmsStorage implements FileStorage {

    /**
     * http客户端
     */
    private RestClient restClient;

    /**
     * 连接对象HTTP URL
     */
    private String httpBaseUrl;


    /**
     * {@inheritDoc}
     */
    @Override
    public void config(StorageConfig config) {
        httpBaseUrl = String.format("http://%s:%s", config.getIpAddr(), String.valueOf(config.getPort()));
        restClient = new RestClient();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String store(InputStream inputStream, String subPath, String fileName) {

        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("name", new MultipartFileResource(inputStream, fileName));

        // 文件上传
        UploadResponse response = restClient.post(httpBaseUrl,
                "/api/upload", params, UploadResponse.class);
        List<FileData> fileDataList = response.getFiles();
        if (!CollectionUtils.isEmpty(fileDataList)) {
            FileData fileData = fileDataList.get(0);
            if (!StringUtils.isEmpty(fileData.getError())) {
                throw new DevelopmentException(JSON.toJSONString(fileData));
            }
            return StringUtils.substringBeforeLast(fileData.getName(), ".");
        }
        throw new DevelopmentException("Upload failed.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputStream get(String fileId) {

        // 文件下载
        return restClient.downloadByStream(httpBaseUrl, "/api/download/".concat(fileId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(String fileId) {

        throw new NotSupportedException("Not implemented.");
    }



    /**
     * 文件上传结果
     */
    @Data
    private static class FileData {
        private String name;
        private String originalName;
        private int size;
        private String type;
        private String error;
    }

    /**
     * 文件上传返回值
     */
    @Data
    private static class UploadResponse {

        private String msg;
        private String result;
        private List<FileData> files;
    }

    /**
     * 文件下载返回值
     */
    @Data
    private static class DownloadResponse {

        private String msg;
        private String result;
    }

}
