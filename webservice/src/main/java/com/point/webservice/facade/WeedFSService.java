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
@Component
public class WeedFSService {


    /**
     * 文件系统工具类
     */
    @Autowired
    private WeedStorage weedStorage;

    /**
     * 圖片存儲方法
     *
     * @param picInputStream 圖片輸入流
     */
    public BizResult<String> storagePic(InputStream picInputStream) {
        int fileName = RandomNameUtil.getNum(0, 100000000);
        String url = weedStorage.store(picInputStream, "", fileName + "");

        // TODO 审核数据库之后进行入库操作

        return BizResult.create(url);
    }

    // TODO 优化配置

    /**
     * 进行文件系统初始化操作
     */
    public void init() throws Exception{
        StorageConfig storageConfig = new StorageConfig();
        storageConfig.setIpAddr("192.168.95.101");
        storageConfig.setPort(9333);
        weedStorage.config(storageConfig);
    }

    /**
     * 获取图片
     * @param fileId 文件ID
     * @return
     */
    public BizResult<InputStream> getPic(String fileId){

        InputStream picInputStream = weedStorage.get(fileId);

        return BizResult.create(picInputStream);
    }

    /**
     * 删除图片
     * @param fileId 文件ID
     */
    public BizResult<Boolean> deletePic(String fileId)
    {
        boolean deleteFlag = weedStorage.delete(fileId);

        return BizResult.create(deleteFlag);
    }
}
