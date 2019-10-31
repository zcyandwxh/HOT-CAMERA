//package com.point.accept.job;
//
//import lombok.Getter;
//
///**
// * @author huixing
// * @description 抽象回調
// * @date 2019/10/29
// */
//@Getter
//public abstract class AbstractCallback {
//
//    private String jobId;
//    private Integer jobType;
//    private String frtDevId;
//    private String capDevId;
//    private String recogPlanId;
//    private String compPlanId;
//
//    /**
//     * 初始化
//     *
//     * @param jobId       任务ID
//     * @param jobType     任务类型
//     * @param frtDevId    前端设备ID
//     * @param capDevId    抓拍服务器ID
//     * @param recogPlanId 结构化方案ID
//     * @param compPlanId  比对方案ID
//     */
//    public void init(String jobId, Integer jobType, String frtDevId, String capDevId, String recogPlanId, String compPlanId) {
//        this.jobId = jobId;
//        this.jobType = jobType;
//        this.frtDevId = frtDevId;
//        this.capDevId = capDevId;
//        this.recogPlanId = recogPlanId;
//        this.compPlanId = compPlanId;
//    }
//
//    /**
//     * 回调处理
//     *
//     * @param jobId 回调程序ID
//     * @param param 回调参数
//     */
//    public abstract void callback(String jobId, String param);
//}
