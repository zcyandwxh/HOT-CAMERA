package com.point.accept.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author huixing
 * @description kafka消费者
 * @date 2019/10/29
 */
@Component
@Slf4j
public class KafkaReceiver {

    @KafkaListener(topics = {"picTopic"})
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {

            Object message = kafkaMessage.get();

            log.info("----------------- record =" + record);
            log.info("------------------ message =" + message);
        }

    }
}

