package com.point.accept.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.point.accept.bean.AcceptBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @author huixing
 * @description kafka生产者接口
 * @date 2019/10/29
 */
public interface KafkaSender {

//    @Async
    void send(String msg);

}

