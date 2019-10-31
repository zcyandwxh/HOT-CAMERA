package com.point.common.biz;

import com.point.common.consts.Constant;
import com.point.common.thread.ThreadManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 线程池管理(线程统一调度管理)
 */
@Component
public final class PreprocessorThreadManager extends ThreadManager {

    /**
     * 系统参数管理工具类
     */
    @Autowired
    private SysParamManager sysParamManager;

    /**
     * 将构造方法访问修饰符设为私有，禁止任意实例化。
     */
    private PreprocessorThreadManager() {
    }

    /**
     * 初始化
     */
    public void prepare() {
        int sizeCorePool = sysParamManager.getSysConfigInt(Constant.SysConfig.PREPROCESS_THREAD_COUNT, 10);
        super.prepare(sizeCorePool);
    }
}