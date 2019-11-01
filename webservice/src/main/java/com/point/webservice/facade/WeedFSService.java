//package com.point.webservice.facade;
//
//import com.point.common.data.StorageConfig;
//import com.point.common.storage.weed.WeedStorage;
//import com.point.common.util.RandomNameUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.stereotype.Component;
//
//import java.io.BufferedInputStream;
//import java.io.FileInputStream;
//import java.io.InputStream;
//
///**
// * @author huixing
// * @description weedFS操作工具類
// * @date 2019/10/29
// */
//@Component
//public class WeedFSService {
//
//    @Autowired
//    private WeedStorage weedStorage;
//
//    /**
//     * 初始化WeedStorage
//     */
//    public WeedFSService() {
//        // init storage
////        StorageConfig storageConfig = new StorageConfig();
////
////        weedStorage.config(storageConfig);
//    }
//
//    /**
//     * 圖片存儲方法
//     *
//     * @param picInputStream 圖片輸入流
//     */
//    public void storagePic(InputStream picInputStream) {
//        int fileName = RandomNameUtil.getNum(0, 10000000);
//        weedStorage.store(picInputStream, "", fileName + "");
//    }
//
//    public void init() {
//        StorageConfig storageConfig = new StorageConfig();
//        weedStorage.config(storageConfig);
//    }
//}
