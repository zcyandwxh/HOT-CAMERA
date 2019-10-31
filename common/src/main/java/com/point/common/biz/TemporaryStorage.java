package com.point.common.biz;

import com.point.common.database.accessor.RedisAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 应用本地临时存储工具类
 */
@Component
public class TemporaryStorage {

    /**
     * Redis访问工具类实例
     */
    @Autowired
    private RedisAccessor redisAccessor;

    /**
     * 保存信息至远程缓存
     *
     * @param key   主键
     * @param value 值
     */
    public void set(String key, String value) {
        redisAccessor.set(key, value);
    }

    /**
     * 从远程缓存读取信息
     *
     * @param key 键
     * @return 值
     */
    public String get(String key) {
        return redisAccessor.get(key);
    }


    /**
     * 设删除
     *
     * @param key   主键
     */
    public void del(String key) {
        redisAccessor.del(key);
    }

    /**
     * 设定值
     *
     * @param key   主键
     * @param value 值
     */
    public void set(String key, String value, int timeout) {
        redisAccessor.setex(key, value, timeout);
    }


    /**
     * 根据主键查找所有数据
     *
     * @param key 主键
     * @return 数据List
     */
    public List<String> findAll(String key) {
        return redisAccessor.findAll(key);
    }

}
