package com.point.common.biz;

import com.point.common.config.LocalConfig;
import com.point.common.consts.Constant;
import com.point.common.database.accessor.RDBAccessor;
import com.point.common.database.domain.MstSysParamConf;
import com.point.common.database.tools.FieldNameConverter;
import com.point.common.http.RestClient;
import com.point.common.util.DateUtil;
import com.point.common.util.IpUtil;
import com.point.common.util.ReflectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * DB 管理器
 */
@Component
@Slf4j
public class DatabaseManager {

    /**
     * DB访问工具类实例
     */
    @Autowired
    private RDBAccessor dbAccessor;

    /**
     * 本主机配置文件
     */
    @Autowired
    private LocalConfig localConfig;

    /**
     * HTTP 工具类
     */
    @Autowired
    private RestClient restClient;

    /**
     * 字段名转换器
     */
    @Autowired
    private FieldNameConverter nameConverter;

    /**
     * DB相关的初始化处理
     */
    public void init() {

        try {
            registerCacheClearReqUrl();
        } catch (Throwable e) {
            log.warn("skip E {}", "DatabaseManager.init");
        }
    }

    /**
     * 注册缓存更新请求地址
     */
    private void registerCacheClearReqUrl() {

        String ip = localConfig.getIp();
        // 若IP地址未被设定
        if ("none".equals(ip)) {
            ip = IpUtil.getLocalIp();
        }
        String key = Constant.SysConfig.CACHE_NAME.concat(ip).concat(":").concat(localConfig.getPort());
        String cacheClearRequest = String.format("http://%s:%s/cache/%s/", ip, localConfig.getPort(), Constant.CacheAction.CLEAR);
        MstSysParamConf conf = new MstSysParamConf();
        conf.setConfKey(key);
        conf.setConfValue(cacheClearRequest);
        conf.setConfName("CacheUpdateUrl");
        Date now = DateUtil.getCurrentDate();
        conf.setCreateTime(now);
        conf.setCreateUser("system");
        conf.setUpdateTime(now);
        conf.setUpdateUser("system");

        String sql = "select CONF_KEY, CONF_VALUE from MST_SYS_PARAM_CONF where CONF_KEY = ?";
        List<MstSysParamConf> confList = dbAccessor.query(sql, MstSysParamConf.class, key);
        if (!CollectionUtils.isEmpty(confList)) {
            dbAccessor.update("MST_SYS_PARAM_CONF", ReflectionUtil.describeBean(conf, nameConverter), "CONF_KEY = ?", key);
        } else {
            dbAccessor.insert("MST_SYS_PARAM_CONF", conf);
        }
    }

    /**
     * 更新缓存
     */
    public void clearCache(String cacheName) {

        String sql = "select CONF_KEY, CONF_VALUE from MST_SYS_PARAM_CONF";
        List<MstSysParamConf> confList = dbAccessor.query(sql, MstSysParamConf.class);

        for (MstSysParamConf conf : confList) {
            String key = conf.getConfKey();
            if (!StringUtils.isEmpty(key) && key.startsWith(Constant.SysConfig.CACHE_NAME)) {
                String url = conf.getConfValue();

                try {
                    new Thread(()-> restClient.post(url, cacheName, String.class)).start();
                } catch (Throwable e) {
                    log.error("缓存清除失败", e);
                }
            }
        }
    }

//    /**
//     * 定时检查并更新缓存
//     */
//    @Scheduled(fixedRate = 20000, initialDelay = 20000)
//    public void checkAndClear() {
//
//        String sql = "select CONF_KEY, CONF_VALUE from MST_SYS_PARAM_CONF";
//        List<MstSysParamConf> confList = dbAccessor.query(sql, MstSysParamConf.class);
//
//        for (MstSysParamConf conf : confList) {
//            String key = conf.getConfKey();
//            if (!StringUtils.isEmpty(key) && key.startsWith(Constant.SysConfig.CACHE_NAME)) {
//                // 最新更新时间
//                String latestTime = conf.getConfValue();
//                // 上次更新时间
//                String lastTime = updateTimeStore.get(key);
//                // 若最新更新时间 > 上次更新时间
//                if (lastTime == null || latestTime.compareTo(lastTime) > 0) {
//                    // 截取缓存名
//                    String cacheName = StringUtils.substringAfterLast(key, Constant.SysConfig.CACHE_NAME);
//                    // 清除缓存
//                    bizCache.clearCache(cacheName);
//                }
//            }
//        }
//    }

}
