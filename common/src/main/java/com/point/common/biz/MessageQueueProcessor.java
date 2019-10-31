package com.point.common.biz;//package com.point.common.biz;
//
//import com.alibaba.fastjson.JSON;
//import com.point.common.msg.MsgWrapper;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.io.ByteArrayInputStream;
//import java.util.List;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
///**
// * 消息队列数据处理器父类
// */
//@Slf4j
//public abstract class MessageQueueProcessor extends Processor<MsgWrapper, Boolean> {
//
//    /**
//     * 消息队列控制器
//     */
//    @Setter
//    private MsgQueueManager receive;
//
//    /**
//     * 消息队列控制器
//     */
//    @Setter
//    private MsgQueueManager publish;
//
//    /**
//     * 线程管理器
//     */
//    @Autowired
//    private ProcessorThreadManager threadManager;
//
//    /**
//     * 系统参数管理工具类
//     */
//    @Autowired
//    private SysParamManager sysParamManager;
//
//    /**
//     * 业务共通
//     */
//    @Autowired
//    private BizCommon bizCommon;
//
//    /**
//     * 文件存储工具类
//     */
//    @Autowired
//    private StorageManager storageManager;
//
//    /**
//     * HTTP 工具类
//     */
//    @Autowired
//    private RestClient restClient;
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
//     * 定时服务
//     */
//    private ScheduledExecutorService service;
//
//    /**
//     * 执行
//     */
//    public void execute() {
//
//        if (hasExited) {
//            return;
//        }
//
//        if (!hasInitialized) {
//
//            // 启动时处理执行
//            onStartup();
//
//            // 注册消息队列消费者
//            new Thread(this::registerConsumer).start();
//
//            // 启动心跳定时处理线程
//            Runnable timingRunnable = () -> {
//                if (!onHeartbeat()) {
//                    exit();
//                }
//            };
//            // 定时线程执行器
//            service = Executors.newSingleThreadScheduledExecutor();
//            // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
//            service.scheduleAtFixedRate(timingRunnable, 5, 10, TimeUnit.SECONDS);
//
//            hasInitialized = true;
//
//        } else {
//
//            if (!onHeartbeat()) {
//                exit();
//            }
//        }
//
//    }
//
//    /**
//     * 退出
//     */
//    private void exit() {
//        hasExited = true;
//        receive.close();
//        if (service != null) {
//            service.shutdown();
//        }
//        onStop();
//    }
//
//    /**
//     * 注册消息队列消费者
//     */
//    private void registerConsumer() {
//        int multiProcessCnt = getMultiProcessCount();
////        //  注册消息队列消费者
////        receive.registerConsumer((message) -> {
////
////            if (multiProcessCnt > 0) {
////                threadManager.addExecuteTask(this.getClass().getSimpleName(), () -> consumerCore(message));
////                return true;
////
////            } else {
////                return consumerCore(message);
////            }
////        }, targetQueues());
//        if (multiProcessCnt > 0) {
//            for (int i = 0; i < multiProcessCnt; i++) {
////                new Thread(() -> {
//                    // 注册消息队列消费者
//                    receive.registerConsumer(this::consumerCore, targetQueues());
////                }).start();
//            }
//        } else {
//            receive.registerConsumer(this::consumerCore, targetQueues());
//        }
//    }
//
//    /**
//     * 消息处理核心
//     *
//     * @param message 消息内容
//     * @return 处理结果
//     */
//    private boolean consumerCore(String message) {
//
//        // 此时 msgWrapper.getMessage() 为JSON Object
//        MsgWrapper msgWrapper = bizCommon.unpackMessage(message);
//
//        // 消息处理
//        log.debug("onProcess {} {}", msgWrapper.getDataType(), JSON.toJSONString(msgWrapper.getMessage()));
//        boolean result = onPreprocess(msgWrapper);
//        if (result) {
//            result = onProcess(msgWrapper);
//        }
//        return result;
//    }
//
//    /**
//     * 启动时处理
//     */
//    protected void onStartup() {
//
//    }
//
//    /**
//     * 心跳时处理
//     *
//     * @return 处理结果（true为继续，false为终止）
//     */
//    public boolean onHeartbeat() {
//        return true;
//    }
//
//    /**
//     * 数据处理
//     *
//     * @param msgWrapper 消息数据
//     * @return 处理结果（true为继续，false为终止）
//     */
//    public Boolean process(MsgWrapper msgWrapper) {
//        return onPreprocess(msgWrapper);
//    }
//
//    /**
//     * 数据处理
//     *
//     * @param msgWrapper 消息数据
//     * @return 处理结果（true为继续，false为终止）
//     */
//    public abstract boolean onProcess(MsgWrapper msgWrapper);
//
//    /**
//     * 数据预处理
//     *
//     * @param msgWrapper 消息数据
//     * @return 处理结果（true为继续，false为终止）
//     */
//    protected boolean onPreprocess(MsgWrapper msgWrapper) {
//        return true;
//    }
//
//    /**
//     * 停止时处理
//     */
//    protected void onStop() {
//    }
//
//    /**
//     * 获取监视目标消息队列名
//     *
//     * @return 监视目标消息队列名
//     */
//    protected abstract String[] targetQueues();
//
//    /**
//     * 发送消息
//     *
//     * @param exchange 路由
//     * @param msg      消息
//     */
//    public void publishQueue(String exchange, MsgWrapper msg) {
//        if (publish == null) {
//            throw new DevelopmentException("Publish Manager is not initialized.");
//        }
//        publish.publish(exchange, msg);
//    }
//
//    /**
//     * 存储图片
//     *
//     * @param imageBase64 图片Base64
//     * @param fileName    文件名
//     * @param jobId       任务ID
//     * @return 存储路径
//     */
//    public String storeCaptureImage(String imageBase64, String fileName, String jobId, String fileId) {
//
//        List<String> fileIds = storageManager.store(
//                new ByteArrayInputStream(ImageUtil.decodeBase64(imageBase64)), jobId,
//                Constant.FileType.IMAGE, Constant.FileUploadType.CAPTURE,
//                fileName, null, fileId);
//        log.debug("Image file storage {} {}", fileId, StringUtils.length(imageBase64));
//        return fileIds.get(0);
//    }
//
//    /**
//     * 生成存储图片ID
//     *
//     * @param dataId 数据ID
//     * @param jobId  任务ID
//     * @return 存储路径
//     */
//    public String makeStoreCapImgId(String dataId, String jobId) {
//        String time = StringUtils.left(dataId, 17);
//        String random = StringUtils.mid(dataId, 17, 6);
//        return storageManager.makeFileId(time, random, jobId, Constant.FileType.IMAGE);
//    }
//
////    /**
////     * 是否开启多线程
////     *
////     * @return 是否开启多线程
////     */
////    public boolean isMultiThreadEnabled() {
////        return true;
////    }
//
//    /**
//     * 获取并行处理数
//     *
//     * @return 并行处理数
//     */
//    public int getMultiProcessCount() {
//
//        return sysParamManager.getSysConfigInt(Constant.SysConfig.PROCESS_THREAD_COUNT, 10);
//    }
//
//}
