package com.amigosecode.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("FRAUDSERVICE")
public interface FraudClient {
    @GetMapping(path = "/api/v1/fraud-check/{customerId}")
     FraudCheckResponse isFraudCustomer(@PathVariable("customerId") Long customerId);
}
