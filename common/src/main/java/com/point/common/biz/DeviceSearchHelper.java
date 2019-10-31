package com.point.common.biz;

import com.point.common.consts.Constant;
import com.point.common.database.accessor.NoSqlAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 设备相关数据检索帮助类
 */
@Component
public class DeviceSearchHelper {

    /**
     * 业务共通
     */
    @Autowired
    private BizCommon bizCommon;

    /**
     * NoSql操作工具
     */
    @Autowired
    private NoSqlAccessor noSqlAccessor;

    /**
     * 根据IPC设备ID获取输入源信息
     *
     * @param ipcId IPC 设备ID
     * @return 输入源信息
     */
    public List<String> getSrcIdsByIpc(String ipcId) {

        String sql = "select SRC_ID from MST_SOURCE where SRC_TYPE = ? and IPC_DEV_ID = ?";
        return noSqlAccessor.querySingle(sql, String.class, Constant.FrontDeviceType.IPC, ipcId);
    }

//    /**
//     * 获取抓拍设备信息
//     *
//     * @param srcId 输入源ID
//     * @param devId 设备ID
//     * @return 设备信息
//     */
//    public ClientDeviceInfo getCaptureDevInfo(String srcId, String devId) {
//        ClientDeviceInfo info = new ClientDeviceInfo();
//        if (StringUtils.isEmpty(devId)) {
//            MstFront mstFront = bizCommon.getFrontDevInfo(srcId);
//            if (mstFront.getDevType() == Constant.DevType.IPC) {
//                MstProvider mstProvider = bizCommon.getProvider(mstFront.getProviderId());
//                info.setDevId(mstFront.getDevId());
//                info.setDevModel(mstFront.getDevModel());
//                info.setProvider(mstProvider.getProviderName());
//                return info;
//            }
//            throw new NotSupportedException("Impossible case.");
//        } else {
//            return getAnlSvrDevInfo(devId);
//        }
//    }

//    /**
//     * 获取分析服务器设备信息
//     *
//     * @param devId 设备ID
//     * @return 设备信息
//     */
//    @Cacheable(value = "anlSvrDevInfo")
//    public ClientDeviceInfo getAnlSvrDevInfo(String devId) {
//        ClientDeviceInfo info = new ClientDeviceInfo();
//        if (devId == null) {
//            return info;
//        }
//        MstAnlSvr mstAnlSvr = bizCommon.getAnlSvrInfo(devId);
//        MstProvider mstProvider = bizCommon.getProvider(mstAnlSvr.getProviderId());
//        info.setDevId(mstAnlSvr.getDevId());
//        info.setDevModel(mstAnlSvr.getDevModel());
//        info.setProvider(mstProvider.getProviderName());
//        return info;
//
//    }
}
