package com.point.webservice.facade.impl;

import com.point.common.biz.BizResult;
import com.point.common.util.ImageUtil;
import com.point.webservice.facade.KafkaReceiverService;
import com.point.webservice.facade.WeedFSService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Optional;

/**
 * @author huixing
 * @description kafka接收服务实现
 * @date 2019/11/4
 */
@Service
@Slf4j
public class KafkaReceiverServiceImpl implements KafkaReceiverService {
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
