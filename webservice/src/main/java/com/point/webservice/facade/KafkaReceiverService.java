package com.point.webservice.facade;

//import com.point.common.util.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by huixing on 2019/11/1.
 */
@Slf4j
@Component
public class KafkaReceiverService {

//    private Base64

    @KafkaListener(topics = {"picTopic"})
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {

            Object message = kafkaMessage.get();
            String msg = (String)message;
            String accept = msg.substring(msg.indexOf("{"));
           // log.info("----------------- record =" + record);
            log.info("------------------ message =" + accept);
        }
    }
}
