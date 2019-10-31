package com.point.common.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * 线程池管理(线程统一调度管理)
 */
@Slf4j
public abstract class ThreadManager {

    /**
     * 线程池维护线程所允许的空闲时间
     */
    private static final int TIME_KEEP_ALIVE = 5000;

    /**
     * 线程池所使用的缓冲队列大小
     */
    private static final int SIZE_WORK_QUEUE = 2000;

    /**
     * 任务调度周期
     */
    private static final int PERIOD_TASK_QOS = 1000;

    /**
     * 任务缓冲队列
     */
    private final Queue<Runnable> mTaskQueue = new LinkedList<>();

    /**
     * 线程池超出界线时将任务加入缓冲队列
     */
    private final RejectedExecutionHandler mHandler = (task, executor) -> mTaskQueue.offer(task);

    /**
     * 将缓冲队列中的任务重新加载到线程池
     */
    private final Runnable mAccessBufferThread = new Runnable() {
        @Override
        public void run() {
            if (hasMoreAcquire()) {
                mThreadPool.execute(mTaskQueue.poll());
            }
        }
    };

    /**
     * 创建一个调度线程池
     */
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    /**
     * 通过调度线程周期性的执行缓冲队列中任务
     */
    protected final ScheduledFuture<?> mTaskHandler = scheduler.scheduleAtFixedRate(mAccessBufferThread, 0,
            PERIOD_TASK_QOS, TimeUnit.MILLISECONDS);

    /**
     * 线程池
     */
    private ThreadPoolExecutor mThreadPool;

    /**
     * 初始化
     */
    public void prepare(int poolSize) {
        mThreadPool = new ThreadPoolExecutor(poolSize, poolSize,
                TIME_KEEP_ALIVE, TimeUnit.SECONDS, new ArrayBlockingQueue<>(SIZE_WORK_QUEUE), mHandler);

        if (mThreadPool.isShutdown() && !mThreadPool.prestartCoreThread()) {
            mThreadPool.prestartAllCoreThreads();
        }
    }

    /**
     * 消息队列检查方法
     */
    private boolean hasMoreAcquire() {
        return !mTaskQueue.isEmpty();
    }

    /**
     * 向线程池中添加任务方法
     */
    public void addExecuteTask(String tag, Runnable task) {
        if (task != null) {
            mThreadPool.execute(task);
            log.debug("Class:{} 执行中线程数:{}, 等待中线程数: {}", this.getClass().getSimpleName(), mThreadPool.getActiveCount(), mThreadPool.getQueue().size());
        }
    }

    /**
     * 判断是否结束
     *
     * @return 是否结束
     */
    protected boolean isTaskEnd() {
        if (mThreadPool.getActiveCount() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 关闭线程池
     */
    public void shutdown() {
        mTaskQueue.clear();
        mThreadPool.shutdown();
    }
}