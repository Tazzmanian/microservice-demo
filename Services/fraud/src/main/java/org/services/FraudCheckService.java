package org.services;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FraudCheckService {

    private final FraudCheckHistoryRepository historyRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public FraudCheckResponse validate(Integer customerId) {
        var fraud = FraudCheckHistory.builder()
                .createdAt(LocalDateTime.now())
                .customerId(customerId)
                .isFraudster(false)
                .build();
        historyRepository.save(fraud);

        kafkaTemplate.send("custom", fraud.toString());
        return new FraudCheckResponse(fraud.getIsFraudster());
    }
}
