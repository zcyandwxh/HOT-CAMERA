package com.point.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * MessageQueue配置
 */
@Configuration
@Data
@PropertySource("classpath:msgqueue.properties")
public class MsgQueueConfig {

    /**
     * 连接Uri
     */
    @Value("${msgqueue.preprocess.uri}")
    private String preprocessUri;

    /**
     * 连接Uri
     */
    @Value("${msgqueue.process.uri}")
    private String processUri;

    /**
     * 预处理消息队列 - 人脸抓拍原图
     */
    @Value("${msgqueue.queue.preprocess.face.full}")
    private String queuePreprocessFaceFull;

    /**
     * 预处理消息队列交换机 - 人脸抓拍原图
     */
    @Value("${msgqueue.exchange.preprocess.face.full}")
    private String exchangePreprocessFaceFull;

    /**
     * 预处理消息队列 - 人脸抓拍数据
     */
    @Value("${msgqueue.queue.preprocess.face.capture}")
    private String queuePreprocessFaceCapture;

    /**
     * 预处理消息队列交换机 - 人脸抓拍数据
     */
    @Value("${msgqueue.exchange.preprocess.face.capture}")
    private String exchangePreprocessFaceCapture;

    /**
     * 预处理消息队列 - 人脸结构化数据
     */
    @Value("${msgqueue.queue.preprocess.face.recognize}")
    private String queuePreprocessFaceRecognize;

    /**
     * 预处理消息队列交换机 - 人脸结构化数据
     */
    @Value("${msgqueue.exchange.preprocess.face.recognize}")
    private String exchangePreprocessFaceRecognize;

    /**
     * 预处理消息队列 - 人脸比对数据
     */
    @Value("${msgqueue.queue.preprocess.face.compare}")
    private String queuePreprocessFaceCompare;

    /**
     * 预处理消息队列交换机 - 人脸比对数据
     */
    @Value("${msgqueue.exchange.preprocess.face.compare}")
    private String exchangePreprocessFaceCompare;

    /**
     * 应用处理消息队列 - 人脸抓拍结果处理
     */
    @Value("${msgqueue.queue.result.face.capture.process}")
    private String queueResultFaceCaptureProcess;


    /**
     * 应用处理消息队列交换机 - 人脸抓拍结果
     */
    @Value("${msgqueue.exchange.result.face.capture}")
    private String exchangeResultFaceCapture;

    /**
     * 应用处理消息队列 - 人脸抓拍结果推送
     */
    @Value("${msgqueue.queue.result.face.capture.report}")
    private String queueResultFaceCaptureReport;

    /**
     * 应用处理消息队列交换机 - 人脸抓拍结果推送
     */
    @Value("${msgqueue.exchange.result.face.capture.report}")
    private String exchangeResultFaceCaptureReport;

    /**
     * 应用处理消息队列 - 人脸抓拍结果后处理
     */
    @Value("${msgqueue.queue.result.face.capture.after}")
    private String queueResultFaceCaptureAfter;

    /**
     * 应用处理消息队列交换机 - 人脸抓拍结果后处理
     */
    @Value("${msgqueue.exchange.result.face.capture.after}")
    private String exchangeResultFaceCaptureAfter;

    /**
     * 应用处理消息队列 - 人脸比对结果处理
     */
    @Value("${msgqueue.queue.result.face.compare.process}")
    private String queueResultFaceCompareProcess;

    /**
     * 应用处理消息队列 - 人脸比对结果录像抽取
     */
    @Value("${msgqueue.queue.result.face.compare.download}")
    private String queueResultFaceCompareDownload;

    /**
     * 应用处理消息队列 - 人脸比对结果筛选
     */
    @Value("${msgqueue.queue.result.face.compare.filter}")
    private String queueResultFaceCompareFilter;

    /**
     * 应用处理消息队列交换机 - 人脸比对结果
     */
    @Value("${msgqueue.exchange.result.face.compare}")
    private String exchangeResultFaceCompare;

    /**
     * 应用处理消息队列 - 人脸比对结果推送
     */
    @Value("${msgqueue.queue.result.face.compare.report}")
    private String queueResultFaceCompareReport;

    /**
     * 应用处理消息队列 - 人脸比对结果推送2
     */
    @Value("${msgqueue.queue.result.face.compare.report2}")
    private String queueResultFaceCompareReport2;

    /**
     * 应用处理消息队列交换机 - 人脸比对结果推送
     */
    @Value("${msgqueue.exchange.result.face.compare.report}")
    private String exchangeResultFaceCompareReport;

    /**
     * 预处理消息队列 - 车辆抓拍原图
     */
    @Value("${msgqueue.queue.preprocess.vehicle.full}")
    private String queuePreprocessVehicleFull;

    /**
     * 预处理消息队列交换机 - 车辆抓拍原图
     */
    @Value("${msgqueue.exchange.preprocess.vehicle.full}")
    private String exchangePreprocessVehicleFull;

    /**
     * 预处理消息队列 - 车辆抓拍数据
     */
    @Value("${msgqueue.queue.preprocess.vehicle.capture}")
    private String queuePreprocessVehicleCapture;

    /**
     * 预处理消息队列交换机 - 车辆抓拍数据
     */
    @Value("${msgqueue.exchange.preprocess.vehicle.capture}")
    private String exchangePreprocessVehicleCapture;

    /**
     * 预处理消息队列 - 车辆结构化数据
     */
    @Value("${msgqueue.queue.preprocess.vehicle.recognize}")
    private String queuePreprocessVehicleRecognize;

    /**
     * 预处理消息队列交换机 - 车辆结构化数据
     */
    @Value("${msgqueue.exchange.preprocess.vehicle.recognize}")
    private String exchangePreprocessVehicleRecognize;

    /**
     * 预处理消息队列 - 车辆比对数据
     */
    @Value("${msgqueue.queue.preprocess.vehicle.compare}")
    private String queuePreprocessVehicleCompare;

    /**
     * 预处理消息队列交换机 - 车辆比对数据
     */
    @Value("${msgqueue.exchange.preprocess.vehicle.compare}")
    private String exchangePreprocessVehicleCompare;

    /**
     * 应用处理消息队列 - 车辆抓拍结果处理
     */
    @Value("${msgqueue.queue.result.vehicle.capture.process}")
    private String queueResultVehicleCaptureProcess;

    /**
     * 应用处理消息队列交换机 - 车辆抓拍结果
     */
    @Value("${msgqueue.exchange.result.vehicle.capture}")
    private String exchangeResultVehicleCapture;

    /**
     * 应用处理消息队列 - 车辆抓拍结果推送
     */
    @Value("${msgqueue.queue.result.vehicle.capture.report}")
    private String queueResultVehicleCaptureReport;

    /**
     * 应用处理消息队列交换机 - 车辆抓拍结果推送
     */
    @Value("${msgqueue.exchange.result.vehicle.capture.report}")
    private String exchangeResultVehicleCaptureReport;

    /**
     * 应用处理消息队列 - 车辆抓拍结果后处理
     */
    @Value("${msgqueue.queue.result.vehicle.capture.after}")
    private String queueResultVehicleCaptureAfter;

    /**
     * 应用处理消息队列交换机 - 车辆抓拍结果后处理
     */
    @Value("${msgqueue.exchange.result.vehicle.capture.after}")
    private String exchangeResultVehicleCaptureAfter;

    /**
     * 应用处理消息队列 - 车辆比对结果处理
     */
    @Value("${msgqueue.queue.result.vehicle.compare.process}")
    private String queueResultVehicleCompareProcess;

    /**
     * 应用处理消息队列 - 车辆比对结果录像抽取
     */
    @Value("${msgqueue.queue.result.vehicle.compare.download}")
    private String queueResultVehicleCompareDownload;

    /**
     * 应用处理消息队列 - 车辆比对结果筛选
     */
    @Value("${msgqueue.queue.result.vehicle.compare.filter}")
    private String queueResultVehicleCompareFilter;

    /**
     * 应用处理消息队列交换机 - 车辆比对结果
     */
    @Value("${msgqueue.exchange.result.vehicle.compare}")
    private String exchangeResultVehicleCompare;

    /**
     * 应用处理消息队列 - 车辆比对结果展示
     */
    @Value("${msgqueue.queue.result.vehicle.compare.report}")
    private String queueResultVehicleCompareReport;

    /**
     * 应用处理消息队列 - 车辆比对结果展示
     */
    @Value("${msgqueue.queue.result.vehicle.compare.report2}")
    private String queueResultVehicleCompareReport2;

    /**
     * 应用处理消息队列交换机 - 车辆比对结果展示
     */
    @Value("${msgqueue.exchange.result.vehicle.compare.report}")
    private String exchangeResultVehicleCompareReport;

    /**
     * 预处理消息队列 - 人体抓拍原图
     */
    @Value("${msgqueue.queue.preprocess.body.full}")
    private String queuePreprocessBodyFull;

    /**
     * 预处理消息队列交换机 - 人体抓拍原图
     */
    @Value("${msgqueue.exchange.preprocess.body.full}")
    private String exchangePreprocessBodyFull;

    /**
     * 预处理消息队列 - 人体抓拍数据
     */
    @Value("${msgqueue.queue.preprocess.body.capture}")
    private String queuePreprocessBodyCapture;

    /**
     * 预处理消息队列交换机 - 人体抓拍数据
     */
    @Value("${msgqueue.exchange.preprocess.body.capture}")
    private String exchangePreprocessBodyCapture;

    /**
     * 预处理消息队列 - 人体结构化数据
     */
    @Value("${msgqueue.queue.preprocess.body.recognize}")
    private String queuePreprocessBodyRecognize;

    /**
     * 预处理消息队列交换机 - 人体结构化数据
     */
    @Value("${msgqueue.exchange.preprocess.body.recognize}")
    private String exchangePreprocessBodyRecognize;

    /**
     * 预处理消息队列 - 人体比对数据
     */
    @Value("${msgqueue.queue.preprocess.body.compare}")
    private String queuePreprocessBodyCompare;

    /**
     * 预处理消息队列交换机 - 人体比对数据
     */
    @Value("${msgqueue.exchange.preprocess.body.compare}")
    private String exchangePreprocessBodyCompare;

    /**
     * 应用处理消息队列 - 人体抓拍结果处理
     */
    @Value("${msgqueue.queue.result.body.capture.process}")
    private String queueResultBodyCaptureProcess;

    /**
     * 应用处理消息队列交换机 - 人体抓拍结果
     */
    @Value("${msgqueue.exchange.result.body.capture}")
    private String exchangeResultBodyCapture;

    /**
     * 应用处理消息队列 - 人体抓拍结果推送
     */
    @Value("${msgqueue.queue.result.body.capture.report}")
    private String queueResultBodyCaptureReport;

    /**
     * 应用处理消息队列交换机 - 人体抓拍结果推送
     */
    @Value("${msgqueue.exchange.result.body.capture.report}")
    private String exchangeResultBodyCaptureReport;

    /**
     * 应用处理消息队列 - 人体抓拍结果后处理
     */
    @Value("${msgqueue.queue.result.body.capture.after}")
    private String queueResultBodyCaptureAfter;

    /**
     * 应用处理消息队列交换机 - 人体抓拍结果后处理
     */
    @Value("${msgqueue.exchange.result.body.capture.after}")
    private String exchangeResultBodyCaptureAfter;

    /**
     * 应用处理消息队列 - 人体比对结果处理
     */
    @Value("${msgqueue.queue.result.body.compare.process}")
    private String queueResultBodyCompareProcess;

    /**
     * 应用处理消息队列 - 人体比对结果录像抽取
     */
    @Value("${msgqueue.queue.result.body.compare.download}")
    private String queueResultBodyCompareDownload;

    /**
     * 应用处理消息队列 - 人体比对结果筛选
     */
    @Value("${msgqueue.queue.result.body.compare.filter}")
    private String queueResultBodyCompareFilter;

    /**
     * 应用处理消息队列交换机 - 人体比对结果
     */
    @Value("${msgqueue.exchange.result.body.compare}")
    private String exchangeResultBodyCompare;

    /**
     * 应用处理消息队列 - 人体比对结果展示
     */
    @Value("${msgqueue.queue.result.body.compare.report}")
    private String queueResultBodyCompareReport;

    /**
     * 应用处理消息队列 - 人体比对结果展示
     */
    @Value("${msgqueue.queue.result.body.compare.report2}")
    private String queueResultBodyCompareReport2;

    /**
     * 应用处理消息队列交换机 - 人体比对结果展示
     */
    @Value("${msgqueue.exchange.result.body.compare.report}")
    private String exchangeResultBodyCompareReport;

    /**
     * 应用处理消息队列 - 设备状态处理
     */
    @Value("${msgqueue.queue.device.status.process}")
    private String queueDeviceStatusProcess;

    /**
     * 应用处理消息队列交换机 - 设备状态处理
     */
    @Value("${msgqueue.exchange.device.status.process}")
    private String exchangeDeviceStatusProcess;

    /**
     * 应用处理消息队列 - 设备状态展示
     */
    @Value("${msgqueue.queue.device.status.report}")
    private String queueDeviceStatusReport;

    /**
     * 应用处理消息队列交换机 - 设备状态
     */
    @Value("${msgqueue.exchange.device.status.report}")
    private String exchangeDeviceStatusReport;

    /**
     * 应用处理消息队列 - 设布控任务状态处理
     */
    @Value("${msgqueue.queue.job.status.process}")
    private String queueJobStatusProcess;

    /**
     * 应用处理消息队列交换机 - 布控任务状态处理
     */
    @Value("${msgqueue.exchange.job.status.process}")
    private String exchangeJobStatusProcess;

    /**
     * 应用处理消息队列 - 布控任务状态展示
     */
    @Value("${msgqueue.queue.job.status.report}")
    private String queueJobStatusReport;

    /**
     * 应用处理消息队列交换机 - 布控任务状态
     */
    @Value("${msgqueue.exchange.job.status.report}")
    private String exchangeJobStatusReport;

    /**
     * 应用处理消息队列 - 任务状态处理
     */
    @Value("${msgqueue.queue.task.status.process}")
    private String queueTaskStatusProcess;

    /**
     * 应用处理消息队列 - 任务状态展示
     */
    @Value("${msgqueue.queue.task.status.report}")
    private String queueTaskStatusReport;

    /**
     * 应用处理消息队列交换机 - 任务状态
     */
    @Value("${msgqueue.exchange.task.status}")
    private String exchangeTaskStatus;

    /**
     * 应用处理消息队列 - 任务状态处理
     */
    @Value("${msgqueue.queue.task.process}")
    private String queueTaskProcess;

    /**
     * 应用处理消息队列交换机 - 任务状态
     */
    @Value("${msgqueue.exchange.task.process}")
    private String exchangeTaskProcess;

    /**
     * 搜索处理消息队列 - 任务状态处理
     */
    @Value("${msgqueue.queue.task.search}")
    private String queueTaskSearch;

    /**
     * 搜索处理消息队列交换机 - 任务状态
     */
    @Value("${msgqueue.exchange.task.search}")
    private String exchangeTaskSearch;

    /**
     * 应用处理消息队列 - 人脸命中
     */
    @Value("${msgqueue.queue.hit.face.report}")
    private String queueHitFaceReport;

    /**
     * 应用处理消息队列交换机 - 人脸命中
     */
    @Value("${msgqueue.exchange.hit.face}")
    private String exchangeHitFace;

    /**
     * 应用处理消息队列 - 车辆命中
     */
    @Value("${msgqueue.queue.hit.vehicle.report}")
    private String queueHitVehicleReport;

    /**
     * 应用处理消息队列交换机 - 车辆命中
     */
    @Value("${msgqueue.exchange.hit.vehicle}")
    private String exchangeHitVehicle;

    /**
     * 应用处理消息队列 - 人体命中
     */
    @Value("${msgqueue.queue.hit.body.report}")
    private String queueHitBodyReport;

    /**
     * 应用处理消息队列交换机 - 人体命中
     */
    @Value("${msgqueue.exchange.hit.body}")
    private String exchangeHitBody;

    /**
     * 应用处理消息队列 - 统计数据
     */
    @Value("${msgqueue.queue.statistics}")
    private String queueStatistics;

    /**
     * 应用处理消息队列交换机 - 统计数据
     */
    @Value("${msgqueue.exchange.statistics}")
    private String exchangeStatistics;

    /**
     * 是否开启网页告警消息队列功能
     */
    @Value("${webalarm.isopen}")
    private boolean isOpenAlarmQueue;

    /**
     * 应用处理消息队列交换机 - 所有告警
     */
    @Value("${msgqueue.exchange.alarm}")
    private String exchangeAlarmFace;

    /**
     * 应用处理消息队列 - 所有告警
     */
    @Value("${msgqueue.queue.alarm}")
    private String alarmFaceQueue;

    /**
     * 应用处理消息队列 - 网页告警
     */
    @Value("${msgqueue.queue.web.alarm}")
    private String alarmFcQueue;

}
