package com.point.webservice.facade;

import com.point.common.biz.BizCommon;
import com.point.common.biz.BizResult;
import com.point.common.data.StorageConfig;
import com.point.common.storage.weed.WeedStorage;
import com.point.common.util.RandomNameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * @author huixing
 * @description weedFS操作服务处理
 * @date 2019/10/29
 */
public interface WeedFSService {

    /**
     * 圖片存儲方法
     *
     * @param picInputStream 圖片輸入流
     */
    BizResult<String> storagePic(InputStream picInputStream);

    // TODO 优化配置

    /**
     * 进行文件系统初始化操作
     */
    void init() throws Exception;

    /**
     * 获取图片
     * @param fileId 文件ID
     * @return
     */
    BizResult<InputStream> getPic(String fileId);

    /**
     * 删除图片
     * @param fileId 文件ID
     */
    BizResult<Boolean> deletePic(String fileId);
}
