package org.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaListeners {

    @KafkaListener(topics = "custom", groupId = "foo")
    void listener(String data) {
        log.info("Message received: `{}`", data);
    }
}
