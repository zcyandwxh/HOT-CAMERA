package com.point.common.biz;

import com.point.common.cache.BizCache;
import com.point.common.database.tools.FieldNameConverter;
import com.point.common.util.ReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 属性数据映射共通处理
 */
@Component
public class PropDataMapper {

    /**
     * 缓存管理器
     */
    @Autowired
    private BizCache bizCache;

    /**
     * 字段名转换器
     */
    @Autowired
    private FieldNameConverter fieldNameConverter;

    /**
     * 属性映射
     *
     * @param dataType 数据种类
     * @param src      映射源
     */
    public Map<String, Object> mapping(int dataType, Object src) {

        return mapping(dataType, ReflectionUtil.describeBean(src, null), null);
    }

    /**
     * 属性映射
     *
     * @param dataType 数据种类
     * @param src      映射源
     */
    public Map<String, Object> mapping(int dataType, Object src, Map<String, String> replaceKeys) {

        return mapping(dataType, ReflectionUtil.describeBean(src, null), replaceKeys);
    }

    /**
     * 属性映射
     *
     * @param dataType 数据种类
     * @param src      映射源
     */
    public Map<String, Object> mapping(int dataType, Map<String, Object> src, Map<String, String> replaceKeys) {

        Map<Integer, Map<String, String>> attrMappingRule = bizCache.getCurrentAttrMappingRule();

        // 属性转换规则
        Map<String, String> rule = attrMappingRule.get(dataType);
        if (rule == null) {
            rule = attrMappingRule.get(dataType >= 10 ? dataType / 10 : dataType);
        }
        if (rule != null) {

            if (replaceKeys != null) {
                for (Map.Entry<String, String> replace : replaceKeys.entrySet()) {
                    String oldKey = replace.getKey();
                    if (src.containsKey(oldKey)) {
                        src.put(replace.getValue(), src.get(oldKey));
                        src.remove(oldKey);
                    }
                }
            }
            // 根据属性转换规则将属性复制
            return ReflectionUtil.mappingProperties(src, rule, fieldNameConverter);
        }
        return null;
    }
}
