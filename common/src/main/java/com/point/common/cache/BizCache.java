package com.point.common.cache;

import com.point.common.application.AppContextSupport;
import com.point.common.biz.DeviceAbility;
import com.point.common.consts.Constant;
import com.point.common.data.*;
import com.point.common.database.accessor.RDBAccessor;
import com.point.common.database.domain.*;
import com.point.common.database.tools.FieldNameConverter;
import com.point.common.storage.FileStorage;
import com.point.common.util.DateUtil;
import com.point.common.util.ReflectionUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 缓存管理
 */
@Component
@Slf4j
public class BizCache {

    /**
     * DB访问工具类实例
     */
    @Autowired
    private RDBAccessor dbAccessor;

    /**
     * 缓存管理工具
     */
    @Autowired
    private CacheManager cacheManager;

    /**
     * 字段名转换器
     */
    @Autowired
    private FieldNameConverter nameConverter;

    /**
     * 缓存命名空间管理器
     */
    @Autowired
    private CacheNamespaceManager cacheNamespaceManager;

    /**
     * 根据输入源ID获取输入源信息
     *
     * @return 输入源信息
     */
    @NamespaceCacheable(value = "getAllFrontDevInfo", namespace = Constant.DataTable.MST_FRONT)
    public List<MstFront> getAllFrontDevInfo() {

        // 检索输入源表
        String srcSql = "select * from MST_FRONT where IS_DELETED = 2";
        return dbAccessor.query(srcSql, MstFront.class);
    }

    /**
     * 根据输入源ID获取输入源信息
     *
     * @param frtDevId 输入源ID
     * @return 输入源信息
     */
    @NamespaceCacheable(value = "getFrontDevInfo", namespace = Constant.DataTable.MST_FRONT)
    public MstFront getFrontDevInfo(String frtDevId) {

        // 检索输入源表
        String srcSql = "select * from MST_FRONT where DEV_ID = ? and IS_DELETED = 2";
        return dbAccessor.queryForObject(srcSql, MstFront.class, frtDevId);
    }

    /**
     * 根据分析服务器ID获取分析服务器信息
     *
     * @return 分析服务器信息
     */
    @NamespaceCacheable(value = "getAllAnlSvrInfo", namespace = Constant.DataTable.MST_ANL_SVR)
    public List<MstAnlSvr> getAllAnlSvrInfo() {

        String devSql = "select * from MST_ANL_SVR where IS_DELETED = 2";
        return dbAccessor.query(devSql, MstAnlSvr.class);
    }

    /**
     * 根据分析服务器ID获取分析服务器信息
     *
     * @param svrId 分析服务器ID
     * @return 分析服务器信息
     */
    @NamespaceCacheable(value = "getAnlSvrInfo", namespace = Constant.DataTable.MST_ANL_SVR)
    public MstAnlSvr getAnlSvrInfo(String svrId) {

        String devSql = "select * from MST_ANL_SVR where DEV_ID = ? and IS_DELETED = 2";
        return dbAccessor.queryForObject(devSql, MstAnlSvr.class, svrId);
    }

    /**
     * 获取所有具有比对能力的分析服务器信息
     *
     * @return 分析服务器信息
     */
    @NamespaceCacheable(value = "getAnlSvrInfoByAbility", namespace = Constant.DataTable.MST_ANL_SVR)
    public List<MstAnlSvr> getAnlSvrInfoByAbility(int... abilities) {

        String devSql = "select * from MST_ANL_SVR where IS_ENABLED = 1 and IS_DELETED = 2";
        List<MstAnlSvr> mstAnlSvrList = dbAccessor.query(devSql, MstAnlSvr.class);
        if (CollectionUtils.isEmpty(mstAnlSvrList)) {
            return mstAnlSvrList;
        }

        List<MstAnlSvr> resultList = new ArrayList<>();
        for (MstAnlSvr mstAnlSvr : mstAnlSvrList) {
            DeviceAbility deviceAbility = new DeviceAbility(mstAnlSvr.getAbility());
            boolean hasAbility = true;
            for (int ability : abilities) {
                if (!deviceAbility.hasAbility(ability)) {
                    hasAbility = false;
                    break;
                }
            }
            if (!hasAbility) {
                continue;
            }
            resultList.add(mstAnlSvr);
        }
        return resultList;
    }

    /**
     * 根据平台ID获取平台服务器信息
     *
     * @param svrId 平台服务器ID
     * @return 平台服务器信息
     */
    @NamespaceCacheable(value = "getPlatformInfo", namespace = Constant.DataTable.MST_PLATFORM)
    public MstPlatform getPlatformInfo(String svrId) {

        String devSql = "select * from MST_PLATFORM where DEV_ID = ? and IS_DELETED = 2";
        return dbAccessor.queryForObject(devSql, MstPlatform.class, svrId);
    }

    /**
     * 根据流媒体服务器ID获取流媒体服务器信息
     *
     * @param svrId 流媒体服务器ID
     * @return 流媒体服务器信息
     */
    @NamespaceCacheable(value = "getRmsSvrInfo", namespace = Constant.DataTable.MST_RMS_SVR)
    public MstRmsSvr getRmsSvrInfo(String svrId) {

        String devSql;
        // 若未指定设备ID，则返回第一台可用设备
        if (StringUtils.isEmpty(svrId)) {
            devSql = "select * from MST_RMS_SVR where IS_ENABLED = 1 and IS_DELETED = 2 order by DEV_ID limit 1";
            return dbAccessor.queryForObject(devSql, MstRmsSvr.class);

        } else {
            devSql = "select * from MST_RMS_SVR where DEV_ID = ? ";
            return dbAccessor.queryForObject(devSql, MstRmsSvr.class, svrId);
        }

    }

    /**
     * 根据转码服务器ID获取转码服务器信息
     *
     * @param svrId 转码服务器ID
     * @return 转码服务器信息
     */
    @NamespaceCacheable(value = "getMcSvrInfo", namespace = Constant.DataTable.MST_MC_SVR)
    public MstMcSvr getMcSvrInfo(String svrId) {

        String devSql;
        // 若未指定设备ID，则返回第一台可用设备
        if (StringUtils.isEmpty(svrId)) {
            devSql = "select * from MST_MC_SVR where IS_ENABLED = 1 and IS_DELETED = 2 order by DEV_ID limit 1";
            return dbAccessor.queryForObject(devSql, MstMcSvr.class);
        } else {
            devSql = "select * from MST_MC_SVR where DEV_ID = ? ";
            return dbAccessor.queryForObject(devSql, MstMcSvr.class, svrId);
        }
    }

    /**
     * 根据识别方案ID获取识别方案配置
     *
     * @param recogPlanId 识别方案ID
     * @return 识别方案配置
     */
    @NamespaceCacheable(value = "getRecogPlanInfos", namespace = Constant.DataTable.MST_RECOG_PLAN)
    public List<MstAnlSvr> getRecogPlanInfos(String recogPlanId) {

        String srcSql = "select C.* from MST_RECOG_PLAN A, MST_RECOG_CONF B, MST_ANL_SVR C " +
                "where A.PLAN_ID = ? and A.IS_ENABLED = 1 and A.IS_DELETED = 2 " +
                "and A.PLAN_ID = B.PLAN_ID and B.DEV_ID = C.DEV_ID";
        return dbAccessor.query(srcSql, MstAnlSvr.class, recogPlanId);
    }

    /**
     * 根据数据类型、动作类型获取筛选配置
     *
     * @param dataType     数据类型
     * @param filterAction 动作类型
     * @return 筛选配置
     */
    @NamespaceCacheable(value = "getResultFilterConf", namespace = Constant.DataTable.MST_RSLT_FILTER_PLAN)
    public List<MstRsltFilterConf> getResultFilterConf(int dataType, int filterAction) {

        String srcSql = "select B.* from MST_RSLT_FILTER_PLAN A, MST_RSLT_FILTER_CONF B " +
                "where A.IS_ENABLED = 1 and A.IS_DELETED = 2 " +
                "and A.PLAN_ID = B.PLAN_ID and A.DATA_TYPE = ? and A.ACTION = ?";
        return dbAccessor.query(srcSql, MstRsltFilterConf.class, dataType, filterAction);
    }

    /**
     * 根据流程类型获取相关配置
     *
     * @param flowType 流程类型
     * @return 流程配置
     */
    @NamespaceCacheable(value = "getTaskFlowConf", namespace = Constant.DataTable.MST_TASK_FLOW_PLAN)
    public List<MstTaskFlowConf> getTaskFlowConf(int flowType) {

        String srcSql = "select B.* from MST_TASK_FLOW_PLAN A, MST_TASK_FLOW_CONF B " +
                "where A.IS_ENABLED = 1 and A.IS_DELETED = 2 and B.IS_ENABLED = 1 " +
                "and A.PLAN_ID = B.PLAN_ID and A.FLOW_TYPE = ? order by B.SEQ";
        return dbAccessor.query(srcSql, MstTaskFlowConf.class, flowType);
    }

    /**
     * 根据对比方案ID获取对比方案配置
     *
     * @param compPlanId 对比方案ID
     * @return 对比方案配置
     */
    @NamespaceCacheable(value = "getCompPlanInfos", namespace = Constant.DataTable.MST_COMP_PLAN)
    public List<MstAnlSvr> getCompPlanInfos(String compPlanId) {

        String srcSql = "select C.* from MST_COMP_PLAN A, MST_COMP_CONF B, MST_ANL_SVR C " +
                "where A.PLAN_ID = ? and A.IS_ENABLED = 1 and A.IS_DELETED = 2 " +
                "and A.PLAN_ID = B.PLAN_ID and B.DEV_ID = C.DEV_ID";
        return dbAccessor.query(srcSql, MstAnlSvr.class, compPlanId);
    }


    /**
     * 获取前端设备的连接信息
     *
     * @param frtDevId 输入源ID
     * @return 设备连接信息
     */
    @NamespaceCacheable(value = "getFrtDevConn", namespace = Constant.DataTable.MST_FRONT)
    public DeviceConnection getFrtDevConn(String frtDevId) {

        // 检索输入源表
        MstFront mstFront = getFrontDevInfo(frtDevId);
        DeviceConnection devConn = new DeviceConnection();
        devConn.setDevId(mstFront.getDevId());
        devConn.setDevName(mstFront.getDevName());
        devConn.setIpAddr(mstFront.getIpAddr());
        devConn.setIpV6Addr(mstFront.getIpV6Addr());
        devConn.setPort(mstFront.getPort());
        devConn.setPlatformId(mstFront.getPlatformId());
        devConn.setDevType(mstFront.getDevType());
        devConn.setDevNum(mstFront.getDevNum());
        devConn.setChannel(mstFront.getChannel());
        devConn.setStreamType(mstFront.getStreamType());
        devConn.setRtspUrl(mstFront.getRtspUrl());
        devConn.setFilePath(mstFront.getFilePath());
        devConn.setLoginUser(mstFront.getLoginUser());
        devConn.setLoginPass(mstFront.getLoginPass());
        return devConn;

    }

    /**
     * 获取分析服务器设备的连接信息
     *
     * @param devId 服务器ID
     * @return 设备连接信息
     */
    @NamespaceCacheable(value = "getAnlSvrConn", namespace = Constant.DataTable.MST_ANL_SVR)
    public DeviceConnection getAnlSvrConn(String devId) {

        // 获取分析服务器
        MstAnlSvr mstAnlSvr = getAnlSvrInfo(devId);
        DeviceConnection devConn = new DeviceConnection();
        devConn.setDevId(mstAnlSvr.getDevId());
        devConn.setIpAddr(mstAnlSvr.getIpAddr());
        devConn.setIpV6Addr(mstAnlSvr.getIpV6Addr());
        devConn.setPort(mstAnlSvr.getPort());
        // 厂商前缀设定
        devConn.setPrefix(mstAnlSvr.getDevModel());
        devConn.setLoginUser(mstAnlSvr.getLoginUser());
        devConn.setLoginPass(mstAnlSvr.getLoginPass());

        return devConn;
    }

    /**
     * 获取平台服务器设备的连接信息
     *
     * @param devId 平台服务器ID
     * @return 设备连接信息
     */
    @NamespaceCacheable(value = "getPlatformConn", namespace = Constant.DataTable.MST_PLATFORM)
    public DeviceConnection getPlatformConn(String devId) {

        // 获取平台服务器
        MstPlatform platformInfo = getPlatformInfo(devId);
        DeviceConnection devConn = new DeviceConnection();
        // 获取平台服务器获取不到
        if (platformInfo == null) {
            return devConn;
        } else {
            devConn.setDevId(platformInfo.getDevId());
            devConn.setIpAddr(platformInfo.getIpAddr());
            devConn.setIpV6Addr(platformInfo.getIpV6Addr());
            devConn.setPort(platformInfo.getPort());
            devConn.setLoginUser(platformInfo.getLoginUser());
            devConn.setLoginPass(platformInfo.getLoginPass());
            devConn.setRtspUrl(platformInfo.getRtsp());
        }
        return devConn;
    }

    /**
     * 获取流媒体服务器设备的连接信息
     *
     * @param devId 服务器ID
     * @return 设备连接信息
     */
    @NamespaceCacheable(value = "getRmsSvrConn", namespace = Constant.DataTable.MST_RMS_SVR)
    public DeviceConnection getRmsSvrConn(String devId) {

        // 获取流媒体服务器
        MstRmsSvr rmsSvrInfo = getRmsSvrInfo(devId);
        DeviceConnection devConn = new DeviceConnection();
        devConn.setDevId(rmsSvrInfo.getDevId());
        devConn.setIpAddr(rmsSvrInfo.getIpAddr());
        devConn.setIpV6Addr(rmsSvrInfo.getIpV6Addr());
        devConn.setPort(rmsSvrInfo.getPort());
        devConn.setLoginUser(rmsSvrInfo.getLoginUser());
        devConn.setLoginPass(rmsSvrInfo.getLoginPass());

        return devConn;
    }

    /**
     * 获取转码服务器设备的连接信息
     *
     * @param devId 服务器ID
     * @return 设备连接信息
     */
    @NamespaceCacheable(value = "getMcSvrConn", namespace = Constant.DataTable.MST_MC_SVR)
    public DeviceConnection getMcSvrConn(String devId) {

        // 获取转码服务器
        MstMcSvr mstMcSvr = getMcSvrInfo(devId);
        DeviceConnection devConn = new DeviceConnection();
        devConn.setDevId(mstMcSvr.getDevId());
        devConn.setIpAddr(mstMcSvr.getIpAddr());
        devConn.setIpV6Addr(mstMcSvr.getIpV6Addr());
        devConn.setPort(mstMcSvr.getPort());
        devConn.setLoginUser(mstMcSvr.getLoginUser());
        devConn.setLoginPass(mstMcSvr.getLoginPass());

        return devConn;
    }

    /**
     * 获取最新的连接程序
     *
     * @return 最新的连接程序
     */
    @NamespaceCacheable(value = "getLatestProgByDev", namespace = Constant.DataTable.MST_PROG)
    public DeviceProgramInfo getLatestProgByDev(int devType, String devId) {

        String devProgSql = "select * from MST_DEV_PROG where TYPE = ? and DEV_ID = ? ";
        MstDevProg devProg = dbAccessor.queryForObject(devProgSql, MstDevProg.class, devType, devId);
        if (devProg == null) {
            return null;
        }
        MstProg prog = getLastProg(devProg.getProgId(), devProg.getProgVer());
        DeviceProgramInfo devProgInfo = new DeviceProgramInfo();
        devProgInfo.setDevId(devId);
        devProgInfo.setProgId(devProg.getProgId());
        if (prog != null) {
            devProgInfo.setProgVer(prog.getProgVer());
            devProgInfo.setProgram(prog.getProgPath());
            devProgInfo.setIs32bit(prog.getIsBit32());
        }
        devProgInfo.setProgramParam(devProg.getProgParam());

        return devProgInfo;
    }

    /**
     * 获取最新版本程序
     *
     * @param progId  程序ID
     * @param version 程序版本
     * @return 程序信息
     */
    @NamespaceCacheable(value = "getLastProg", namespace = Constant.DataTable.MST_PROG)
    public MstProg getLastProg(String progId, String version) {

        MstProg prog;
        // 若未指定程序版本号，则使用最新版本的程序
        if (StringUtils.isEmpty(version)) {
            String progSql = "select MST_PROG.PROG_PATH PROG_PATH, MST_PROG.IS_BIT32 IS_BIT32 from MST_PROG, " +
                    "(select PROG_ID, max(PROG_VER) PROG_VER from MST_PROG group by PROG_ID) LATEST_VER " +
                    "where MST_PROG.PROG_ID = LATEST_VER.PROG_ID and MST_PROG.PROG_VER = LATEST_VER.PROG_VER " +
                    "and MST_PROG.PROG_ID = ?";
            prog = dbAccessor.queryForObject(progSql, MstProg.class, progId);

            // 若指定程序版本号，则使用该版本的程序
        } else {
            String progSql = "select MST_PROG.PROG_PATH PROG_PATH, MST_PROG.IS_BIT32 IS_BIT32 from MST_PROG " +
                    "where MST_PROG.PROG_ID = ? and MST_PROG.PROG_VER = ? ";
            prog = dbAccessor.queryForObject(progSql, MstProg.class, progId, version);
        }
        return prog;
    }

    /**
     * 获取结构化相关配置
     *
     * @param planId   方案ID
     * @param dataType 数据类型
     * @return 结构化相关配置
     */
    @NamespaceCacheable(value = "getRecogConfig", namespace = Constant.DataTable.MST_RECOG_PLAN)
    public List<MstRecogConf> getRecogConfig(String planId, int dataType) {

        if (planId == null) {
            // 检索属性识别程序相关设定数据
            return dbAccessor.query("select B.DEV_ID " +
                            "from MST_RECOG_PLAN A, MST_RECOG_CONF B where A.PLAN_ID = B.PLAN_ID " +
                            "and A.IS_ENABLED = 1 and A.IS_DELETED = 2 and B.DATA_TYPE = ? ",
                    MstRecogConf.class, dataType);
        } else {

            // 检索属性识别程序相关设定数据
            return dbAccessor.query("select B.DEV_ID " +
                            "from MST_RECOG_PLAN A, MST_RECOG_CONF B where A.PLAN_ID = B.PLAN_ID " +
                            "and A.IS_ENABLED = 1 and A.IS_DELETED = 2 and A.PLAN_ID = ? and B.DATA_TYPE = ? ",
                    MstRecogConf.class, planId, dataType);
        }
    }

    /**
     * 获取结构化相关配置
     *
     * @param planId   方案ID
     * @param dataType 数据类型
     * @return 结构化相关配置
     */
    @NamespaceCacheable(value = "getCompConfig", namespace = Constant.DataTable.MST_COMP_PLAN)
    public List<MstCompConf> getCompConfig(String planId, int dataType) {

        if (planId == null) {
            // 检索比对程序相关设定数据
            return dbAccessor.query("select B.DEV_ID, B.TARGET_DB " +
                            "from MST_COMP_PLAN A, MST_COMP_CONF B where A.PLAN_ID = B.PLAN_ID " +
                            "and A.IS_ENABLED = 1 and A.IS_DELETED = 2 and B.DATA_TYPE = ? ",
                    MstCompConf.class, dataType);
        } else {

            // 检索属性识别程序相关设定数据
            return dbAccessor.query("select B.DEV_ID, B.TARGET_DB " +
                            "from MST_COMP_PLAN A, MST_COMP_CONF B where A.PLAN_ID = B.PLAN_ID " +
                            "and A.IS_ENABLED = 1 and A.IS_DELETED = 2 and A.PLAN_ID = ? and B.DATA_TYPE = ? ",
                    MstCompConf.class, planId, dataType);
        }

    }

    /**
     * 获取比对目标信息
     *
     * @param targetDB 对比底库ID
     * @param devId    对比设备ID
     * @param objId    对比对象ID
     * @return 目标信息
     */
    @NamespaceCacheable(value = "getFaceCompareTarget", namespace = Constant.DataTable.MST_FACE_TARGET)
    public MstFaceTarget getFaceCompareTarget(String targetDB, String devId, String objId) {

        String sql = "select A.TARGET_DB, A.TARGET_ID, A.BRANCH_NUM, A.IMG_ID, A.ALIAS, A.DESCRIPTION " +
                "from MST_FACE_TARGET A,  MST_FACE_TARGET_OBJ B " +
                "where A.TARGET_DB = B.TARGET_DB and A.TARGET_ID = B.TARGET_ID and A.BRANCH_NUM = B.BRANCH_NUM " +
                "and B.TARGET_DB = ? and B.DEV_ID = ? and B.OBJ_ID = ?";
        List<MstFaceTarget> targets = dbAccessor.query(sql, MstFaceTarget.class, targetDB, devId, objId);
        if (!CollectionUtils.isEmpty(targets)) {
            return targets.get(0);
        }
        return null;
    }

    /**
     * 获取比对目标信息
     *
     * @param targetDB 对比底库ID
     * @param devId    对比设备ID
     * @param objId    对比对象ID
     * @return 目标信息
     */
    @NamespaceCacheable(value = "getBodyCompareTarget", namespace = Constant.DataTable.MST_BODY_TARGET)
    public MstBodyTarget getBodyCompareTarget(String targetDB, String devId, String objId) {

        String sql = "select A.TARGET_DB, A.TARGET_ID, A.BRANCH_NUM, A.IMG_ID, A.ALIAS, A.DESCRIPTION " +
                "from MST_BODY_TARGET A,  MST_BODY_TARGET_OBJ B, MST_BODY_TARGET_DB C " +
                "where A.TARGET_DB = B.TARGET_DB and A.TARGET_ID = B.TARGET_ID and A.BRANCH_NUM = B.BRANCH_NUM " +
                "and C.TARGET_DB = ? and B.DEV_ID = ? and B.OBJ_ID = ?";
        List<MstBodyTarget> targets = dbAccessor.query(sql, MstBodyTarget.class, targetDB, devId, objId);
        if (!CollectionUtils.isEmpty(targets)) {
            return targets.get(0);
        }
        return null;
    }

    /**
     * 获取比对目标信息(抛开底库条件仅为objid)
     *
     * @param objId    对比对象ID
     * @return 目标信息
     */
    @NamespaceCacheable(value = "getFaceCompareTargetWithoutTargetDb", namespace = Constant.DataTable.MST_FACE_TARGET)
    public MstFaceTarget getFaceCompareTargetWithoutTargetDb(String objId) {

        String sql = "select B.TARGET_DB, B.TARGET_ID " +
                "from MST_FACE_TARGET_OBJ B " +
                "where B.OBJ_ID = ?";
        List<MstFaceTargetObj> targets = dbAccessor.query(sql, MstFaceTargetObj.class, objId);
        if (targets.isEmpty()) {
            return null;
        }

        String targetDbSql = "select A.TARGET_DB, A.TARGET_ID, A.BRANCH_NUM, A.IMG_ID, A.ALIAS, A.DESCRIPTION " +
                "from MST_FACE_TARGET A " +
                "where A.TARGET_DB = ? and A.TARGET_ID = ?";

        List<MstFaceTarget> faceTargets = dbAccessor.query(targetDbSql, MstFaceTarget.class, targets.get(0).getTargetDb(), targets.get(0).getTargetId());
        if (!CollectionUtils.isEmpty(faceTargets)) {
            return faceTargets.get(0);
        }
        return null;
    }

    /**
     * 获取比对目标信息
     *
     * @param targetDB 对比底库ID
     * @param devId    对比设备ID
     * @param objId    对比对象ID
     * @return 目标信息
     */
    @NamespaceCacheable(value = "getFaceCompareTargetStandard", namespace = Constant.DataTable.MST_FACE_TARGET)
    public MstFaceTarget getFaceCompareTargetStandard(String targetDB, String devId, String objId) {

        MstFaceTarget target = getFaceCompareTarget(targetDB, devId, objId);
        if (target != null) {
            String sql = "select A.TARGET_DB, A.TARGET_ID, A.BRANCH_NUM, A.IMG_ID, A.ALIAS, A.DESCRIPTION " +
                    "from MST_FACE_TARGET A,  MST_FACE_TARGET_OBJ B " +
                    "where A.TARGET_DB = B.TARGET_DB and A.TARGET_ID = B.TARGET_ID and A.BRANCH_NUM = B.BRANCH_NUM " +
                    "and B.TARGET_DB = ? and B.TARGET_ID = ? and B.BRANCH_NUM = ?";
            List<MstFaceTarget> targets = dbAccessor.query(sql, MstFaceTarget.class, target.getTargetDb(), target.getTargetId(), 1);
            if (!CollectionUtils.isEmpty(targets)) {
                return targets.get(0);
            }
        }
        return null;
    }

    /**
     * 获取所有人脸比对底库名
     *
     * @return 对比底库名
     */
    @NamespaceCacheable(value = "getAllFaceCompareTargetDB", namespace = Constant.DataTable.MST_FACE_TARGET)
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
    @NamespaceCacheable(value = "getAllFaceCompareTarget", namespace = Constant.DataTable.MST_FACE_TARGET)
    public List<MstFaceTargetObj> getAllFaceCompareTarget(String targetDb, String devId) {

        String sql = "select * from MST_FACE_TARGET_OBJ where TARGET_DB = ? and DEV_ID = ?";
        return dbAccessor.query(sql, MstFaceTargetObj.class, targetDb, devId);
    }

    /**
     * 获取指定服务器中所有人脸比对底库Id
     *
     * @param devId 对比设备ID
     * @return 底库Id
     */
    @NamespaceCacheable(value = "getAllFaceCompareTargetDbId", namespace = Constant.DataTable.MST_FACE_TARGET)
    public List<String> getAllFaceCompareTargetDbId(String devId) {

        String sql = "select DB_ID from MST_FACE_TARGET_DB_OBJ where DEV_ID = ?";
        return dbAccessor.querySingle(sql, String.class, devId);
    }

    /**
     * 获取指定服务器中人脸比对底库Id
     *
     * @param targetDb 比对底库
     * @param devId    对比设备ID
     * @return 底库Id
     */
    @NamespaceCacheable(value = "getFaceCompareTargetDbId", namespace = Constant.DataTable.MST_FACE_TARGET)
    public String getFaceCompareTargetDbId(String targetDb, String devId) {

        String sql = "select DB_ID from MST_FACE_TARGET_DB_OBJ where DEV_ID = ? and TARGET_DB = ? ";
        return dbAccessor.queryForObject(sql, String.class, devId, targetDb);
    }

    /**
     * 获取指定服务器中人体比对底库Id
     *
     * @param targetDb 比对底库
     * @param devId    对比设备ID
     * @return 底库Id
     */
    @NamespaceCacheable(value = "getBodyCompareTargetDbId", namespace = Constant.DataTable.MST_BODY_TARGET)
    public String getBodyCompareTargetDbId(String targetDb, String devId) {

        String sql = "select DB_ID from MST_BODY_TARGET_DB_OBJ where DEV_ID = ? and TARGET_DB = ? ";
        return dbAccessor.queryForObject(sql, String.class, devId, targetDb);
    }


    /**
     * 根据配置信息获取存储工具类
     *
     * @param config 配置信息
     * @return 工具类
     */
    @NamespaceCacheable(value = "getFileStorage", namespace = Constant.DataTable.MST_STORAGE_PLAN)
    public FileStorage getFileStorage(StorageConfig config) {
        String protocol = config.getProtocol();
        String packageName = FileStorage.class.getPackage().getName();
        String clazz = String.format("%s.%s.%s", packageName, protocol, StringUtils.capitalize(protocol).concat("Storage"));
        FileStorage storage = AppContextSupport.getBean(clazz, true, (Object[]) null);
        storage.config(config);
        return storage;
    }

    /**
     * 根据通道ID获取配置信息
     *
     * @param chanelId 通道ID
     * @return 配置信息
     */
    @NamespaceCacheable(value = "getStorageConf", namespace = Constant.DataTable.MST_STORAGE_PLAN)
    public StorageConfig getStorageConf(String chanelId) {

        String sql = "select PROTOCOL, USER, PASSWORD, IP_ADDR, PORT, BASE_PATH from " +
                "MST_STORAGE_CHANEL where CHANEL_ID = ? ";
        MstStorageChanel chanel = dbAccessor.queryForObject(sql, MstStorageChanel.class, chanelId);
        StorageConfig conf = new StorageConfig();
        ReflectionUtil.copyFields(chanel, conf);
        return conf;
    }

    /**
     * 获取当前配置下的所有通道
     *
     * @return 通道信息
     */
    @NamespaceCacheable(value = "getCurrentStorageConf", namespace = Constant.DataTable.MST_STORAGE_PLAN)
    public List<StorageConfig> getCurrentStorageConf() {

        Date currentDate = DateUtil.getCurrentDate();
        String sql = "select C.CHANEL_ID, C.PROTOCOL, C.USER, C.PASSWORD, C.IP_ADDR, C.PORT, C.BASE_PATH, B.FILE_TYPE " +
                "from MST_STORAGE_PLAN A, MST_STORAGE_CONF B, MST_STORAGE_CHANEL C " +
                "where A.PLAN_ID = B.PLAN_ID and B.CHANEL_ID = C.CHANEL_ID " +
                "and A.START_TIME <= ? and (A.END_TIME is null or A.END_TIME >= ?) ";
        List<StorageConf> chanelList = dbAccessor.query(sql, StorageConf.class, currentDate, currentDate);
        List<StorageConfig> confList = new ArrayList<>();
        for (MstStorageChanel chanel : chanelList) {
            StorageConfig conf = new StorageConfig();
            ReflectionUtil.copyFields(chanel, conf);
            confList.add(conf);
        }
        return confList;
    }

    /**
     * 获取属性转换映射关系
     *
     * @return 通道信息
     */
    @NamespaceCacheable(value = "getCurrentAttrMappingRule", namespace = Constant.DataTable.MST_ATTR_TRANS_PLAN)
    public Map<Integer, Map<String, String>> getCurrentAttrMappingRule() {

        // 检索属性转换规则配置
        Map<Integer, Map<String, String>> attrMappingRule = new HashMap<>();
        Date currentDate = DateUtil.getCurrentDate();
        List<AttrMappingInfo> attrTransConfs = dbAccessor.query("select A.DATA_TYPE, B.FIELD_NAME, B.ATTR_MAP_KEY " +
                        "from MST_ATTR_TRANS_PLAN A, MST_ATTR_TRANS_CONF B " +
                        "where A.PLAN_ID = B.PLAN_ID and A.START_TIME <= ? and (A.END_TIME is null or A.END_TIME >= ?) ",
                AttrMappingInfo.class, currentDate, currentDate);

        if (attrTransConfs != null) {
            // 将设定数据保存至内存
            for (AttrMappingInfo info : attrTransConfs) {

                Map<String, String> mapping;
                if (attrMappingRule.containsKey(info.getDataType())) {
                    mapping = attrMappingRule.get(info.getDataType());
                } else {
                    mapping = new HashMap<>();
                    attrMappingRule.put(info.getDataType(), mapping);
                }
                mapping.put(info.getAttrMapKey(), info.getFieldName());
            }
        }
        return attrMappingRule;
    }

    /**
     * 获取数据类型
     *
     * @return 数据类型列表
     */
    @NamespaceCacheable(value = "getCurrentDatAttrMaping", namespace = Constant.DataTable.MST_META_DAT_ATTR)
    public Map<String, Map<String, String>> getCurrentDatAttrMaping() {

        // 数据类型配置
        Map<String, Map<String, String>> datAttrMapping = new HashMap<>();
        List<MetaDataAttr> metaDataAttrs = dbAccessor.query("select ATTRIBUTE_TYPE, COLUMN_NAME, CHN_SHOW_NAME, DICTIONARY_CODE from MST_META_DAT_ATTR ",
                MetaDataAttr.class);

        if (metaDataAttrs != null) {
            // 将设定数据保存至内存
            for (MetaDataAttr info : metaDataAttrs) {

                Map<String, String> mapping;
                if (datAttrMapping.containsKey(info.getAttributeType())) {
                    mapping = datAttrMapping.get(info.getAttributeType());
                } else {
                    mapping = new HashMap<>();
                    datAttrMapping.put(info.getAttributeType(), mapping);
                }
                mapping.put(info.getColumnName(), info.getChnShowName() + (StringUtils.isEmpty(info.getDictionaryCode()) ? "" : "," + info.getDictionaryCode()));
            }
        }
        return datAttrMapping;
    }

    /**
     * 获取数据字典
     *
     * @return 数据字典列表
     */
    @NamespaceCacheable(value = "getCurrentDicCodeMaping", namespace = Constant.DataTable.MST_META_DAT_ATTR)
    public Map<String, Map<String, String>> getCurrentDicCodeMaping() {

        // 数据字典
        Map<String, Map<String, String>> dicCodeMaping = new HashMap<>();
        List<MstMetaDicCode> metaDicCode = dbAccessor.query("select DIC_GROUP, KEY_CODE, VALUE_CONTENT from MST_META_DIC_CODE ",
                MstMetaDicCode.class);

        if (metaDicCode != null) {
            // 将设定数据保存至内存
            for (MstMetaDicCode info : metaDicCode) {

                Map<String, String> mapping;
                if (dicCodeMaping.containsKey(info.getDicGroup())) {
                    mapping = dicCodeMaping.get(info.getDicGroup());
                } else {
                    mapping = new HashMap<>();
                    dicCodeMaping.put(info.getDicGroup(), mapping);
                }
                mapping.put(info.getKeyCode(), info.getValueContent());
            }
        }
        return dicCodeMaping;
    }

    /**
     * 任务相关实例获取
     *
     * @return 任务相关实例
     */
    @NamespaceCacheable(value = "getJobInstance", namespace = Constant.DataTable.MST_JOB_CONF)
    public <T> T getJobInstance(String key, String clazz) {
        return AppContextSupport.getBean(key, clazz, true, BeanDefinition.SCOPE_PROTOTYPE);
    }

    /**
     * 清除指定缓存
     *
     * @param cacheNamespace 缓存名
     */
    public void clearCache(String cacheNamespace) {

        Set<String> cacheNames = cacheNamespaceManager.getCaches(cacheNamespace);
        if (cacheNames != null) {
            for (String cacheName : cacheNames) {
                Cache cache = cacheManager.getCache(cacheName);
                if (cache != null) {
                    log.debug("cache clear {}", cacheName);
                    cache.clear();
                }
            }
        } else {
            Cache cache = cacheManager.getCache(cacheNamespace);
            if (cache != null) {
                log.debug("cache clear {}", cacheNamespace);
                cache.clear();
            }
        }
    }

    /**
     * 属性转换配置数据
     */
    private static class AttrMappingInfo extends MstAttrTransConf {

        @Setter
        @Getter
        private Integer dataType;
    }

}
