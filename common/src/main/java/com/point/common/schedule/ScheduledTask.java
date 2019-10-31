package com.point.common.schedule;

import lombok.extern.slf4j.Slf4j;

/**
 * 定时任务
 */
@Slf4j
public class ScheduledTask {

    /**
     * 是否启动
     */
    private boolean isStart;

    /**
     * 开始定时处理
     */
    public void start() {
        this.isStart = true;
    }

    /**
     * 检查是否被启动
     */
    public boolean isStarted() {
        return this.isStart;
    }
}
