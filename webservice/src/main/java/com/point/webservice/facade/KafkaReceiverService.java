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
 * @description Kafka接收接口
 * @date 2019/10/29
 */
@Component
public interface KafkaReceiverService {

    /**
     * 监听 "picTopic" 将图片数据存入文件系统和数据库
     * @param record
     */
    void listen(ConsumerRecord<?, ?> record);
}
