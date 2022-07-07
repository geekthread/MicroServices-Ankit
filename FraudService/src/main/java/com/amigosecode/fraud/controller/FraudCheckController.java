package com.amigosecode.fraud.controller;

import com.amigosecode.clients.fraud.FraudCheckResponse;
import com.amigosecode.fraud.service.FraudCheckService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fraud-check")
@AllArgsConstructor
@Slf4j
public class FraudCheckController {
    private FraudCheckService fraudService;

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudCustomer(@PathVariable("customerId") Long customerId){
       log.info("Fraud Check request for : " + customerId);
        boolean isFraudCustomer= fraudService.isFraudster(customerId);
       return FraudCheckResponse.builder()
               .isFraudster(isFraudCustomer)
               .build();
    }

}
