package com.point.common.util;

import lombok.extern.slf4j.Slf4j;

/**
 * 重试处理帮助类
 */
@Slf4j
public class RetryHelper {

    int maxCount;
    int currentCount;
    int interval;
//    Retryable mainThreadRun;
//    Runnable slaveThreadRun;


    public RetryHelper(int maxCount, int interval) {
        this.maxCount = maxCount;
        this.interval = interval;
    }

    public void execute(Retryable mainThread, Runnable slaveThreadRun, Runnable timeoutRun) {

        if (slaveThreadRun != null) {
            new Thread(slaveThreadRun).start();
        }

        while (true) {
            currentCount++;
            if (mainThread.run()) {
                break;
            }
            if (currentCount > maxCount) {
                if (timeoutRun != null) {
                    timeoutRun.run();
                }
                break;
            }
            org.apache.hadoop.util.ThreadUtil.sleepAtLeastIgnoreInterrupts(interval);
        }
    }

    private boolean mainThreadOver = true;
    public boolean executeWithHeartbeat(Retryable mainThread, Retryable heartbeatRun, int heartbeatInterval, Runnable timoutRun) {

        mainThreadOver = false;
        if (heartbeatRun != null) {
            new Thread(()->{
                while (!mainThreadOver) {
                    if (heartbeatRun.run()) {
                        break;
                    }
                    org.apache.hadoop.util.ThreadUtil.sleepAtLeastIgnoreInterrupts(heartbeatInterval);
                }
            }).start();
        }

        try {
            while (true) {
                currentCount++;
                if (mainThread.run()) {
                    break;
                }
                if (currentCount > maxCount) {
                    log.debug("处理失败超过最大重试次数 {} {}", currentCount, mainThread.getClass());
                    if (timoutRun != null) {
                        timoutRun.run();
                    }
                    return false;
                }
                log.debug("处理失败 重试次数: {} {}", currentCount, mainThread.getClass());
                org.apache.hadoop.util.ThreadUtil.sleepAtLeastIgnoreInterrupts(interval);
            }
            return true;
        } catch(Throwable e) {
            log.error("executeWithHeartbeat",e );
            return false;
        } finally {
            mainThreadOver = true;
        }

    }

    public void executeInSlave(Runnable mainThread, Retryable slaveThreadRun, Runnable timeoutRun) {

        if (slaveThreadRun != null) {
            new Thread(() -> {
                while (true) {
                    currentCount++;
                    if (slaveThreadRun.run()) {
                        break;
                    }
                    if (currentCount > maxCount) {
                        if (timeoutRun != null) {
                            timeoutRun.run();
                        }
                        break;
                    }
                    org.apache.hadoop.util.ThreadUtil.sleepAtLeastIgnoreInterrupts(interval);
                }
            }).start();
        }
        if (mainThread != null) {
            mainThread.run();
        }
    }

    public interface Retryable {
        boolean run();
    }

}
