//package com.point.accept.controller;
//
//import com.point.common.cache.BizCache;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @author huixing
// * @description HTTP縂回調
// * @date 2019/10/29
// */
//@RestController
//@EnableAutoConfiguration
//@Slf4j
//@RequestMapping("/")
//public class HttpCallBackController {
//    /**
//     * HTTP 回调总路径
//     */
//    public static final String HTTP_CALLBACK_PATH = "callback";
//
//    /**
//     * 缓存
//     */
//    @Autowired
//    private BizCache bizCache;
//
//    /**
//     * 任务相关设置
//     */
//    @Autowired
//    private JobConfig jobConfig;
//
//    /**
//     * HTTP回调总入口 Post
//     *
//     * @param jobId       任务ID
//     * @param jobType     任务类型
//     * @param frtDevId    输入源ID
//     * @param capDevId    分析服务器ID
//     * @param recogPlanId 结构化方案ID
//     * @param compPlanId  比对方案ID
//     * @param requestBody 请求body
//     * @return HTTP结果
//     */
//    @RequestMapping(value = "/{jobId}/{jobType}/{frtDevId}/{capDevId}/{recogPlanId}/{compPlanId}", method = RequestMethod.POST)
//    public HttpStatus handlePostRequest(@PathVariable String jobId, @PathVariable String jobType, @PathVariable String frtDevId,
//                                        @PathVariable String capDevId, @PathVariable String recogPlanId,
//                                        @PathVariable String compPlanId, @RequestBody String requestBody, HttpServletRequest request) {
//        log.debug("callback [{}] [{}] [{}] [{}] [{}] [{}]", jobId, frtDevId, capDevId, recogPlanId, compPlanId, request.getRemoteAddr());
//        return handleRequest(jobId, jobType, frtDevId, capDevId, recogPlanId, compPlanId, requestBody);
//
//    }
//
//    /**
//     * HTTP回调总入口 Get
//     *
//     * @param jobId       任务ID
//     * @param jobType     任务类型
//     * @param frtDevId    输入源ID
//     * @param capDevId    分析服务器ID
//     * @param recogPlanId 结构化方案ID
//     * @param compPlanId  比对方案ID
//     * @param requestBody 请求body
//     * @return HTTP结果
//     */
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public HttpStatus handleGetRequest(@PathVariable String jobId, @PathVariable String jobType, @PathVariable String frtDevId,
//                                       @PathVariable String capDevId, @PathVariable String recogPlanId,
//                                       @PathVariable String compPlanId, @RequestBody String requestBody) {
//
//        return handleRequest(jobId, jobType, frtDevId, capDevId, recogPlanId, compPlanId, requestBody);
//    }
//
//    /**
//     * HTTP回调处理
//     *
//     * @param jobId       任务ID
//     * @param jobType     任务类型
//     * @param srcId       输入源ID
//     * @param svrId       分析服务器ID
//     * @param recogPlanId 结构化方案ID
//     * @param compPlanId  比对方案ID
//     * @param requestBody 请求body
//     * @return HTTP结果
//     */
//    private HttpStatus handleRequest(String jobId, String jobType, String srcId, String svrId, String recogPlanId, String compPlanId,
//                                     String requestBody) {
//
//        // 通过 缓存 + Prototype 的机制，保证不同Job拿到不同的回调实例，同时, 相同Job拿到相同实例。
//        String key = jobId.concat("_").concat(srcId);
//        AbstractCallback callback = bizCache.getJobInstance(HTTP_CALLBACK_PATH.concat(key), jobConfig.getCallbackClass());
//        callback.init(jobId, Integer.parseInt(jobType), srcId, svrId, recogPlanId, compPlanId);
//
//        try {
//            callback.callback(key, requestBody);
//            log.debug("-----haodj0313----- {}",requestBody);
//            return HttpStatus.OK;
//        } catch (Throwable e) {
//            log.error("callback error:", e.getMessage(), e);
//            return HttpStatus.OK;
//        }
//    }
//}
