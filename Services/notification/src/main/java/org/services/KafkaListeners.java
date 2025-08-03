package org.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaListeners {

    @KafkaListener(topics = "custom", groupId = "notification-service")
    void listener(String data, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                  @Header(KafkaHeaders.OFFSET) long offset) {
        log.info("Message received {} - {}: `{}`", topic, offset, data);
    }
}
