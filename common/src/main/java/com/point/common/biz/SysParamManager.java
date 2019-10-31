package com.point.common.biz;

import com.point.common.cache.NamespaceCacheable;
import com.point.common.consts.Constant;
import com.point.common.database.accessor.RDBAccessor;
import com.point.common.database.domain.MstSysParamConf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统参数
 */
@Slf4j
@Component
public class SysParamManager {

    /**
     * DB访问工具类实例
     */
    @Autowired
    private RDBAccessor dbAccessor;

    /**
     * 获取系统参数设定
     *
     * @return 系统参数设定
     */
    private Map<String, String> getSysConfig() {

        Map<String, String> confMap = new HashMap<>();
        try {
            String sql = "select CONF_KEY, CONF_VALUE from MST_SYS_PARAM_CONF";
            List<MstSysParamConf> confList = dbAccessor.query(sql, MstSysParamConf.class);
            for (MstSysParamConf conf : confList) {
                confMap.put(conf.getConfKey(), conf.getConfValue());
            }
        } catch (Throwable e) {
            log.warn("skip getSysConfig");
        }
        return confMap;
    }

    /**
     * 获取系统参数设定
     *
     * @param key 设定值key
     * @return 设定值
     */
    @NamespaceCacheable(value = "getSysConfig", namespace = Constant.DataTable.MST_SYS_PARAM_CONF)
    public String getSysConfig(String key) {

        Map<String, String> conf = getSysConfig();
        return conf.get(key);
    }

    /**
     * 获取系统参数设定
     *
     * @param key          设定值key
     * @param defaultValue 默认值
     * @return 设定值
     */
    @NamespaceCacheable(value = "getSysConfigWithDefault", namespace = Constant.DataTable.MST_SYS_PARAM_CONF)
    public String getSysConfig(String key, String defaultValue) {

        String value = getSysConfig(key);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    /**
     * 获取系统参数设定
     *
     * @param key          设定值key
     * @param defaultValue 默认值
     * @return 设定值
     */
    @NamespaceCacheable(value = "getSysConfigInt", namespace = Constant.DataTable.MST_SYS_PARAM_CONF)
    public int getSysConfigInt(String key, int defaultValue) {

        String value = getSysConfig(key);
        if (value == null) {
            return defaultValue;
        }
        return Integer.parseInt(value);
    }

    /**
     * 获取系统参数设定
     *
     * @param key          设定值key
     * @param defaultValue 默认值
     * @return 设定值
     */
    @NamespaceCacheable(value = "getSysConfigBoolean", namespace = Constant.DataTable.MST_SYS_PARAM_CONF)
    public boolean getSysConfigBoolean(String key, boolean defaultValue) {

        String value = getSysConfig(key);
        if (value == null) {
            return defaultValue;
        }
        return Boolean.parseBoolean(value);
    }

    /**
     * 获取系统参数设定
     *
     * @param key          设定值key
     * @param defaultValue 默认值
     * @return 设定值
     */
    @NamespaceCacheable(value = "getSysConfigLong", namespace = Constant.DataTable.MST_SYS_PARAM_CONF)
    public long getSysConfigLong(String key, long defaultValue) {

        String value = getSysConfig(key);
        if (value == null) {
            return defaultValue;
        }
        return Long.parseLong(value);
    }

    /**
     * 获取系统参数设定
     *
     * @param key          设定值key
     * @param defaultValue 默认值
     * @return 设定值
     */
    @NamespaceCacheable(value = "getSysConfigDouble", namespace = Constant.DataTable.MST_SYS_PARAM_CONF)
    public double getSysConfigDouble(String key, double defaultValue) {

        String value = getSysConfig(key);
        if (value == null) {
            return defaultValue;
        }
        return Double.parseDouble(value);
    }

}
