package com.amigosecode.notification.service;

import com.amigosecode.clients.notification.NotificationRequest;
import com.amigosecode.notification.entity.Notification;
import com.amigosecode.notification.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class NotificationService {
    private NotificationRepository repository;

    public void sendNotification(NotificationRequest request){
        Notification notification = Notification.builder()
                .message(request.getMessage())
                .sender(request.getToCustomerEmail())
                .toCustomerEmail(request.getToCustomerEmail())
                .toCustomerId(request.getToCustomerId())
                .sentAt(LocalDateTime.now())
                .build();
        log.info("Sending notification: " + notification);
        repository.saveAndFlush(notification);
        log.info("Notification sent with id : " + notification.getNotificationId());
    }


}
