package com.point.common.biz;

import com.alibaba.fastjson.JSON;
import com.point.common.application.AppContextSupport;
import com.point.common.cache.BizCache;
import com.point.common.consts.Constant;
import com.point.common.data.DeviceConnection;
import com.point.common.data.DeviceProgramInfo;
import com.point.common.data.JobConf;
import com.point.common.database.accessor.RDBAccessor;
import com.point.common.database.domain.*;
import com.point.common.database.tools.FieldNameConverter;
import com.point.common.exception.NotSupportedException;
import com.point.common.msg.MsgWrapper;
import com.point.common.msg.converter.MsgConverter;
import com.point.common.msg.record.MsgRecordCap;
import com.point.common.msg.record.MsgRecordComp;
import com.point.common.msg.record.MsgRecordDepot;
import com.point.common.msg.record.MsgRecordDepotCommon;
import com.point.common.util.DateUtil;
import com.point.common.util.IpUtil;
import com.point.common.util.ReflectionUtil;
import com.point.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 各任务的业务共通处理
 */
@Slf4j
@Component
public class BizCommon {

    /**
     * MySQL模糊检索匹配字符
     */
    private static final char[] MYSQL_LIKE_CHARS = {'@', '_'};

    /**
     * DB访问工具类实例
     */
    @Autowired
    private RDBAccessor dbAccessor;

    /**
     * 应用存储系统
     */
    @Autowired
    private TemporaryStorage temporaryStorage;

    /**
     * 消息格式转换类
     */
    @Autowired
    private MsgConverter msgConverter;

    /**
     * 字段名转换器
     */
    @Autowired
    private FieldNameConverter nameConverter;

    /**
     * 文件存储工具类
     */
    @Autowired
    private StorageManager storageManager;

    /**
     * 缓存管理器
     */
    @Autowired
    private BizCache bizCache;

    /**
     * 根据输入源ID获取输入源信息
     *
     * @return 输入源信息
     */
    public List<MstFront> getAllFrontDevInfo() {

        return bizCache.getAllFrontDevInfo();
    }

    /**
     * 根据输入源ID获取输入源信息
     *
     * @param frtDevId 输入源ID
     * @return 输入源信息
     */
    public MstFront getFrontDevInfo(String frtDevId) {

        return bizCache.getFrontDevInfo(frtDevId);
    }

    /**
     * 获取分析服务器信息
     *
     * @return 分析服务器信息
     */
    public List<MstAnlSvr> getAllAnlSvrInfo() {

        return bizCache.getAllAnlSvrInfo();
    }

    /**
     * 根据分析服务器ID获取分析服务器信息
     *
     * @param svrId 分析服务器ID
     * @return 分析服务器信息
     */
    public MstAnlSvr getAnlSvrInfo(String svrId) {

        return bizCache.getAnlSvrInfo(svrId);
    }

    /**
     * 获取所有具有比对能力的分析服务器信息
     *
     * @return 分析服务器信息
     */
    public List<MstAnlSvr> getAnlSvrInfoByAbility(int... abilities) {

        return bizCache.getAnlSvrInfoByAbility((int[]) abilities);
    }

    /**
     * 根据平台ID获取平台服务器信息
     *
     * @param svrId 平台服务器ID
     * @return 平台服务器信息
     */
    public MstPlatform getPlatformInfo(String svrId) {

        return bizCache.getPlatformInfo(svrId);
    }

    /**
     * 根据流媒体服务器ID获取流媒体服务器信息
     *
     * @param svrId 流媒体服务器ID
     * @return 流媒体服务器信息
     */
    public MstRmsSvr getRmsSvrInfo(String svrId) {

        return bizCache.getRmsSvrInfo(svrId);
    }

    /**
     * 根据转码服务器ID获取转码服务器信息
     *
     * @param svrId 转码服务器ID
     * @return 转码服务器信息
     */
    public MstMcSvr getMcSvrInfo(String svrId) {

        return bizCache.getMcSvrInfo(svrId);
    }

    /**
     * 根据识别方案ID获取识别方案配置
     *
     * @param recogPlanId 识别方案ID
     * @return 识别方案配置
     */
    public List<MstAnlSvr> getRecogPlanInfos(String recogPlanId) {

        return bizCache.getRecogPlanInfos(recogPlanId);
    }

    /**
     * 根据数据类型、动作类型获取筛选配置
     *
     * @param dataType     数据类型
     * @param filterAction 动作类型
     * @return 筛选配置
     */
    public List<MstRsltFilterConf> getResultFilterConf(int dataType, int filterAction) {

        return bizCache.getResultFilterConf(dataType, filterAction);
    }

    /**
     * 根据流程类型获取相关配置
     *
     * @param flowType 流程类型
     * @return 流程配置
     */
    public List<MstTaskFlowConf> getTaskFlowConf(int flowType) {
        return bizCache.getTaskFlowConf(flowType);
    }

    /**
     * 根据对比方案ID获取对比方案配置
     *
     * @param compPlanId 对比方案ID
     * @return 对比方案配置
     */
    public List<MstAnlSvr> getCompPlanInfos(String compPlanId) {

        return bizCache.getCompPlanInfos(compPlanId);
    }


    /**
     * 获取前端设备的连接信息
     *
     * @param frtDevId 输入源ID
     * @return 设备连接信息
     */
    public DeviceConnection getFrtDevConn(String frtDevId) {

        return bizCache.getFrtDevConn(frtDevId);

    }

    /**
     * 获取分析服务器设备的连接信息
     *
     * @param devId 服务器ID
     * @return 设备连接信息
     */
    public DeviceConnection getAnlSvrConn(String devId) {

        return bizCache.getAnlSvrConn(devId);
    }

    /**
     * 获取平台服务器设备的连接信息
     *
     * @param devId 平台服务器ID
     * @return 设备连接信息
     */
    public DeviceConnection getPlatformConn(String devId) {

        DeviceConnection conn = bizCache.getPlatformConn(devId);
        String ip = conn.getIpAddr();
        // 将域名转换为IP(海康平台SDK不支持域名）
        if (!IpUtil.isIpAddress(ip)) {
            ip = IpUtil.getIpByName(ip);
            conn.setIpAddr(ip);
        }
        return conn;
    }

    /**
     * 获取流媒体服务器设备的连接信息
     *
     * @param devId 服务器ID
     * @return 设备连接信息
     */
    public DeviceConnection getRmsSvrConn(String devId) {

        return bizCache.getRmsSvrConn(devId);
    }

    /**
     * 获取转码服务器设备的连接信息
     *
     * @param devId 服务器ID
     * @return 设备连接信息
     */
    public DeviceConnection getMcSvrConn(String devId) {

        return bizCache.getMcSvrConn(devId);
    }


    /**
     * 获取最新的连接程序
     *
     * @return 最新的连接程序
     */
    public DeviceProgramInfo getLatestProgByDev(int devType, String devId) {

        return bizCache.getLatestProgByDev(devType, devId);
    }

    /**
     * 获取最新版本程序
     *
     * @param progId  程序ID
     * @param version 程序版本
     * @return 程序信息
     */
    public MstProg getLastProg(String progId, String version) {

        return bizCache.getLastProg(progId, version);
    }

    /**
     * 获取结构化相关配置
     *
     * @param planId   方案ID
     * @param dataType 数据类型
     * @return 结构化相关配置
     */
    public List<MstRecogConf> getRecogConfig(String planId, int dataType) {

        return bizCache.getRecogConfig(planId, dataType);
    }

    /**
     * 获取结构化相关配置
     *
     * @param planId   方案ID
     * @param dataType 数据类型
     * @return 结构化相关配置
     */
    public List<MstCompConf> getCompConfig(String planId, int dataType) {

        return bizCache.getCompConfig(planId, dataType);

    }

    /**
     * 获取比对对象图片路径
     *
     * @param targetDB 对比底库ID
     * @param devId    对比设备ID
     * @param objId    对比对象ID
     * @return 图片路径
     */
    public MstFaceTarget getFaceCompareTarget(String targetDB, String devId, String objId) {

        return bizCache.getFaceCompareTarget(targetDB, devId, objId);
    }

    /**
     * 获取比对对象图片路径
     *
     * @param targetDB 对比底库ID
     * @param devId    对比设备ID
     * @param objId    对比对象ID
     * @return 图片路径
     */
    public MstBodyTarget getBodyCompareTarget(String targetDB, String devId, String objId) {

        return bizCache.getBodyCompareTarget(targetDB, devId, objId);
    }

    /**
     * 获取比对对象图片路径
     *
     * @param objId    对比对象ID
     * @return 图片路径
     */
    public MstFaceTarget getFaceCompareTargetWithoutTargetDb(String objId) {

        return bizCache.getFaceCompareTargetWithoutTargetDb(objId);
    }

    /**
     * 获取比对对象图片路径
     *
     * @param targetDB 对比底库ID
     * @param devId    对比设备ID
     * @param objId    对比对象ID
     * @return 图片路径
     */
    public MstFaceTarget getFaceCompareTargetStandard(String targetDB, String devId, String objId) {

        return bizCache.getFaceCompareTargetStandard(targetDB, devId, objId);
    }

    /**
     * 获取指定服务器中所有人脸比对底库Id
     *
     * @param devId 对比设备ID
     * @return 底库Id
     */
    public List<String> getAllFaceCompareTargetDbId(String devId) {

        return bizCache.getAllFaceCompareTargetDbId(devId);
    }

    /**
     * 获取指定服务器中人脸比对底库Id
     *
     * @param targetDb 比对底库
     * @param devId    对比设备ID
     * @return 底库Id
     */
    public String getFaceCompareTargetDbId(String targetDb, String devId) {

        return bizCache.getFaceCompareTargetDbId(targetDb, devId);
    }

    /**
     * 获取指定服务器中人体比对底库Id
     *
     * @param targetDb 比对底库
     * @param devId    对比设备ID
     * @return 底库Id
     */
    public String getBodyCompareTargetDbId(String targetDb, String devId) {

        return bizCache.getBodyCompareTargetDbId(targetDb, devId);
    }

    /**
     * 获取所有人脸比对底库名
     *
     * @return 对比底库名
     */
    public List<String> getAllFaceCompareTargetDB() {

        String sql = "select TARGET_DB from MST_FACE_TARGET_DB";
        return dbAccessor.querySingle(sql, String.class);
    }

    /**
     * 获取所有人脸比对目标
     *
     * @param targetDb 比对底库
     * @param devId    对比设备ID
     * @return 所有人脸比对目标
     */
    public List<MstFaceTargetObj> getAllFaceCompareTarget(String targetDb, String devId) {

        return bizCache.getAllFaceCompareTarget(targetDb, devId);
    }

    /**
     * 根据设备ID设定前端设备相关信息
     *
     * @param data     数据
     * @param frtDevId 前端设备ID
     */
    public void setFrontDevInfo(MsgRecordDepotCommon data, String frtDevId) {

        // 检索前端设备表
        MstFront mstFront = bizCache.getFrontDevInfo(frtDevId);
        data.setLongitude(mstFront.getLongitude() == null ? null
                : StringUtil.padDecimalStr(mstFront.getLongitude().toPlainString(), 10, '0'));
        data.setLatitude(mstFront.getLatitude() == null ? null
                : StringUtil.padDecimalStr(mstFront.getLatitude().toPlainString(), 10, '0'));
        data.setPlaceCode(mstFront.getPlaceCode());
        data.setPlace(mstFront.getPlace());
        data.setFrtDevId(frtDevId);
        data.setFrtDevDesc(mstFront.getDevName());
    }

    /**
     * 根据设备ID设定抓拍设备相关信息
     *
     * @param data     数据
     * @param capDevId 抓拍设备ID
     */
    public void setCapDevInfo(MsgRecordDepotCommon data, String capDevId) {

        if (StringUtils.isEmpty(capDevId) || capDevId.equals(data.getFrtDevId())) {

            // 检索前端设备表
            MstFront mstFront = bizCache.getFrontDevInfo(data.getFrtDevId());
            data.setCapDevDesc(mstFront.getDevName());
            data.setCapDevId(mstFront.getDevId());

        } else {
            // 检索分析服务器表
            MstAnlSvr mstAnlSvr = bizCache.getAnlSvrInfo(capDevId);
            data.setCapDevDesc(mstAnlSvr.getDevName());
            data.setCapDevId(capDevId);
        }
    }

    /**
     * 根据设备ID设定结构化设备相关信息
     *
     * @param data       数据
     * @param recogDevId 抓拍设备ID
     */
    public void setRecogDevInfo(MsgRecordCap data, String recogDevId) {

        if (StringUtils.isEmpty(recogDevId)) {
            return;
        }

        // 检索分析服务器表
        MstAnlSvr mstAnlSvr = bizCache.getAnlSvrInfo(recogDevId);
        data.setRecogDevDesc(mstAnlSvr.getDevName());
        data.setRecogDevId(recogDevId);
    }

    /**
     * 根据设备ID设定比对设备相关信息
     *
     * @param data      数据
     * @param compDevId 比对设备ID
     */
    public void setCompDevInfo(MsgRecordComp data, String compDevId) {

        if (StringUtils.isEmpty(compDevId)) {
            return;
        }

        // 检索分析服务器表
        MstAnlSvr mstAnlSvr = bizCache.getAnlSvrInfo(compDevId);
        data.setCompDevDesc(mstAnlSvr.getDevName());
        data.setCompDevId(compDevId);
    }

    /**
     * 根据属性数据类型获取数据类型
     *
     * @param attributeType 属性数据类型
     */
    public Map<String, String> getCurrentDatAttrMaping(String attributeType) {

        if (StringUtils.isEmpty(attributeType)) {
            return new HashMap<>();
        }

        return bizCache.getCurrentDatAttrMaping().get(attributeType);
    }

    /**
     * 根据字典CODE获取数据字典列表
     *
     * @param dicCode 字典CODE
     */
    public Map<String, String> getCurrentDicCodeMaping(String dicCode) {

        if (StringUtils.isEmpty(dicCode)) {
            return new HashMap<>();
        }

        return bizCache.getCurrentDicCodeMaping().get(dicCode);
    }

    /**
     * 设定更新者更新日等字段
     *
     * @param record 数据
     * @param user   用户
     */
    public void setUserTimeForCreate(MsgRecordDepotCommon record, String user) {
        String now = DateUtil.getCurrentTimeDefaultFormat();
        record.setCreateTime(now);
        record.setCreateUser(user);
        record.setUpdateTime(now);
        record.setUpdateUser(user);
    }

    /**
     * 设定更新者更新日等字段
     *
     * @param record 数据
     * @param user   用户
     */
    public void setUserTimeForUpdate(MsgRecordDepotCommon record, String user) {
        String now = DateUtil.getCurrentTimeDefaultFormat();
        record.setUpdateTime(now);
        record.setUpdateUser(user);
    }

    /**
     * 检查人脸目标ID是否存在
     *
     * @param targetDb 人脸目标底库
     * @param targetId 人脸目标ID
     * @return 是否存在
     */
    public boolean checkFaceTargetExist(String targetDb, String targetId) {
        String sql = "select count(1) from MST_FACE_TARGET where TARGET_DB = ? and TARGET_ID = ? ";
        List<Integer> count = dbAccessor.querySingle(sql, Integer.class, targetDb, targetId);
        if (count != null && count.size() > 0) {
            return count.get(0) > 0;
        }
        return false;
    }

    /**
     * 检查车辆目标ID是否存在
     *
     * @param targetDb 车辆目标底库
     * @param targetId 车辆目标ID
     * @return 是否存在
     */
    public boolean checkVehicleTargetExist(String targetDb, String targetId) {
        String sql = "select count(1) from MST_VEH_TARGET where TARGET_DB = ? and TARGET_ID = ? ";
        List<Integer> count = dbAccessor.querySingle(sql, Integer.class, targetDb, targetId);
        if (count != null && count.size() > 0) {
            return count.get(0) > 0;
        }
        return false;
    }

    /**
     * 检查人体目标ID是否存在
     *
     * @param targetDb 人体目标底库
     * @param targetId 人体目标ID
     * @return 是否存在
     */
    public boolean checkBodyTargetExist(String targetDb, String targetId) {
        String sql = "select count(1) from MST_BODY_TARGET where TARGET_DB = ? and TARGET_ID = ? ";
        List<Integer> count = dbAccessor.querySingle(sql, Integer.class, targetDb, targetId);
        if (count != null && count.size() > 0) {
            return count.get(0) > 0;
        }
        return false;
    }

    /**
     * 读取任务启动配置,启动任务
     *
     * @param targetJobName 对象任务名
     * @return 对象任务信息
     */
    public List<JobConf> readJobConf(String targetJobName, boolean includeDisabled) {

        // 准备检索条件
        String condition;
        if (StringUtils.isEmpty(targetJobName)) {
            condition = (includeDisabled ? "" : " IS_ENABLED = 1 and ").concat(" IS_DELETED = 2 ");
        } else if (StringUtils.containsAny(targetJobName, MYSQL_LIKE_CHARS)) {
            condition = (includeDisabled ? "" : " IS_ENABLED = 1 and ").concat(" IS_DELETED = 2 and MST_JOB_CONF.JOB_ID LIKE ? ");
        } else {
            condition = " MST_JOB_CONF.JOB_ID = ? ";
        }

        // 检索
        String sql = "select * from MST_JOB_CONF where ".concat(condition);
        List<JobConf> jobConfList;
        if (StringUtils.isEmpty(targetJobName)) {
            jobConfList = dbAccessor.query(sql, JobConf.class);
        } else {
            jobConfList = dbAccessor.query(sql, JobConf.class, targetJobName);
        }
        return jobConfList;
    }

    /**
     * 读取任务启动配置,启动任务
     *
     * @param targetJobName 对象任务名
     * @return 对象任务信息
     */
    public List<JobConf> readJobConf(String targetJobName) {

        return readJobConf(targetJobName, false);
    }

    /**
     * 检查指定设备是否在被采集任务使用
     *
     * @param devType 设备类型
     * @param devId   设备ID
     * @return 是否使用中
     */
    public String checkDeviceUsing(int devType, String devId) {

        // 读取所有采集任务配置
        List<JobConf> jobConfList = readJobConf(null, true);
        for (JobConf jobConf : jobConfList) {
            String jobId = jobConf.getJobId();
            // 前端设备
            if (devType == Constant.DeviceType.FRONT && devId.equals(jobConf.getFrtDevId())) {
                return jobId;

                // 分析服务器
            } else if (devType == Constant.DeviceType.ANLSVR) {

                // 当作为抓拍设备时
                if (devId.equals(jobConf.getCapDevId())) {
                    return jobId;
                }

                // 当作为比对方案中的设备时
                if (jobConf.getCompPlanId() != null) {
                    List<MstAnlSvr> anlSvrList = getCompPlanInfos(String.valueOf(jobConf.getCompPlanId()));
                    for (MstAnlSvr mstAnlSvr : anlSvrList) {
                        if (devId.equals(mstAnlSvr.getDevId())) {
                            return jobId;
                        }
                    }
                }

                // 当作为结构化方案中的设备时
                if (jobConf.getRecogPlanId() != null) {
                    List<MstAnlSvr> anlSvrList = getRecogPlanInfos(String.valueOf(jobConf.getRecogPlanId()));
                    for (MstAnlSvr mstAnlSvr : anlSvrList) {
                        if (devId.equals(mstAnlSvr.getDevId())) {
                            return jobId;
                        }
                    }
                }

                // 平台
            } else if (devType == Constant.DeviceType.PLATFORM) {

                MstFront front = getFrontDevInfo(jobConf.getFrtDevId());
                if (!StringUtils.isEmpty(front.getPlatformId())) {
                    MstPlatform platform = getPlatformInfo(front.getPlatformId());
                    if (devId.equals(platform.getDevId())) {
                        return jobId;
                    }
                }
            }
        }
        return null;
    }

//    /**
//     * 获取数据推送任务信息
//     *
//     * @return 数据推送任务信息
//     */
//    public List<BizDataPushJob> getDataPushJob(int dataType) {
//
//        Map<Integer, List<BizDataPushJob>> all = getAllDataPushJob();
//        return all.get(dataType);
//    }

//    /**
//     * 获取所有数据推送任务信息
//     *
//     * @return 数据推送任务信息
//     */
//    @Cacheable(value = "allDataPushJob")
//    public Map<Integer, List<BizDataPushJob>> getAllDataPushJob() {
//
//        String sql = "select * from BIZ_DATA_PUSH_JOB ";
//        List<BizDataPushJob> allJobs = dbAccessor.query(sql, BizDataPushJob.class);
//
//        Map<Integer, List<BizDataPushJob>> all = new HashMap<>();
//        for (BizDataPushJob job : allJobs) {
//            int dataType = job.getDataType();
//            List<BizDataPushJob> jobsByDataType = all.get(dataType);
//            if (jobsByDataType == null) {
//                jobsByDataType = new ArrayList<>();
//                all.put(dataType, jobsByDataType);
//            }
//            jobsByDataType.add(job);
//        }
//        return all;
//    }


    /**
     * 生成原图数据表ID
     *
     * @param trackIdx    追踪ID
     * @param captureTime 抓图时间
     * @param srcId       输入源ID
     * @return ID
     */
    public String makeFullDatId(String captureTime, String trackIdx, String srcId) {

        // 图片抓拍时间(yyyyMMddHHmmssSSS) + 追踪ID + 输入源ID
        return captureTime
                .concat(StringUtils.leftPad(StringUtils.right(trackIdx, 3), 3, '0'))
                .concat(StringUtils.leftPad(StringUtils.right(srcId, 14), 14, '0'));
    }

    /**
     * 生成人脸数据表ID
     *
     * @param trackIdx    追踪ID
     * @param captureTime 抓图时间
     * @param srcId       输入源ID
     * @return ID
     */
    public String makeFaceDatId(String captureTime, String trackIdx, String srcId) {

        return makeFaceDatId(captureTime, trackIdx, srcId, null);
    }

    /**
     * 生成人脸数据表ID
     *
     * @param trackIdx    追踪ID
     * @param captureTime 抓图时间
     * @param srcId       输入源ID
     * @param idx         索引
     * @return ID
     */
    public String makeFaceDatId(String captureTime, String trackIdx, String srcId, String idx) {

        // 图片抓拍时间(yyyyMMddHHmmssSSS) + 追踪ID + 输入源ID
        return captureTime
                .concat(StringUtils.leftPad(StringUtils.right(trackIdx, 3), 3, '0'))
                .concat(StringUtils.leftPad(StringUtils.right(srcId, 14), 14, '0'))
                .concat(StringUtils.leftPad(idx == null ? "1" : idx, 2, '0'));
    }

    /**
     * 生成车辆数据表ID
     *
     * @param trackIdx    追踪ID
     * @param captureTime 抓图时间
     * @param srcId       输入源ID
     * @return ID
     */
    public String makeVehDatId(String captureTime, String trackIdx, String srcId) {
        return makeVehDatId(captureTime, trackIdx, srcId, null);
    }

    /**
     * 生成车辆数据表ID
     *
     * @param trackIdx    追踪ID
     * @param captureTime 抓图时间
     * @param srcId       输入源ID
     * @param idx         索引
     * @return ID
     */
    public String makeVehDatId(String captureTime, String trackIdx, String srcId, String idx) {

        // 图片抓拍时间(yyyyMMddHHmmssSSS) + 追踪ID + 输入源ID
        return captureTime
                .concat(StringUtils.leftPad(StringUtils.right(trackIdx, 3), 3, '0'))
                .concat(StringUtils.leftPad(StringUtils.right(srcId, 14), 14, '0'))
                .concat(StringUtils.leftPad(idx == null ? "1" : idx, 2, '0'));
    }

    /**
     * 生成对比结果数据表ID
     *
     * @param capFaceDatID 人脸数据ID
     * @param compDb       对比底库
     * @param index        对比结果序号
     * @return ID
     */
    public String makeCompDatId(String capFaceDatID, String compDb, int index) {

        // 由 抓拍人脸数据ID + 对比结果序号（6位数字） 构成
        String db = StringUtils.leftPad(StringUtil.nvl(compDb, ""), 10, '0');
        return capFaceDatID.concat(StringUtils.left(db, 10))
                .concat(StringUtils.leftPad(String.valueOf(index), 6, '0'));
    }

    /**
     * 生成对比结果消息ID
     *
     * @param faceDatID 人脸数据ID
     * @param compDb    对比底库
     * @return ID
     */
    public String makeCompMsgId(String faceDatID, String compDb) {

        // 由 抓拍人脸数据ID + 对比底库 构成
        return faceDatID.concat(StringUtils.leftPad(compDb, 10, '0'));
    }

    /**
     * 生成任务ID
     *
     * @return ID
     */
    public String makeTaskId() {

        // 由 时间 + 随机6位数字 构成
        return String.valueOf(DateUtil.getCurrentTimeDefaultFormat()).concat(StringUtil.getRandomNumber(6));
    }

    /**
     * 创建新任务
     *
     * @param taskId    任务ID
     * @param subNum    子任务编号
     * @param taskType  任务类型
     * @param taskParam 任务参数
     * @param dataId    任务关键数据ID
     * @param dataType  任务关键数据类型
     * @param user      创建者
     */
    public void createTask(String taskId, int subNum, int taskType, String taskParam, String dataId, Integer dataType, String user) {

        MstTaskSts taskSts = new MstTaskSts();
        Date now = DateUtil.getCurrentDate();
        taskSts.setTaskId(taskId);
        taskSts.setSubNum(subNum);
        taskSts.setType(taskType);
        taskSts.setStatus("任务等待开始");
        taskSts.setStatusCode(Constant.TaskStatusCode.PREPARED);
        taskSts.setParam(StringUtils.left(taskParam, 1000));
        taskSts.setDataId(dataId);
        //TODO 将人体数据类型转化成人脸数据类型
        taskSts.setDataType(dataType == 42 ? 22 : dataType);
        taskSts.setCreateTime(now);
        taskSts.setCreateUser(user);
        taskSts.setUpdateTime(now);
        taskSts.setUpdateUser(user);

        dbAccessor.insert("MST_TASK_STS", taskSts);
    }

    /**
     * 更新任务状态
     *
     * @param taskId     任务ID
     * @param subNum     子任务编号
     * @param status     任务状态文字
     * @param statusCode 任务状态代码
     * @param progress   任务进度
     * @param endTime    任务结束时间
     * @param error      错误信息
     * @param user       更新者
     */
    public void updateTask(String taskId, int subNum, String status, Integer statusCode,
                           Integer progress, String startTime, String endTime, String error, Object result, String user) {

        MstTaskSts taskSts = new MstTaskSts();
        taskSts.setProgress(progress);
        taskSts.setStatus(status);
        taskSts.setStatusCode(statusCode);
        taskSts.setStartTime(startTime);
        taskSts.setEndTime(endTime);
        taskSts.setUpdateTime(DateUtil.getCurrentDate());
        taskSts.setError(StringUtils.left(error, 1000));
        taskSts.setResult(result == null ? null : (result instanceof String ? (String) result : JSON.toJSONString(result)));
        taskSts.setUpdateUser(user);
        dbAccessor.update("MST_TASK_STS", ReflectionUtil.describeBean(taskSts, nameConverter),
                "TASK_ID = ? and SUB_NUM = ?", taskId, subNum);
    }


    /**
     * 获取任务状态
     *
     * @param taskId 任务ID
     * @param subNum 子任务编号
     */
    public MstTaskSts getTask(String taskId, int subNum) {
        String sql = "select * from MST_TASK_STS where TASK_ID = ? and SUB_NUM = ?";
        return dbAccessor.queryForObject(sql, MstTaskSts.class, taskId, subNum);
    }

    /**
     * 获取任务状态
     *
     * @param taskId 任务ID
     */
    public List<MstTaskSts> getTask(String taskId) {
        String sql = "select * from MST_TASK_STS where TASK_ID = ? ";
        return dbAccessor.query(sql, MstTaskSts.class, taskId);
    }

    /**
     * 判断对同一数据创建的正在进行中的任务是否存在
     *
     * @param taskType       任务类型
     * @param dataId         任务关键数据ID
     * @param dataType       任务关键数据类型
     * @param isJudgeNotOver 是否判断还未结束
     * @return 是否存在
     */
    public MstTaskSts getTaskByData(int taskType, String dataId, Integer dataType, boolean isJudgeNotOver) {

        String sql = "select * from MST_TASK_STS where TYPE = ? and DATA_ID = ? and DATA_TYPE = ? ".concat(isJudgeNotOver ? " and END_TIME IS NULL" : "");
        List<MstTaskSts> taskStsList = dbAccessor.query(sql, MstTaskSts.class, taskType, dataId, dataType);
        if (!CollectionUtils.isEmpty(taskStsList)) {
            return taskStsList.get(0);
        }
        return null;
    }

    /**
     * 获取所有未完成的任务
     *
     * @param taskType 任务类型
     * @return 任务列表
     */
    public List<MstTaskSts> getTaskNotCompleted(int taskType) {

        String sql = "select * from MST_TASK_STS where type = ? and END_TIME IS NULL";
        return dbAccessor.query(sql, MstTaskSts.class, taskType);
    }

    /**
     * 获取关联数据ID
     *
     * @param srcDataType    源数据类型
     * @param srcDataId      源数据ID
     * @param targetDataType 获取对象数据类型
     * @return 获取对象数据ID
     */
    public String getRelationDataId(int srcDataType, String srcDataId, int targetDataType) {
        if (srcDataType == targetDataType) {
            return srcDataId;
        }
        if ((srcDataType == Constant.DataType.FACE || srcDataType == Constant.DataType.FACE_RECOG)
                && targetDataType == Constant.DataType.FACE_FULL) {
            return StringUtils.left(srcDataId, Constant.DataIdLength.FACE_FULL);
        } else if (srcDataType == Constant.DataType.FACE_COMPARE &&
                (targetDataType == Constant.DataType.FACE || targetDataType == Constant.DataType.FACE_RECOG)) {
            return StringUtils.left(srcDataId, Constant.DataIdLength.FACE);
        } else if ((srcDataType == Constant.DataType.VEH || srcDataType == Constant.DataType.VEH_RECOG)
                && targetDataType == Constant.DataType.VEH_FULL) {
            return StringUtils.left(srcDataId, Constant.DataIdLength.VEH_FULL);
        } else if (srcDataType == Constant.DataType.VEH_COMPARE &&
                (targetDataType == Constant.DataType.VEH || targetDataType == Constant.DataType.VEH_RECOG)) {
            return StringUtils.left(srcDataId, Constant.DataIdLength.VEH);
        } else if ((srcDataType == Constant.DataType.BODY || srcDataType == Constant.DataType.BODY_RECOG)
                && targetDataType == Constant.DataType.BODY_FULL) {
            return StringUtils.left(srcDataId, Constant.DataIdLength.BODY_FULL);
        } else if (srcDataType == Constant.DataType.BODY_COMPARE &&
                (targetDataType == Constant.DataType.BODY || targetDataType == Constant.DataType.BODY_RECOG)) {
            return StringUtils.left(srcDataId, Constant.DataIdLength.BODY);
        }
        throw new NotSupportedException(String.format("Wrong data type.src:%s target:%s", srcDataType, targetDataType));
    }

    /**
     * 消息数据转化
     *
     * @param msgWrapperStr 消息数据字符串
     * @return 消息数据
     */
    public MsgWrapper unpackMessage(String msgWrapperStr) {

        // 此时 msgWrapper.getMessage() 为JSON Object
        MsgWrapper msgWrapper = msgConverter.unpackObject(msgWrapperStr, MsgWrapper.class);

        // 将JSON Object转换为JSON字符串
        String messageStr = msgConverter.packObject(msgWrapper.getMessage());

        // 将JSON字符串转化为相应的JavaObject
        Class<? extends MsgRecordDepot> clazz = AppContextSupport.loadClass(msgWrapper.getDataClass(), true);
        MsgRecordDepot realMessage = msgConverter.unpackObject(messageStr, clazz);
        msgWrapper.setMessage(realMessage);

        return msgWrapper;
    }

    /**
     * 获取表中指定字段的最大值
     *
     * @param table  表名
     * @param column 字段名
     * @return 最大值
     */
    public String getMaxValue(String table, String column) {

        String sql = "select max(".concat(column).concat(") ").concat(column).concat(" from ").concat(table);
        List<String> maxValues = dbAccessor.querySingle(sql, String.class);
        if (maxValues != null && maxValues.size() > 0) {
            return maxValues.get(0);
        }
        return null;
    }

//    /**
//     * 根据图片ID获取URL
//     *
//     * @param id 图片ID
//     * @return URL
//     */
//    public String makeFileUrlFromId(String id) {
//
//        if (StringUtils.isEmpty(id)) {
//            return id;
//        }
//        return String.format("http://%s:%s%s/%s", getHostName(), serverConfig.getApiHostPort(), serverConfig.getApiHostImg(), id);
//    }

//    /**
//     * 获取主机名
//     *
//     * @return 主机名
//     */
//    @Cacheable(value = "hostName")
//    private String getHostName() {
//        try {
//            return InetAddress.getLocalHost().getHostName();
//        } catch (UnknownHostException var1) {
//            throw new DevelopmentException(var1);
//        }
//    }

    /**
     * 清除指定缓存
     *
     * @param cacheName 缓存名
     */
    public void clearCache(String cacheName) {
        bizCache.clearCache(cacheName);
    }

}
