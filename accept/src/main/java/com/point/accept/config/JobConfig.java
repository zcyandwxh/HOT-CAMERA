//package com.point.accept.config;
//
//import lombok.Data;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
///**
// * 任务相关设定
// */
////@Component
////@Data
////@Configuration
//public class JobConfig {
//
//    /**
//     * 是否开启数据模拟
//     */
//    @Value("${job.simulator.enable:true}")
//    private boolean simulatorEnable;
//
//    /**
//     * 任务是否使用消息队列
//     */
//    @Value("${job.mq.use}")
//    private boolean useMq;
//
//    /**
//     * 任务管理class
//     */
//    @Value("${job.class.manager}")
//    private String managerClass;
//
//    /**
//     * 任务布控处理class
//     */
//    @Value("${job.class.surveillance}")
//    private String surveillanceClass;
//
//    /**
//     * 任务布控回调处理class
//     */
//    @Value("${job.class.callback}")
//    private String callbackClass;
//
//    /**
//     * 任务调试图片输出
//     */
//    @Value("${job.debug.img.output:false}")
//    private boolean debugImgOutput;
//
//    /**
//     * 任务调试图片输出目录
//     */
//    @Value("${job.debug.img.dir:none}")
//    private String debugOutputDir;
//
//}
