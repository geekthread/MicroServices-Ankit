package com.amigosecode.customer.service;

import com.amigosecode.clients.fraud.FraudCheckResponse;
import com.amigosecode.clients.fraud.FraudClient;
import com.amigosecode.clients.notification.NotificationClient;
import com.amigosecode.clients.notification.NotificationRequest;
import com.amigosecode.customer.entity.Customer;
import com.amigosecode.customer.repository.CustomerRepository;
import com.amigosecode.customer.vo.CustomerRegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerService {
    private CustomerRepository customerRepository;
    private RestTemplate restTemplate;
    private FraudClient fraudClient;
    private NotificationClient notificationClient;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();

        //todo validate email
        //todo validate if email not taken
        customerRepository.saveAndFlush(customer); // without flush customer id would be null.
        log.info("Customer id for fraud check :" + customer.getId());


        FraudCheckResponse response = fraudClient.isFraudCustomer(customer.getId());

        log.info("Is a fraud customer : " + response.isFraudster());

        if(response.isFraudster()){
            throw new RuntimeException("Fraudster!!");
        }

        //todo send notification
        NotificationRequest notificationRequest = NotificationRequest.builder()
                .toCustomerId(customer.getId())
                .message("Not a Fraud")
                .toCustomerEmail(customer.getEmail())
                .build();

        log.info("Sending notification : " + notificationRequest);
        //todo ; make this async: i.e. add to a queue
        notificationClient.sendNotification(notificationRequest);
    }
}
