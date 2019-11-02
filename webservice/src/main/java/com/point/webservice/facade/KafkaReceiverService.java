package com.point.webservice.facade;

//import com.point.common.util.ImageUtil;
import com.point.common.biz.BizResult;
import com.point.common.util.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Optional;

/**
 * @author huixing
 * @description Kafka接收程序
 * @date 2019/10/29
 */
@Slf4j
@Component
public class KafkaReceiverService {

    @Autowired
    private WeedFSService weedFSService;

    /**
     * 监听 "picTopic" 将图片数据存入文件系统和数据库
     * @param record
     */
    @KafkaListener(topics = {"picTopic"})
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {

            Object message = kafkaMessage.get();
            String msg = (String)message;
            String accept = msg.substring(msg.indexOf("{"));

            byte[] imgBytes = ImageUtil.decodeBase64(accept);
            try {
                weedFSService.init();
            } catch (Exception e) {
                log.info("图片服务器初始化失败", e);
            }
            InputStream fileImputStream = new ByteArrayInputStream(imgBytes);
            BizResult<String> weedfsStoreResult = weedFSService.storagePic(fileImputStream);
            if (!weedfsStoreResult.getFlag()){
                log.info("KafkaReceiverService.class, 图片数据存入失败", weedfsStoreResult.getDesc());
            }
        }
    }
}
