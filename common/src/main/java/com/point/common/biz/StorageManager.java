package com.point.common.biz;

import com.point.common.cache.BizCache;
import com.point.common.config.FileConfig;
import com.point.common.consts.Constant;
import com.point.common.data.FileWrapper;
import com.point.common.data.StorageConfig;
import com.point.common.database.accessor.NoSqlAccessor;
import com.point.common.database.accessor.RDBAccessor;
import com.point.common.database.domain.WhsFileStorage;
import com.point.common.exception.DevelopmentException;
import com.point.common.exception.NotSupportedException;
import com.point.common.storage.FileStorage;
import com.point.common.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 存储系统管理类
 */
@Component
@Slf4j
public class StorageManager {

    /**
     * 协议符号
     */
    private static final String PROTOCOL_MARK = "://";

    /**
     * 默认文件用户
     */
    private static final String DEFAULT_USER = "default";

    /**
     * 管理DB访问器
     */
    @Autowired
    private RDBAccessor rdbAccessor;

    /**
     * 数据仓库DB访问器
     */
    @Autowired
    private NoSqlAccessor noSqlAccessor;

    /**
     * 文件存储相关设定
     */
    @Autowired
    private FileConfig fileConfig;

    /**
     * 缓存管理器
     */
    @Autowired
    private BizCache bizCache;

    /**
     * 文件存储
     *
     * @param stream     输入流
     * @param user       用户
     * @param type       文件类型
     * @param uploadType 文件上传类型
     * @param fileName   文件名
     * @param attrs      文件属性
     * @return 文件ID
     */
    public List<String> store(InputStream stream, String user, Integer type,
                              Integer uploadType, String fileName, Map<String, Object> attrs) {
        return store(stream, user, type, uploadType, fileName, attrs, null);
    }

    /**
     * 文件存储
     *
     * @param stream     输入流
     * @param user       用户
     * @param type       文件类型
     * @param uploadType 文件上传类型
     * @param fileName   文件名
     * @param attrs      文件属性
     * @param fileId     文件ID
     * @return 文件ID
     */
    public List<String> store(InputStream stream, String user, Integer type,
                              Integer uploadType, String fileName, Map<String, Object> attrs, String fileId) {

        // 获取当前存储配置
        List<StorageConfig> configs = getCurrentStorageConf();
        if (configs == null || configs.size() == 0) {
            throw new NotSupportedException("Storage configuration is not found.");
        }

        // 文件类型
        Integer fileType = type == null ? getFileType(fileName) : type;

        List<String> fileIds = new ArrayList<>();
        for (StorageConfig config : configs) {

            // 判断文件类型
            if (!fileType.equals(config.getFileType())) {
                continue;
            }
            // 获取存储工具类
            FileStorage storage = getFileStorage(config);

            // 存储
            RetryHelper retryHelper = new RetryHelper(5, 3000);
            retryHelper.execute(() -> {
                try {
                    String filePath = storage.store(stream, user, fileName);
                    // 保存相应记录
                    String id = storeDatabase(filePath, user, fileType, uploadType, fileName, attrs, config, fileId);

                    fileIds.add(id);
                    return true;
                } catch (Throwable e) {
                    log.warn("Error when store to storage.", e);
                    return false;
                }
            }, null, () -> {
                throw new DevelopmentException("Error when store to storage.");
            });

            // 由于InputStream不能重复利用，只处理第一条存储通道。
            break;
        }
        return fileIds;
    }

    /**
     * 获取文件
     *
     * @param fileId 文件Id
     * @return 文件
     */
    public FileWrapper get(String fileId) {

        WhsFileStorage fileInfo = getFileInfo(fileId);
        if (fileInfo == null) {
            return null;
        }
        String chanelId = getChanelFromFileUrl(fileInfo.getFilePath());
        String path = getPathFromFileUrl(fileInfo.getFilePath());

        StorageConfig config = getStorageConf(chanelId);
        FileStorage fileStorage = getFileStorage(config);

        FileWrapper result = new FileWrapper();
        RetryHelper retryHelper = new RetryHelper(5, 3000);
        retryHelper.execute(() -> {
            try {
                result.setFileInputStream(fileStorage.get(path));
                return true;
            } catch (Throwable e) {
                log.error("Error when get from storage.", e);
                return false;
            }
        }, null, () -> {
            throw new DevelopmentException("Error when get from storage.");
        });
        result.setFileName(fileInfo.getFileName());
        return result;
    }

    /**
     * 获取文件真实路径
     *
     * @param fileId 文件Id
     * @return 文件路径
     */
    public String getRealPath(String fileId) {

        WhsFileStorage fileInfo = getFileInfo(fileId);
        if (fileInfo == null) {
            return null;
        }
        return getPathFromFileUrl(fileInfo.getFilePath());
    }

    /**
     * 检查文件是否存在
     *
     * @param fileId 文件Id
     * @return 文件是否存在
     */
    public boolean check(String fileId) {

        WhsFileStorage fileInfo = getFileInfo(fileId);
        if (fileInfo == null) {
            return false;
        }
        return true;
    }

    /**
     * 删除文件
     *
     * @param fileId 文件Id
     */
    public boolean delete(String fileId) {

        WhsFileStorage fileInfo = getFileInfo(fileId);
        String chanelId = getChanelFromFileUrl(fileInfo.getFilePath());
        String path = getPathFromFileUrl(fileInfo.getFilePath());

        StorageConfig config = getStorageConf(chanelId);
        FileStorage fileStorage = getFileStorage(config);
        return fileStorage.delete(path);
    }

    /**
     * 保存文件记录
     *
     * @param filePath   文件路径
     * @param user       用户
     * @param type       文件类型
     * @param uploadType 文件上传类型
     * @param fileName   文件名
     * @param attrs      文件属性
     * @param config     存储配置
     * @param fileId     文件ID
     * @return 文件记录ID
     */
    private String storeDatabase(String filePath, String user, Integer type, Integer uploadType, String fileName,
                                 Map<String, Object> attrs, StorageConfig config, String fileId) {

        // 保存在DB中的路径
        String dbPath = makeFileUrl(filePath, config);
        // 用户
        String fileUser = StringUtils.isEmpty(user) ? DEFAULT_USER : user;
        // 文件上传类型
        int fileUploadType = uploadType == null ? Constant.FileUploadType.OTHER : uploadType;
        // 保存时间
        String time = DateUtil.getCurrentTimeDefaultFormat();
        // 文件ID（若参数中的FileId不为空则生成）
        String id = StringUtils.isEmpty(fileId) ? makeFileId(time, null, fileUser, type) : fileId;

        WhsFileStorage whsFileStorage = new WhsFileStorage();
        // 反映属性
        if (attrs != null) {
            ReflectionUtil.populateBean(whsFileStorage, attrs, null);
        }
        // 属性以外字段设定
        whsFileStorage.setId(id);
        whsFileStorage.setFileTime(time);
        whsFileStorage.setFileUser(fileUser);
        whsFileStorage.setFileType(String.valueOf(type));
        whsFileStorage.setUploadType(String.valueOf(fileUploadType));
        whsFileStorage.setFilePath(dbPath);
        whsFileStorage.setFileName(fileName);
        whsFileStorage.setCreateTime(time);
        whsFileStorage.setCreateUser(fileUser);
        whsFileStorage.setUpdateUser(fileUser);
        whsFileStorage.setUpdateTime(time);

        noSqlAccessor.insert("WHS_FILE_STORAGE", whsFileStorage);
        return id;
    }

    /**
     * 根据配置信息获取存储工具类
     *
     * @param config 配置信息
     * @return 工具类
     */
    private FileStorage getFileStorage(StorageConfig config) {
        return bizCache.getFileStorage(config);
    }

    /**
     * 根据通道ID获取配置信息
     *
     * @param chanelId 通道ID
     * @return 配置信息
     */
    private StorageConfig getStorageConf(String chanelId) {

        return bizCache.getStorageConf(chanelId);
    }

    /**
     * 获取当前配置下的所有通道
     *
     * @return 通道信息
     */
    private List<StorageConfig> getCurrentStorageConf() {

        return bizCache.getCurrentStorageConf();
    }

    /**
     * 根据文件ID获取文件储存信息
     *
     * @param fileId 文件ID
     * @return 文件储存信息
     */
    private WhsFileStorage getFileInfo(String fileId) {
        String sql = "select FILE_PATH, FILE_NAME from WHS_FILE_STORAGE where ID = ? ";
        return noSqlAccessor.queryForObject(sql, WhsFileStorage.class, fileId);
    }

    /**
     * 构建存储用文件URL
     *
     * @param filePath 文件路径
     * @param config   存储配置
     * @return 存储用文件路径
     */
    private String makeFileUrl(String filePath, StorageConfig config) {
        return config.getChanelId().concat(PROTOCOL_MARK).concat(filePath);
    }

    /**
     * 根据文件URL获取通道ID
     *
     * @param fileUrl 文件URL
     * @return 通道ID
     */
    private String getChanelFromFileUrl(String fileUrl) {
        int pos = fileUrl.indexOf(PROTOCOL_MARK);
        return StringUtils.left(fileUrl, pos);
    }

    /**
     * 根据文件URL获取路径
     *
     * @param fileUrl 文件URL
     * @return 路径
     */
    private String getPathFromFileUrl(String fileUrl) {
        return StringUtils.substringAfter(fileUrl, PROTOCOL_MARK);
    }

    /**
     * 根据文件后缀名判断文件类型
     *
     * @param fileName 文件名
     * @return 文件类型
     */
    public int getFileType(String fileName) {

        // 若没有文件名，则视作“其他”
        if (StringUtils.isEmpty(fileName)) {
            return Constant.FileType.OTHER;
        }

        // 获取后缀名
        String ext = FileUtil.getFileExtName(fileName);
        // 若没有后缀名，则视作“其他”
        if (StringUtils.isEmpty(ext)) {
            return Constant.FileType.OTHER;
        }

        // 后缀名转换为小写
        ext = StringUtils.lowerCase(ext);

        // 视频
        String video = StringUtils.lowerCase(fileConfig.getFileTypeVideo());
        if (!StringUtils.isEmpty(video) && ArrayUtils.contains(video.split(","), ext)) {
            return Constant.FileType.VIDEO;
        }

        // 图片
        String image = StringUtils.lowerCase(fileConfig.getFileTypeImage());
        if (!StringUtils.isEmpty(image) && ArrayUtils.contains(image.split(","), ext)) {
            return Constant.FileType.IMAGE;
        }

        // Word
        String word = StringUtils.lowerCase(fileConfig.getFileTypeWord());
        if (!StringUtils.isEmpty(word) && ArrayUtils.contains(word.split(","), ext)) {
            return Constant.FileType.WORD;
        }

        // Excel
        String excel = StringUtils.lowerCase(fileConfig.getFileTypeExcel());
        if (!StringUtils.isEmpty(excel) && ArrayUtils.contains(excel.split(","), ext)) {
            return Constant.FileType.EXCEL;
        }

        // 文本
        String text = StringUtils.lowerCase(fileConfig.getFileTypeText());
        if (!StringUtils.isEmpty(text) && ArrayUtils.contains(text.split(","), ext)) {
            return Constant.FileType.TEXT;
        }

        return Constant.FileType.OTHER;
    }

    /**
     * 生成文件ID
     *
     * @param fileUser 文件用户
     * @param type     文件类型
     * @param time     时间
     * @return 文件ID
     */
    public String makeFileId(String time, String random, String fileUser, Integer type) {
        String userStr = StringUtils.isEmpty(fileUser) ? DEFAULT_USER : fileUser;
        String randomStr = StringUtils.isEmpty(random) ? StringUtil.getRandomNumber(6) : random;

        // 文件ID
        return time.concat(StringUtils.leftPad(randomStr, 6, '0'))
                .concat(StringUtils.leftPad(userStr, 10, '0'))
                .concat(StringUtils.leftPad(String.valueOf(type), 2, '0'));
    }
}
