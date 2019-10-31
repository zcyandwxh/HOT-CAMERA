//package com.point.accept.job;
//
//import com.dangdang.ddframe.job.api.ShardingContext;
//import com.dangdang.ddframe.job.api.simple.SimpleJob;
//import com.dangdang.ddframe.job.util.env.IpUtils;
//import dataacquire.job.manager.AbstractJobManager;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.ArrayUtils;
//import org.apache.commons.lang.StringUtils;
//import point.view.common.consts.Constant;
//import point.view.common.exception.JobException;
//import point.view.common.util.DateUtil;
//
//import java.util.Calendar;
//
///**
// * 任务父类
// */
//@Slf4j
//public abstract class AbstractJob implements SimpleJob {
//
//    /**
//     * 任务ID
//     */
//    @Getter
//    @Setter
//    private String jobId;
//
//    /**
//     * 任务参数
//     */
//    @Getter
//    @Setter
//    private JobParam jobParam;
//
//    /**
//     * 任务运行条件
//     */
//    @Setter
//    private JobRunCondition runCondition;
//
//    /**
//     * 是否初始化过
//     */
//    private boolean hasInitialized = false;
//
//    /**
//     * 是否已被停止
//     */
//    private boolean hasExited = false;
//
//    /**
//     * 上下文
//     */
//    private ShardingContext shardingContext;
//
//    /**
//     * 任务操作API
//     */
//    @Setter
//    private AbstractJobManager jobManager;
//
//    /**
//     * 任务状态代码
//     */
//    @Getter
//    private String statusCode;
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void execute(ShardingContext shardingContext) {
//
//        // 检查是否符合运行条件
//        if (!isOnRunTime()) {
//            this.statusCode = Constant.MessageCode.JOB_NOT_IN_TIME;
//            if (hasInitialized) {
//                onStop();
//                hasInitialized = false;
//            }
//            // 若为一次性任务，则彻底停止
//            int frequency = runCondition.getFrequency();
//            if (frequency == Constant.JobFrequency.ONE_TIME) {
//                jobManager.stopJob(jobId);
//            }
//            return;
//        }
//
//        if (!hasInitialized) {
//            this.shardingContext = shardingContext;
//            init();
//
//            // 启动时处理执行
//            try {
//                this.statusCode = Constant.MessageCode.JOB_STARTING;
//                onStartup();
//                this.statusCode = Constant.MessageCode.JOB_ONLINE;
//                hasInitialized = true;
//
//            } catch (Throwable e) {
//                if (e instanceof JobException) {
//                    this.statusCode = ((JobException) e).getErrorCode();
//                } else {
//                    this.statusCode = Constant.MessageCode.UNKNOWN;
//                }
//                log.error("onStartup", e);
//                onStop();
//            }
//
//        } else {
//
//            try {
//                // 心跳处理
//                heartbeat();
//
//            } catch (Throwable e) {
//                if (e instanceof JobException) {
//                    this.statusCode = ((JobException) e).getErrorCode();
//                } else {
//                    this.statusCode = Constant.MessageCode.UNKNOWN;
//                }
//                // 若心跳处理发生异常，则停止后，等待下次重启。
//                log.error("onHeartbeat", e);
//                onStop();
//                hasInitialized = false;
//            }
//        }
//    }
//
//    /**
//     * 初始化处理
//     */
//    protected void init() {
//
//    }
//
//    /**
//     * 获取任务在系统中的唯一标识
//     *
//     * @return 唯一标识
//     */
//    protected String getUniqId() {
//        return jobId.concat("_").concat(IpUtils.getHostName()).concat(String.valueOf(shardingContext.getShardingItem()));
//    }
//
//    protected abstract void onStartup();
//
//    protected abstract void onHeartbeat();
//
//    protected abstract void onStop();
//
//    /**
//     * 停止任务
//     */
//    public synchronized void stop() {
//        onStop();
//    }
//
//    /**
//     * 心跳处理
//     */
//    public synchronized void heartbeat() {
//        onHeartbeat();
//    }
//
//    /**
//     * 判断当前时间是否符合启动时间条件
//     *
//     * @return 是否符合
//     */
//    private boolean isOnRunTime() {
//
//        int frequency = runCondition.getFrequency();
//        String runDay = runCondition.getRunDay();
//        String startTime = runCondition.getStartTime();
//        String endTime = runCondition.getEndTime();
//
//        Calendar calendar = Calendar.getInstance();
//        String currentTime = DateUtil.getTimeOnlyDefaultFormat(calendar.getTime());
//
//        // 若条件为“每天”或者 runDay为空，则直接OK，否则进行runDay的判断
//        if (frequency != Constant.JobFrequency.EVERY_DAY && !StringUtils.isEmpty(runDay)) {
//            String currentDay = "";
//
//            // 例如：runDay="1,2,3"，在条件为每周时，则代表星期一、星期二、星期三；条件为每月时，则代表1号、2号、3号
//            String[] runDays = StringUtils.isEmpty(runDay) ? null : runDay.split(",");
//
//            // 若条件为“每周”
//            if (frequency == Constant.JobFrequency.EVERY_WEEK) {
//                // 则获取当前是星期几
//                currentDay = String.valueOf(calendar.get(Calendar.DAY_OF_WEEK) - 1);
//
//                // 若条件为“每月”
//            } else if (frequency == Constant.JobFrequency.EVERY_MONTH) {
//                // 则获取当前是几号
//                currentDay = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
//
//                // 若条件为“一次性”
//            } else if (frequency == Constant.JobFrequency.ONE_TIME) {
//                // 则获取当前日期
//                currentDay = DateUtil.getCurrentDateDefaultFormat();
//            }
//            if (!ArrayUtils.contains(runDays, currentDay)) {
//                return false;
//            }
//        }
//
//        // 判断时间部分是否符合
//        if ((!StringUtils.isEmpty(startTime) && currentTime.compareTo(startTime) < 0)
//                || (!StringUtils.isEmpty(endTime) && currentTime.compareTo(endTime) >= 0)) {
//            return false;
//        }
//        return true;
//    }
//}
