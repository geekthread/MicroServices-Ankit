package com.amigosecode.notification.controller;

import com.amigosecode.clients.notification.NotificationRequest;
import com.amigosecode.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/notification")
public class NotificationController {

    private NotificationService notificationService;

    @PostMapping
    public void sendNotification(@RequestBody NotificationRequest request){
        log.info("Sending notification for request:" + request);
        notificationService.sendNotification(request);
    }

}
