package com.amigosecode.fraud.service;

import com.amigosecode.fraud.entity.FraudCheckHistory;
import com.amigosecode.fraud.repository.FraudCheckRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckService {
    private FraudCheckRepository fraudRepository;

    public boolean isFraudster(Long customerId) {
        FraudCheckHistory fraudCheckHistory = FraudCheckHistory.builder()
                .isFraudster(false)
                .createdAt(LocalDateTime.now())
                .customerId(customerId)
                .build();
        fraudRepository.save(fraudCheckHistory);

        return false; //Integrate with 3rd party system for validation against records.
    }
}
