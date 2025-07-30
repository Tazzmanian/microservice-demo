package org.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FraudCheckService {

    private final FraudCheckHistoryRepository historyRepository;

    public FraudCheckResponse validate(Integer customerId) {
        var fraud = FraudCheckHistory.builder()
                .createdAt(LocalDateTime.now())
                .customerId(customerId)
                .isFraudster(false)
                .build();
        historyRepository.save(fraud);
        return new FraudCheckResponse(fraud.getIsFraudster());
    }
}
