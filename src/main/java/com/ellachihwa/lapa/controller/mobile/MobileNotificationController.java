package com.ellachihwa.lapa.controller.mobile;


import com.ellachihwa.lapa.dto.NotificationDto;
import com.ellachihwa.lapa.dto.SubscriptionRequestDto;
import com.ellachihwa.lapa.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class MobileNotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/subscribe")
    public void subscribeToTopic(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        notificationService.subscribeToTopic(subscriptionRequestDto);
    }

    @PostMapping("/unsubscribe")
    public void unsubscribeFromTopic(SubscriptionRequestDto subscriptionRequestDto) {
        notificationService.unsubscribeFromTopic(subscriptionRequestDto);
    }

    @PostMapping("/token")
    public String sendPnsToDevice(@RequestBody NotificationDto notificationDto) {
        return notificationService.sendPnsToDevice(notificationDto);
    }

    @PostMapping("/topic")
    public String sendPnsToTopic(@RequestBody NotificationDto notificationDto) {
        return notificationService.sendPnsToTopic(notificationDto);
    }
}